package net.renfei.service;

import com.jblur.acme_client.CommandExecutor;
import com.jblur.acme_client.Parameters;
import net.renfei.base.BaseService;
import net.renfei.config.RenFeiConfig;
import org.springframework.stereotype.Service;

/**
 * <p>Title: LetsEncryptService</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 * @date : 2021-02-11 16:31
 */
@Service
public class LetsEncryptService extends BaseService {
    private final RenFeiConfig renFeiConfig;

    public LetsEncryptService(RenFeiConfig renFeiConfig) {
        this.renFeiConfig = renFeiConfig;
    }

    /**
     * 注册CA用户帐户
     */
    public void register() {
        Parameters parameters = new Parameters();
        parameters.setCommand("register");
        parameters.setAccountKey(renFeiConfig.getLetsEncrypt().getDirPath() + "/account.key");
        parameters.setWithAgreementUpdate(true);
        parameters.setEmail(renFeiConfig.getLetsEncrypt().getEmail());
        new CommandExecutor(parameters).execute();
    }

    /**
     * 请求证书订单并下载DNS01验证文件
     *
     * @param csrFileName CSR证书请求文件名称
     */
    public void orderCertificate(String csrFileName) {
        Parameters parameters = new Parameters();
        parameters.setCommand("order-certificate");
        parameters.setAccountKey(renFeiConfig.getLetsEncrypt().getDirPath() + "/account.key");
        parameters.setWorkDir(renFeiConfig.getLetsEncrypt().getDirPath() + "/workdir/");
        parameters.setCsr(renFeiConfig.getLetsEncrypt().getDirPath() + "/" + csrFileName);
        parameters.setChallengeType("DNS01");
        parameters.setDnsDigestDir(renFeiConfig.getLetsEncrypt().getDirPath() + "/digests/");
        new CommandExecutor(parameters).execute();
    }

    /**
     * 验证域名记录(DNS01)
     *
     * @param csrFileName CSR证书请求文件名称
     */
    public void verifyDomains(String csrFileName) {
        Parameters parameters = new Parameters();
        parameters.setCommand("verify-domains");
        parameters.setAccountKey(renFeiConfig.getLetsEncrypt().getDirPath() + "/account.key");
        parameters.setWorkDir(renFeiConfig.getLetsEncrypt().getDirPath() + "/workdir/");
        parameters.setCsr(renFeiConfig.getLetsEncrypt().getDirPath() + "/" + csrFileName);
        parameters.setChallengeType("DNS01");
        new CommandExecutor(parameters).execute();
    }

    /**
     * 生成证书并下载 cert.pem，chain.pem和fullchain.pem
     *
     * @param csrFileName CSR证书请求文件名称
     */
    public void generateCertificate(String csrFileName) {
        Parameters parameters = new Parameters();
        parameters.setCommand("generate-certificate");
        parameters.setAccountKey(renFeiConfig.getLetsEncrypt().getDirPath() + "/account.key");
        parameters.setWorkDir(renFeiConfig.getLetsEncrypt().getDirPath() + "/workdir/");
        parameters.setCsr(renFeiConfig.getLetsEncrypt().getDirPath() + "/" + csrFileName);
        parameters.setCertDir(renFeiConfig.getLetsEncrypt().getDirPath() + "/certdir/");
        new CommandExecutor(parameters).execute();
    }
}
