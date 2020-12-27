package net.renfei.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseService;
import net.renfei.config.RenFeiConfig;
import net.renfei.sdk.http.HttpRequest;
import net.renfei.sdk.http.HttpResult;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.HttpUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * <p>Title: ReCaptchaService</p>
 * <p>Description: reCAPTCHA服务</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Service
public class ReCaptchaService extends BaseService {
    private final RenFeiConfig renFeiConfig;

    public ReCaptchaService(RenFeiConfig renFeiConfig) {
        this.renFeiConfig = renFeiConfig;
    }

    /**
     * 验证 reCAPTCHA
     *
     * @param token    客户端提交的 token
     * @param remoteIp 客户端IP
     * @return
     */
    public boolean verifying(String token, String remoteIp) {
        HttpRequest request = HttpRequest.create().url("https://www.recaptcha.net/recaptcha/api/siteverify?secret="
                + renFeiConfig.getGoogle().getReCAPTCHA().getServerKey()
                + "&response="
                + token).useSSL();
        if (!BeanUtils.isEmpty(remoteIp)) {
            request.addParam("remoteip", remoteIp);
        }
        try {
            HttpResult result = HttpUtils.get(request);
            if (result.getCode() != 200) {
                return true;
            }
            JSONObject jsonObject = JSON.parseObject(result.getResponseText());
            if (!jsonObject.getBoolean("success")) {
                return false;
            } else {
                float score = jsonObject.getFloat("score");
                // 得分大于0.6就放行
                return score > 0.6;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }
}
