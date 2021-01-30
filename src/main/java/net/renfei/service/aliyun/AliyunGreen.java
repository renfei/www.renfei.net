package net.renfei.service.aliyun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.green.model.v20180509.TextScanRequest;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;
import net.renfei.config.RenFeiConfig;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Service
public class AliyunGreen extends AliyunService {

    public AliyunGreen(RenFeiConfig renFeiConfig) {
        super(renFeiConfig, DefaultProfile.getProfile(renFeiConfig.getAliyun().getGreen().getRegionId(),
                renFeiConfig.getAliyun().getAccessKeyId(), renFeiConfig.getAliyun().getAccessKeySecret()));
    }

    /**
     * @param text 待检测的文本，长度不超过10000个字符
     */
    public boolean textScan(String text) {
        TextScanRequest textScanRequest = new TextScanRequest();
        // 指定api返回格式
        textScanRequest.setSysAcceptFormat(FormatType.JSON);
        textScanRequest.setHttpContentType(FormatType.JSON);
        // 指定请求方法
        textScanRequest.setSysMethod(com.aliyuncs.http.MethodType.POST);
        textScanRequest.setSysEncoding("UTF-8");
        textScanRequest.setSysRegionId(renFeiConfig.getAliyun().getGreen().getRegionId());
        List<Map<String, Object>> tasks = new ArrayList<Map<String, Object>>();
        Map<String, Object> task1 = new LinkedHashMap<String, Object>();
        task1.put("dataId", UUID.randomUUID().toString());
        /**
         * 待检测的文本，长度不超过10000个字符
         */
        task1.put("content", text);
        tasks.add(task1);
        JSONObject data = new JSONObject();

        /**
         * 检测场景，文本垃圾检测传递：antispam
         **/
        data.put("scenes", Arrays.asList("antispam"));
        data.put("tasks", tasks);
        log.info(JSON.toJSONString(data, true));
        try {
            textScanRequest.setHttpContent(data.toJSONString().getBytes(StandardCharsets.UTF_8), "UTF-8", FormatType.JSON);
            // 请务必设置超时时间
            textScanRequest.setSysConnectTimeout(3000);
            textScanRequest.setSysReadTimeout(6000);
            HttpResponse httpResponse = client.doAction(textScanRequest);
            if (httpResponse.isSuccess()) {
                JSONObject scrResponse = JSON.parseObject(new String(httpResponse.getHttpContent(), StandardCharsets.UTF_8));
                if (200 == scrResponse.getInteger("code")) {
                    JSONArray taskResults = scrResponse.getJSONArray("data");
                    for (Object taskResult : taskResults) {
                        if (200 == ((JSONObject) taskResult).getInteger("code")) {
                            JSONArray sceneResults = ((JSONObject) taskResult).getJSONArray("results");
                            for (Object sceneResult : sceneResults) {
                                String scene = ((JSONObject) sceneResult).getString("scene");
                                String suggestion = ((JSONObject) sceneResult).getString("suggestion");
                                //根据scene和suggetion做相关处理
                                //suggestion == pass 未命中垃圾  suggestion == block 命中了垃圾，可以通过label字段查看命中的垃圾分类
//                                log.info("args = [" + scene + "]");
//                                log.info("args = [" + suggestion + "]");
                                return "pass".equals(suggestion.toLowerCase());
                            }
                        } else {
                            log.info("task process fail:" + ((JSONObject) taskResult).getInteger("code"));
                            return false;
                        }
                    }
                } else {
                    log.info("detect not success. code:" + scrResponse.getInteger("code"));
                    return false;
                }
            } else {
                log.info("response not success. status:" + httpResponse.getStatus());
                return false;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return false;
    }
}
