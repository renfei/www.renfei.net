package net.renfei.service.aliyun;

import com.aliyuncs.dcdn.model.v20180115.SetDcdnDomainCertificateRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import net.renfei.config.RenFeiConfig;
import org.springframework.stereotype.Service;

/**
 * <p>Title: AliyunDCDN</p>
 * <p>Description: 阿里云全站加速服务</p>
 *
 * @author RenFei(i @ renfei.net)
 * @date : 2021-01-30 16:19
 */
@Slf4j
@Service
public class AliyunDCDN extends AliyunService {
    protected AliyunDCDN(RenFeiConfig renFeiConfig) {
        super(renFeiConfig,
                DefaultProfile.getProfile("cn-hangzhou",
                        renFeiConfig.getAliyun().getAccessKeyId(),
                        renFeiConfig.getAliyun().getAccessKeySecret()));
    }

    public void setDcdnDomainCertificate(String domainName, String certName, String privateKey) throws ClientException {
        SetDcdnDomainCertificateRequest request = new SetDcdnDomainCertificateRequest();
        request.setDomainName(domainName);
        request.setCertName(certName);
        request.setCertType("cas");
        request.setSSLProtocol("on");
        request.setForceSet("1");
        client.getAcsResponse(request);
    }
}
