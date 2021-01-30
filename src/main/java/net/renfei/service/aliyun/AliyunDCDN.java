package net.renfei.service.aliyun;

import com.aliyuncs.dcdn.model.v20180115.SetDcdnDomainCertificateRequest;
import com.aliyuncs.dcdn.model.v20180115.SetDcdnDomainCertificateResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
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

    public void setDcdnDomainCertificate(String domainName, String certName) {
        SetDcdnDomainCertificateRequest request = new SetDcdnDomainCertificateRequest();
        request.setDomainName(domainName);
        request.setCertName(certName);
        request.setCertType("cas");
        request.setSSLProtocol("on");

        try {
            SetDcdnDomainCertificateResponse response = client.getAcsResponse(request);
            log.info(new Gson().toJson(response));
        } catch (ServerException e) {
            log.error(e.getMessage(), e);
        } catch (ClientException e) {
            log.info("ErrCode:{}", e.getErrCode());
            log.info("ErrMsg:{}", e.getErrMsg());
            log.info("RequestId:{}", e.getRequestId());
            log.error(e.getMessage(), e);
        }
    }
}
