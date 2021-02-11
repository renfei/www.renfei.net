package net.renfei.service.aliyun;

import com.aliyuncs.cdn.model.v20180510.SetDomainServerCertificateRequest;
import com.aliyuncs.cdn.model.v20180510.SetDomainServerCertificateResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import net.renfei.config.RenFeiConfig;
import org.springframework.stereotype.Service;

/**
 * <p>Title: AliyunCDN</p>
 * <p>Description: 阿里云内容分发服务</p>
 *
 * @author RenFei(i @ renfei.net)
 * @date : 2021-01-30 16:04
 */
@Slf4j
@Service
public class AliyunCDN extends AliyunService {
    protected AliyunCDN(RenFeiConfig renFeiConfig) {
        super(renFeiConfig,
                DefaultProfile.getProfile("cn-hangzhou",
                        renFeiConfig.getAliyun().getAccessKeyId(),
                        renFeiConfig.getAliyun().getAccessKeySecret()));
    }

    /**
     * 设置加速域名的证书信息
     *
     * @param domainName 指定证书所属加速域名，需属于https加速类型
     * @param certName   证书名称
     */
    public void setDomainServerCertificate(String domainName, String certName, String privateKey) throws ClientException {
        SetDomainServerCertificateRequest request = new SetDomainServerCertificateRequest();
        request.setDomainName(domainName);
        request.setCertName(certName);
        request.setCertType("cas");
        request.setServerCertificateStatus("on");
        request.setPrivateKey(privateKey);
        client.getAcsResponse(request);
    }
}
