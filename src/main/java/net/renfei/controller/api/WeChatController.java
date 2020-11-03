package net.renfei.controller.api;

import lombok.extern.slf4j.Slf4j;
import net.renfei.annotation.SystemLog;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.LogLevel;
import net.renfei.entity.LogModule;
import net.renfei.entity.LogType;
import net.renfei.sdk.utils.WeChatUtils;
import net.renfei.service.CommentsService;
import net.renfei.service.GlobalService;
import net.renfei.service.PaginationService;
import net.renfei.service.WeChatService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * <p>Title: WeChatController</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@ApiIgnore
@RestController
@RequestMapping("/api/wechat")
public class WeChatController extends BaseController {
    private final WeChatService weChatService;

    protected WeChatController(RenFeiConfig renFeiConfig,
                               GlobalService globalService,
                               CommentsService commentsService,
                               PaginationService paginationService,
                               WeChatService weChatService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.weChatService = weChatService;
    }

    /**
     * 验证消息是否来自微信官方服务器
     *
     * @param signature 微信加密签名
     * @param echostr   随机字符串
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @return
     */
    @GetMapping("access")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.WECHAT, logType = LogType.GET, logDesc = "验证消息是否来自微信官方服务器")
    public String checkWxMsg(@RequestParam("signature") String signature,
                             @RequestParam("timestamp") String timestamp,
                             @RequestParam("nonce") String nonce,
                             @RequestParam("echostr") String echostr) {
        WeChatUtils weChatUtils = new WeChatUtils();
        //判断加密后的字符串是否与微信的签名一致
        if (weChatUtils.checkWeChat(signature, echostr, timestamp, nonce, renFeiConfig.getWeChat().getToken())) {
            return echostr;
        } else {
            log.warn("微信消息验证失败！");
            return null;
        }
    }

    @PostMapping("access")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.WECHAT, logType = LogType.GET, logDesc = "来自微信公众号的消息")
    public String getWeiXinMessage(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        // 微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
        request.setCharacterEncoding("UTF-8");
        // 在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
        response.setCharacterEncoding("UTF-8");
        return weChatService.weChatMessageHandelCoreService(request);
    }
}
