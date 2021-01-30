package net.renfei.service.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.cas.model.v20180713.CreateUserCertificateRequest;
import com.aliyuncs.cas.model.v20180713.CreateUserCertificateResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import net.renfei.config.RenFeiConfig;
import org.springframework.stereotype.Service;

/**
 * <p>Title: AliyunCAS</p>
 * <p>Description: 阿里云证书服务</p>
 *
 * @author RenFei(i @ renfei.net)
 * @date : 2021-01-30 15:24
 */
@Slf4j
@Service
public class AliyunCAS extends AliyunService {

    public AliyunCAS(RenFeiConfig renFeiConfig) {
        super(renFeiConfig,
                DefaultProfile.getProfile("cn-hangzhou",
                        renFeiConfig.getAliyun().getAccessKeyId(),
                        renFeiConfig.getAliyun().getAccessKeySecret()));
    }

    /**
     * 添加证书
     *
     * @param certName 自定义的证书名称:同一个用户下的证书名称不能重复。
     * @param cert     指定PEM格式的证书内容:指定PEM格式的证书内容
     * @param certKey  指定PEM格式证书的私钥内容:指定PEM格式证书的私钥内容
     */
    public void createUserCertificate(String certName, String cert, String certKey) {
        CreateUserCertificateRequest request = new CreateUserCertificateRequest();
        request.setName(certName);
        request.setCert(cert);
        request.setKey(certKey);
        try {
            CreateUserCertificateResponse response = client.getAcsResponse(request);
            log.info(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            log.info("ErrCode:{}", e.getErrCode());
            log.info("ErrMsg:{}", e.getErrMsg());
            log.info("RequestId:{}", e.getRequestId());
            log.error(e.getMessage());
        }
    }
}
