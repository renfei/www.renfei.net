package net.renfei.controller;

import lombok.extern.slf4j.Slf4j;
import net.renfei.annotation.SystemLog;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.*;
import net.renfei.repository.entity.ShortUrl;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.utils.IpUtils;
import net.renfei.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static net.renfei.service.KitBoxService.*;

/**
 * <p>Title: KitboxController</p>
 * <p>Description: 工具箱</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Controller
@RequestMapping("/kitbox")
public class KitBoxController extends BaseController {
    private final IpService ipService;
    private final RenFeiConfig renFeiConfig;
    private final KitBoxService kitBoxService;
    private final ShortUrlService shortUrlService;

    protected KitBoxController(RenFeiConfig renFeiConfig,
                               GlobalService globalService,
                               IpService ipService,
                               KitBoxService kitBoxService,
                               CommentsService commentsService,
                               ShortUrlService shortUrlService,
                               PaginationService paginationService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.renFeiConfig = renFeiConfig;
        this.ipService = ipService;
        this.kitBoxService = kitBoxService;
        this.shortUrlService = shortUrlService;
    }

    @RequestMapping("")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取开发者工具箱页面")
    public ModelAndView kitbox(ModelAndView mv) {
        mv.addObject("title", "开发者工具箱 - " + renFeiConfig.getSiteName());
        setHead(mv, "免费的开发者与站长工具箱小工具，包含网络工具、加解密工具、测试工具等，工欲善其事，必先利其器。",
                "开发,开发者,工具,工具箱");
        mv.addObject("KitBoxMenus", kitBoxService.getKitBoxMenus());
        mv.setViewName("kitbox/index");
        return mv;
    }

    @RequestMapping("ip")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取IP地址信息查询工具页面")
    public ModelAndView ip(ModelAndView mv) {
        String ip = IpUtils.getIpAddress(request);
        IpInfoDTO ipInfoDTO = ipService.query(ip);
        mv.addObject("myip", ip);
        mv.addObject("ipdto", ipInfoDTO);
        mv.addObject("title", "IP地址信息查询工具 - 开发者工具箱 - " + renFeiConfig.getSiteName());
        setHead(mv, "IP地址信息查询工具，开放服务接口实现IP信息查询，查询IP地址所属地理位置",
                "IP,地址,信息,查询,工具,地理,位置");
        mv.setViewName("kitbox/ipinfo");
        setKitBoxMenus(mv, NETWORK_TOOL);
        return mv;
    }

    @RequestMapping("digtrace")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取域名解析信息查询工具页面")
    public ModelAndView getDigTrace(ModelAndView mv) {
        setHead(mv, "域名解析信息查询工具，开放服务接口实现dig+trace域名解析过程查询",
                "域名,解析,信息,查询,工具,Dig,trace,DNS");
        mv.addObject("title", "在线Dig+trace命令检测DNS状态工具 - 开发者工具箱 - " + renFeiConfig.getSiteName());
        mv.setViewName("kitbox/digtrace");
        setKitBoxMenus(mv, NETWORK_TOOL);
        return mv;
    }

    @RequestMapping("whois")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取域名Whois信息查询工具页面")
    public ModelAndView getWhois(ModelAndView mv) {
        setHead(mv, "域名Whois信息查询工具，查询域名是否已经被注册，以及注册域名的详细信息的数据库（如域名所有人、域名注册商、域名注册日期和过期日期等）。通过域名Whois服务器查询，可以查询域名归属者联系方式，以及注册和到期时间。",
                "域名,whois,信息,查询,工具");
        mv.addObject("title", "域名Whois信息查询工具 - 开发者工具箱 - " + renFeiConfig.getSiteName());
        mv.setViewName("kitbox/whois");
        setKitBoxMenus(mv, NETWORK_TOOL);
        return mv;
    }

    @RequestMapping("getmyip")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取公网IP获取工具页面")
    public ModelAndView getmyip(ModelAndView mv) {
        setHead(mv, "公网IP获取工具支持Linux、Windows、API",
                "IP,公网,出口,地址,工具");
        mv.addObject("title", "公网IP获取工具 - 开发者工具箱 - " + renFeiConfig.getSiteName());
        mv.setViewName("kitbox/getmyip");
        setKitBoxMenus(mv, NETWORK_TOOL);
        return mv;
    }

    @RequestMapping({"uuid", "guid", "UUID", "GUID"})
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取在线批量生成 UUID/GUID 工具页面")
    public ModelAndView getUUID(ModelAndView mv) {
        setHead(mv, "在线批量生成 UUID/GUID 工具",
                "UUID,GUID,在线,批量,生成,工具");
        mv.addObject("title", "UUID/GUID 在线批量生成工具 - 开发者工具箱 - " + renFeiConfig.getSiteName());
        mv.setViewName("kitbox/uuid");
        setKitBoxMenus(mv, DEVELOPMENT_TOOL);
        return mv;
    }

    @RequestMapping({"freemarkerTest", "FtlTest"})
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取FreeMarker(FTL)在线测试工具页面")
    public ModelAndView freemarkerTest(ModelAndView mv) {
        setHead(mv, "FreeMarker(FTL)在线测试工具",
                "FreeMarker,ftl,在线,测试,工具");
        mv.addObject("title", "FreeMarker(FTL)在线测试工具 - 开发者工具箱 - " + renFeiConfig.getSiteName());
        setKitBoxMenus(mv, DEVELOPMENT_TOOL);
        mv.setViewName("kitbox/freemarkerTest");
        return mv;
    }

    @RequestMapping("ueditor")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取 UEditor 富文本在线编辑器页面")
    public ModelAndView ueditor(ModelAndView mv) {
        mv.addObject("title", "UEditor 富文本在线编辑器 - 开发者工具箱 - " + renFeiConfig.getSiteName());
        setHead(mv, "UEditor是由百度web前端研发部开发所见即所得富文本web编辑器，具有轻量，可定制，注重用户体验等特点，开源基于MIT协议，允许自由使用和修改代码。",
                "ueditor,百度,在线,测试,demo,富文本,编辑器");
        mv.setViewName("kitbox/ueditor");
        setKitBoxMenus(mv, DEVELOPMENT_TOOL);
        return mv;
    }

    @RequestMapping("md5")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取MD5在线加密工具页面")
    public ModelAndView md5Tools(ModelAndView mv) {
        mv.addObject("title", "MD5在线加密工具 - 开发者工具箱 - " + renFeiConfig.getSiteName());
        setHead(mv, "MD5在线加密工具，对字符串进行MD5计算得出MD5加密字符串",
                "MD5,在线,加密,解密,字符串");
        mv.setViewName("kitbox/md5");
        setKitBoxMenus(mv, ENCRYPTION_TOOL);
        return mv;
    }

    @RequestMapping("sha1")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取SHA-1在线加密工具页面")
    public ModelAndView sha1Tools(ModelAndView mv) {
        mv.addObject("title", "SHA-1在线加密工具 - 开发者工具箱 - " + renFeiConfig.getSiteName());
        setHead(mv, "SHA-1 散列函数加密算法输出的散列值为40位十六进制数字串，可用于验证信息的一致性，防止被篡改。本页面的 SHA-1 在线加密工具可对字符串进行 SHA-1 加密，并可转换散列值中字母的大小写。",
                "SHA-1,在线,加密,解密,字符串");
        mv.setViewName("kitbox/sha1");
        setKitBoxMenus(mv, ENCRYPTION_TOOL);
        return mv;
    }

    @RequestMapping("sha256")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取SHA-256在线加密工具页面")
    public ModelAndView sha256Tools(ModelAndView mv) {
        mv.addObject("title", "SHA-256在线加密工具 - 开发者工具箱 - " + renFeiConfig.getSiteName());
        setHead(mv, "SHA-256 散列函数加密算法输出的散列值可用于验证信息的一致性，防止被篡改。本页面的 SHA-256 在线加密工具可对字符串进行 SHA-256 加密，并可转换散列值中字母的大小写。",
                "SHA-256,在线,加密,解密,字符串");
        mv.setViewName("kitbox/sha256");
        setKitBoxMenus(mv, ENCRYPTION_TOOL);
        return mv;
    }

    @RequestMapping("sha512")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取SHA-512在线加密工具页面")
    public ModelAndView sha512Tools(ModelAndView mv) {
        mv.addObject("title", "SHA-512在线加密工具 - 开发者工具箱 - " + renFeiConfig.getSiteName());
        setHead(mv, "SHA-512 散列函数加密算法输出的散列值可用于验证信息的一致性，防止被篡改。本页面的 SHA-512 在线加密工具可对字符串进行 SHA-512 加密，并可转换散列值中字母的大小写。",
                "SHA-512,在线,加密,解密,字符串");
        mv.setViewName("kitbox/sha512");
        setKitBoxMenus(mv, ENCRYPTION_TOOL);
        return mv;
    }

    @RequestMapping("url16")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取URL网址16进制加密工具页面")
    public ModelAndView url16Tools(ModelAndView mv) {
        mv.addObject("title", "URL网址16进制加密工具 - 开发者工具箱 - " + renFeiConfig.getSiteName());
        setHead(mv, "RL编码形式表示的ASCII字符(十六进制格式)。把URL网址转换成16进制代码形式,加密后可直接复制到地址栏访问。",
                "URL,网址,加密,16进制,hex");
        mv.setViewName("kitbox/url16");
        setKitBoxMenus(mv, ENCRYPTION_TOOL);
        return mv;
    }

    @RequestMapping({"qrcode", "QRCode"})
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取二维码在线生成工具页面")
    public ModelAndView qrCode(ModelAndView mv) {
        mv.addObject("title", "二维码在线生成工具 - 开发者工具箱 - " + renFeiConfig.getSiteName());
        setHead(mv, "二维码在线生成工具",
                "二维码,qrcode,在线,生成,工具");
        mv.setViewName("kitbox/qrcode");
        setKitBoxMenus(mv, OTHER_TOOL);
        return mv;
    }

    @RequestMapping({"ShortUrl", "ShortURL"})
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取短网址在线生成工具页面")
    public ModelAndView shortUrl(ModelAndView mv) {
        mv.addObject("title", "短网址在线生成工具 - 开发者工具箱 - " + renFeiConfig.getSiteName());
        setHead(mv, "短网址在线生成工具",
                "短网址,Short,Url,生成,工具");
        mv.setViewName("kitbox/ShortURL");
        setKitBoxMenus(mv, OTHER_TOOL);
        return mv;
    }

    @PostMapping("ShortURL/do")
    @ResponseBody
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.OPENAPI, logType = LogType.ADD, logDesc = "创建一个短网址")
    public APIResult<ShortUrlVO> setShortUrl(String url) {
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(url)) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("Url不合法")
                    .build();
        }
        if (url.startsWith("http://") || url.startsWith("https://")) {
            try {
                ShortUrl shortUrl = shortUrlService.createShortUrl(url, null);
                if (shortUrl != null) {
                    ShortUrlVO shortUrlVO = new ShortUrlVO();
                    BeanUtils.copyProperties(shortUrl, shortUrlVO);
                    shortUrlVO.setShortUrl(ShortUrlVO.BASE_DOMAIN + shortUrl.getShortUrl());
                    return new APIResult(shortUrlVO);
                } else {
                    return APIResult.builder()
                            .code(StateCode.Error)
                            .message("内部服务错误")
                            .build();
                }
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
                return APIResult.builder()
                        .code(StateCode.Error)
                        .message("内部服务错误")
                        .build();
            }

        } else {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("Url不合法")
                    .build();
        }
    }

    @RequestMapping("ShortURL/do/{url}")
    @ResponseBody
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.OPENAPI, logType = LogType.GET, logDesc = "查询一个短网址")
    public APIResult<ShortUrlVO> getShortUrl(@PathVariable("url") String url) {
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(url)) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("Url不合法")
                    .build();
        }
        try {
            ShortUrl shortUrl = shortUrlService.getShortUrl(url);
            if (shortUrl != null) {
                ShortUrlVO shortUrlVO = new ShortUrlVO();
                BeanUtils.copyProperties(shortUrl, shortUrlVO);
                shortUrlVO.setShortUrl(shortUrl.getShortUrl());
                return new APIResult(shortUrlVO);
            } else {
                return APIResult.builder()
                        .code(StateCode.Failure)
                        .message("短网址不存在")
                        .build();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return APIResult.builder()
                    .code(StateCode.Error)
                    .message("内部服务错误")
                    .build();

        }
    }

    @ResponseBody
    @RequestMapping("ueditor/controller")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.OPENAPI, logType = LogType.ADD, logDesc = "获取ueditor配置信息")
    public String ueditorConfig(@RequestParam("action") String action) {
        if ("config".equals(action)) {
            return "{" +
                    "    \"imageActionName\": \"uploadimage\", " +
                    "    \"imageFieldName\": \"upfile\", " +
                    "    \"imageMaxSize\": 2048000, " +
                    "    \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], " +
                    "    \"imageCompressEnable\": true, " +
                    "    \"imageCompressBorder\": 1600, " +
                    "    \"imageInsertAlign\": \"none\", " +
                    "    \"imageUrlPrefix\": \"\", " +
                    "    \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"scrawlActionName\": \"uploadscrawl\", " +
                    "    \"scrawlFieldName\": \"upfile\", " +
                    "    \"scrawlPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"scrawlMaxSize\": 2048000, " +
                    "    \"scrawlUrlPrefix\": \"\", " +
                    "    \"scrawlInsertAlign\": \"none\"," +
                    "    \"snapscreenActionName\": \"uploadimage\", " +
                    "    \"snapscreenPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"snapscreenUrlPrefix\": \"\", " +
                    "    \"snapscreenInsertAlign\": \"none\", " +
                    "    \"catcherLocalDomain\": [\"127.0.0.1\", \"localhost\", \"img.baidu.com\"]," +
                    "    \"catcherActionName\": \"catchimage\", " +
                    "    \"catcherFieldName\": \"source\", " +
                    "    \"catcherPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"catcherUrlPrefix\": \"\", " +
                    "    \"catcherMaxSize\": 2048000, " +
                    "    \"catcherAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], " +
                    "    \"videoActionName\": \"uploadvideo\", " +
                    "    \"videoFieldName\": \"upfile\", " +
                    "    \"videoPathFormat\": \"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"videoUrlPrefix\": \"\", " +
                    "    \"videoMaxSize\": 102400000, " +
                    "    \"videoAllowFiles\": [" +
                    "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\"," +
                    "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"], " +
                    "    \"fileActionName\": \"uploadfile\", " +
                    "    \"fileFieldName\": \"upfile\", " +
                    "    \"filePathFormat\": \"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"fileUrlPrefix\": \"\", " +
                    "    \"fileMaxSize\": 51200000, " +
                    "    \"fileAllowFiles\": [" +
                    "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"," +
                    "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\"," +
                    "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"," +
                    "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\"," +
                    "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"" +
                    "    ], " +
                    "    \"imageManagerActionName\": \"listimage\", " +
                    "    \"imageManagerListPath\": \"/ueditor/jsp/upload/image/\", " +
                    "    \"imageManagerListSize\": 20, " +
                    "    \"imageManagerUrlPrefix\": \"\", " +
                    "    \"imageManagerInsertAlign\": \"none\", " +
                    "    \"imageManagerAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], " +
                    "    \"fileManagerActionName\": \"listfile\", " +
                    "    \"fileManagerListPath\": \"/ueditor/jsp/upload/file/\", " +
                    "    \"fileManagerUrlPrefix\": \"\", " +
                    "    \"fileManagerListSize\": 20, " +
                    "    \"fileManagerAllowFiles\": [" +
                    "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"," +
                    "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\"," +
                    "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"," +
                    "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\"," +
                    "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"" +
                    "    ] " +
                    "}";
        }
        return "Unsupported action. -By RenFei.Net";
    }

    private void setKitBoxMenus(ModelAndView mv, String key) {
        mv.addObject("KitBoxMenus", kitBoxService.getKitBoxMenus(key));
    }
}
