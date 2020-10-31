package net.renfei.service;

import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseService;
import net.renfei.config.RenFeiConfig;
import net.renfei.sdk.entity.WeChatMsg;
import net.renfei.sdk.entity.WeChatMsgType;
import net.renfei.sdk.entity.WeChatResponseTextMsg;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.WeChatUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: WeChatService</p>
 * <p>Description: 微信服务</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Service
public class WeChatService extends BaseService {
    private final RenFeiConfig renFeiConfig;
    private final WeChatKeywordService weChatKeywordService;

    public WeChatService(RenFeiConfig renFeiConfig,
                         WeChatKeywordService weChatKeywordService) {
        this.renFeiConfig = renFeiConfig;
        this.weChatKeywordService = weChatKeywordService;
    }

    public String weChatMessageHandelCoreService(HttpServletRequest request) {
        WeChatUtils weChatUtils = new WeChatUtils();
        try {
            // 用户发来的消息
            WeChatMsg requestMsg = new WeChatMsg(request);
            // 默认回复文本消息
            WeChatResponseTextMsg responseTextMsg = new WeChatResponseTextMsg(requestMsg);
            //验证消息来自微信官方
            if (!weChatUtils.checkWeChat(request, renFeiConfig.getWeChat().getToken())) {
                responseTextMsg.setContent("您的消息似乎不是来自微信官方服务器，所以未被处理。");
                return weChatUtils.textMessageToXml(responseTextMsg);
            }
            switch (requestMsg.getMsgType()) {
                case TEXT:
                case VOICE:
                    return textMessageProcessor(requestMsg);
                case IMAGE:
                    responseTextMsg.setContent("我们已经收到您发送的图片：" + requestMsg.getPicUrl() + "，但「RENFEI.NET」暂时还无法理解其中的含义，请您尝试发送文本消息。");
                    return weChatUtils.textMessageToXml(responseTextMsg);
                case VIDEO:
                    responseTextMsg.setContent("我们已经收到您发送的视频消息，但「RENFEI.NET」暂时还无法理解其中的含义，请您尝试发送文本消息。");
                    requestMsg.getThumbMediaId();
                    return weChatUtils.textMessageToXml(responseTextMsg);
                case LOCATION:
                    requestMsg.getLocation_X();
                    requestMsg.getLocation_Y();
                    responseTextMsg.setContent("我们已经收到您发送的位置信息：" + requestMsg.getLabel() + "，但「RENFEI.NET」暂时还无法理解其中的含义，请您尝试发送文本消息。");
                    return weChatUtils.textMessageToXml(responseTextMsg);
                case LINK:
                    responseTextMsg.setContent("我们已经收到您发送的网页信息：" + requestMsg.getUrl() + "，但「RENFEI.NET」暂时还无法理解其中的含义，请您尝试发送文本消息。");
                    return weChatUtils.textMessageToXml(responseTextMsg);
                case EVENT:
                    switch (requestMsg.getEventEnum()) {
                        case SUBSCRIBE:
                            break;
                        case UNSUBSCRIBE:
                            break;
                        default:
                            break;
                    }
                    break;
                case SHORTVIDEO:
                case MUSIC:
                case NEWS:
                default:
                    responseTextMsg.setContent("您的消息我们已经收到，但「RENFEI.NET」暂时无法处理您的消息类型。");
                    return weChatUtils.textMessageToXml(responseTextMsg);
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return null;
    }

    /**
     * 文字类处理
     *
     * @param requestMsg
     * @return
     */
    private String textMessageProcessor(WeChatMsg requestMsg) {
        WeChatUtils weChatUtils = new WeChatUtils();
        WeChatResponseTextMsg responseTextMsg = new WeChatResponseTextMsg(requestMsg);
        String conetent = "";
        if(requestMsg.getMsgType().equals(WeChatMsgType.TEXT)){
            conetent = requestMsg.getContent();
        }else{
            conetent = requestMsg.getRecognition();
        }
        if (BeanUtils.isEmpty(conetent)) {
            responseTextMsg.setContent("我们已经收到您发送的消息，但「RENFEI.NET」暂时还无法理解其中的含义，请您尝试发送文本消息。");
            return weChatUtils.textMessageToXml(responseTextMsg);
        } else if ("【收到不支持的消息类型，暂无法显示】".equals(conetent)) {
            responseTextMsg.setContent("我们已经收到您发送的消息，但「RENFEI.NET」暂时还无法理解其中的含义，请您尝试发送文本消息。");
            return weChatUtils.textMessageToXml(responseTextMsg);
        } else {
            return weChatKeywordService.talk(requestMsg);
        }
    }
}
