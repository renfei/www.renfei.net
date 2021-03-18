package net.renfei.service;

import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseService;
import net.renfei.cloudflare.Cloudflare;
import net.renfei.cloudflare.entity.CreateDnsRecord;
import net.renfei.cloudflare.entity.DnsRecords;
import net.renfei.cloudflare.entity.Zone;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.ApplySslCertificate;
import net.renfei.repository.LetsEncryptDOMapper;
import net.renfei.repository.entity.LetsEncryptDOExample;
import net.renfei.repository.entity.LetsEncryptDOWithBLOBs;
import net.renfei.sdk.utils.DateUtils;
import net.renfei.sdk.utils.ListUtils;
import net.renfei.service.aliyun.AliyunCAS;
import net.renfei.service.aliyun.AliyunCDN;
import net.renfei.service.aliyun.AliyunDCDN;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: SslService</p>
 * <p>Description: SSL证书服务</p>
 *
 * @author RenFei(i @ renfei.net)
 * @date : 2021-02-11 16:38
 */
@Slf4j
@Service
public class SslService extends BaseService {
    private final AliyunCAS aliyunCAS;
    private final AliyunCDN aliyunCDN;
    private final AliyunDCDN aliyunDCDN;
    private final Cloudflare cloudflare;
    private final RenFeiConfig renFeiConfig;
    private final ExecCmdService execCmdService;
    private final LetsEncryptService letsEncryptService;
    private final LetsEncryptDOMapper letsEncryptMapper;
    private Zone zone;

    public SslService(AliyunCAS aliyunCAS,
                      AliyunCDN aliyunCDN,
                      AliyunDCDN aliyunDCDN,
                      RenFeiConfig renFeiConfig,
                      ExecCmdService execCmdService,
                      LetsEncryptService letsEncryptService,
                      LetsEncryptDOMapper letsEncryptMapper) {
        this.aliyunCAS = aliyunCAS;
        this.aliyunCDN = aliyunCDN;
        this.aliyunDCDN = aliyunDCDN;
        this.renFeiConfig = renFeiConfig;
        this.execCmdService = execCmdService;
        this.letsEncryptService = letsEncryptService;
        this.cloudflare = new Cloudflare(renFeiConfig.getCloudflare().getApiToken());
        this.letsEncryptMapper = letsEncryptMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public void checkSslCertificate() throws IOException, ClientException {
        // 检查证书
        LetsEncryptDOExample example = new LetsEncryptDOExample();
        example.setOrderByClause("end_date DESC");
        example.createCriteria();
        LetsEncryptDOWithBLOBs letsEncryptDO = ListUtils.getOne(letsEncryptMapper.selectByExampleWithBLOBs(example));
        boolean update = false;
        if (letsEncryptDO == null) {
            update = true;
        } else {
            long day = DateUtils.getDaysBetween(new Date(), letsEncryptDO.getEndDate());
            if (day <= 7) {
                update = true;
            }
        }
        if (update) {
            ApplySslCertificate applySslCertificate = this.applySslCertificate();
            letsEncryptDO = new LetsEncryptDOWithBLOBs();
            letsEncryptDO.setApplyDate(new Date());
            letsEncryptDO.setEndDate(DateUtils.nextMonth(3));
            letsEncryptDO.setCertName(applySslCertificate.getName());
            letsEncryptDO.setCertKey(applySslCertificate.getKey());
            letsEncryptDO.setCert(applySslCertificate.getCertificate());
            this.deploymentCertificate(applySslCertificate);
            letsEncryptMapper.insert(letsEncryptDO);
        }
    }

    /**
     * 申请新的SSL证书
     */
    public ApplySslCertificate applySslCertificate() throws IOException {
        // 注册CA用户帐户
        letsEncryptService.register();
        // 创建CSR证书请求文件，需要环境已经安装 openssl
        String csrFileName = "renfei_net_" + DateUtils.getDate("yyyyMMddHHmmss") + ".csr";
        String shell = "openssl req -config " + renFeiConfig.getLetsEncrypt().getDirPath() + "/openssl.cnf -new -key"
                + " " + renFeiConfig.getLetsEncrypt().getDirPath() + "/renfei.net.key -sha256 -nodes -subj"
                + " \"/C=US/ST=District Of Columbia/L=Washington/O=RENFEI.NET/OU=Security Center/CN=renfei.net/emailAddress=i@renfei.net\""
                + " -outform PEM -out " + renFeiConfig.getLetsEncrypt().getDirPath() + "/" + csrFileName;
        log.info(shell);
        String[] cmd = {"/bin/sh", "-c", shell};
        log.info(execCmdService.execCmd(cmd));
        // 请求证书订单并下载DNS01验证文件
        letsEncryptService.orderCertificate(csrFileName);
        // 修改域名记录
        String dnsDigest = reader(renFeiConfig.getLetsEncrypt().getDirPath() + "/digests/renfei.net_dns_digest"),
                dnsDigestWildcard = reader(renFeiConfig.getLetsEncrypt().getDirPath() + "/digests/renfei.net_dns_digest_wildcard");
        // Cloudflare
        DnsRecords[] dnsRecords = createDnsRecord(dnsDigest, dnsDigestWildcard);
        // 验证域名记录
        letsEncryptService.verifyDomains(csrFileName);
        // 生成证书并下载
        letsEncryptService.generateCertificate(csrFileName);
        // 删除域名记录
        deleteDnsRecord(dnsRecords[0]);
        deleteDnsRecord(dnsRecords[1]);
        // 清理WorkDir
        cmd = new String[]{"/bin/sh", "-c", "rm -rf " + renFeiConfig.getLetsEncrypt().getDirPath() + "/workdir/*"};
        execCmdService.execCmd(cmd);
        ApplySslCertificate applySslCertificate = new ApplySslCertificate();
        // 读取证书
        applySslCertificate.setCertificate(reader(renFeiConfig.getLetsEncrypt().getDirPath() + "/certdir/fullchain.pem"));
        applySslCertificate.setName(csrFileName.replace(".csr", ""));
        applySslCertificate.setKey(reader(renFeiConfig.getLetsEncrypt().getDirPath() + "/renfei.net.key"));
        return applySslCertificate;
    }

    /**
     * 部署SSL证书
     *
     * @param applySslCertificate SSL证书
     */
    public void deploymentCertificate(ApplySslCertificate applySslCertificate) throws ClientException {
        // 上传证书
        aliyunCAS.createUserCertificate(applySslCertificate.getName(), applySslCertificate.getCertificate(), applySslCertificate.getKey());
        aliyunCDN.setDomainServerCertificate("cdn.renfei.net", applySslCertificate.getName(), applySslCertificate.getKey());
        aliyunCDN.setDomainServerCertificate("ip.renfei.net", applySslCertificate.getName(), applySslCertificate.getKey());
        aliyunCDN.setDomainServerCertificate("ipv4.renfei.net", applySslCertificate.getName(), applySslCertificate.getKey());
        aliyunCDN.setDomainServerCertificate("download.renfei.net", applySslCertificate.getName(), applySslCertificate.getKey());
        aliyunCDN.setDomainServerCertificate("nifidoc.renfei.net", applySslCertificate.getName(), applySslCertificate.getKey());
        aliyunCDN.setDomainServerCertificate("start.renfei.net", applySslCertificate.getName(), applySslCertificate.getKey());
        aliyunDCDN.setDcdnDomainCertificate("www.renfei.net", applySslCertificate.getName(), applySslCertificate.getKey());
        aliyunDCDN.setDcdnDomainCertificate("bbs.renfei.net", applySslCertificate.getName(), applySslCertificate.getKey());
    }

    private String reader(String path) throws IOException {
        InputStreamReader in = new InputStreamReader(new FileInputStream(new File(path)), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(in);
        StringBuilder digestContent = new StringBuilder();
        String tempStr = "";
        while ((tempStr = br.readLine()) != null) {
            digestContent.append(tempStr).append("\n");
        }
        if (digestContent.toString().endsWith("\n")) {
            return digestContent.toString().substring(0, digestContent.toString().length() - 1);
        }
        return digestContent.toString();
    }

    private Zone getZone() throws IOException {
        if (this.zone == null) {
            List<Zone> zones = cloudflare.zone.getListZones();
            zones.forEach(zone -> {
                if ("renfei.net".equals(zone.getName())) {
                    this.zone = zone;
                }
            });
        }
        return this.zone;
    }

    private DnsRecords[] createDnsRecord(String dnsDigest, String dnsDigestWildcard) throws IOException {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("match", "all");
        paramMap.put("name", "_acme-challenge.renfei.net");
        List<DnsRecords> dnsRecords = cloudflare.dnsRecords.getListDnsRecord(getZone().getId(), paramMap);
        for (DnsRecords dnsRecord : dnsRecords
        ) {
            cloudflare.dnsRecords.deleteDnsRecord(getZone().getId(), dnsRecord.getId());
        }
        CreateDnsRecord createDnsRecord = new CreateDnsRecord();
        createDnsRecord.setType("TXT");
        createDnsRecord.setName("_acme-challenge");
        createDnsRecord.setContent(dnsDigest);
        createDnsRecord.setTtl(120);
        createDnsRecord.setProxied(false);
        createDnsRecord.setPriority(10);
        // dnsDigest
        DnsRecords dnsRecordRenFei = cloudflare.dnsRecords.createDnsRecord(getZone().getId(), createDnsRecord);
        // dnsDigestWildcard
        createDnsRecord.setContent(dnsDigestWildcard);
        DnsRecords dnsRecordRenFeiWildcard = cloudflare.dnsRecords.createDnsRecord(getZone().getId(), createDnsRecord);
        DnsRecords[] dnsRecordsArr = new DnsRecords[2];
        dnsRecordsArr[0] = dnsRecordRenFei;
        dnsRecordsArr[1] = dnsRecordRenFeiWildcard;
        return dnsRecordsArr;
    }

    private void deleteDnsRecord(DnsRecords dnsRecords) throws IOException {
        cloudflare.dnsRecords.deleteDnsRecord(getZone().getId(), dnsRecords.getId());
    }
}
