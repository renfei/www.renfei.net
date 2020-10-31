package net.renfei.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.repository.entity.DownloadDO;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.DateUtils;
import net.renfei.service.*;
import net.renfei.service.aliyun.AliyunOSS;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: OtherController</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Controller
@RequestMapping("/other")
public class OtherController extends BaseController {
    private final AliyunOSS aliyunOSS;
    private final CacheService cacheService;

    protected OtherController(RenFeiConfig renFeiConfig,
                              GlobalService globalService,
                              PaginationService paginationService,
                              CommentsService commentsService,
                              AliyunOSS aliyunOSS, CacheService cacheService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.aliyunOSS = aliyunOSS;
        this.cacheService = cacheService;
    }

    /**
     * 链接重定向（注意地址需要Base64编码以后传入）
     *
     * @param url URL需要Base64编码
     * @param mv
     * @return
     */
    @RequestMapping("urlredirect")
    public ModelAndView urlredirect(String url, ModelAndView mv) {
        if (BeanUtils.isEmpty(url)) {
            return new ModelAndView("redirect:/");
        }
        try {
            byte[] asBytes = Base64.getDecoder().decode(url);
            mv.addObject("siteName", renFeiConfig.getSiteName());
            String uri = new String(asBytes, "utf-8");
            if (!uri.startsWith("http://") && !uri.startsWith("https://")) {
                uri = "http://" + uri;
            }
            if (uri.startsWith(renFeiConfig.getDomain())) {
                return new ModelAndView("redirect:" + uri);
            }
            mv.addObject("url", uri);
            setHead(mv, "提示！Notice!");
            mv.setViewName("other/urlredirect");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ModelAndView("redirect:/");
        }
        return mv;
    }

    @GetMapping("qrcode")
    public void qrcode(@RequestParam("content") String content,
                       @RequestParam(value = "size", required = false) String size,
                       HttpServletResponse response) throws Exception {
        ServletOutputStream stream = null;
        QRCodeService qrCodeService;
        if (!ObjectUtils.isEmpty(size)) {
            int qrCodeSize;
            try {
                qrCodeSize = Integer.valueOf(size);
                qrCodeService = new QRCodeService(qrCodeSize);
            } catch (NumberFormatException nfe) {
                qrCodeService = new QRCodeService();
            }
        } else {
            qrCodeService = new QRCodeService();
        }
        try {
            response.setHeader("Content-Type", "image/jpg");
            stream = response.getOutputStream();
            //使用工具类生成二维码
            qrCodeService.encode(content, stream);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    /**
     * 获取极速下载链接
     *
     * @param code 从微信里返回的授权码
     * @return
     */
    @ResponseBody
    @GetMapping("JiSuDownloadLink")
    public APIResult getJiSuDownloadLink(String code) {
        String json = cacheService.get(code);
        if (json != null && json.length() > 0) {
            try {
                DownloadDO downloadDO = JSON.parseObject(json, DownloadDO.class);
                if (downloadDO != null) {
                    String link = "";
                    //有效期6个小时，6时(h)=21600000毫秒(ms)
                    Date expires = new Date(System.currentTimeMillis() + 21600000);
                    if ("https://download.renfei.net".equals(downloadDO.getBucket())) {
                        //阿里云的储存
                        link = aliyunOSS.getPresignedUrl(downloadDO.getFilePath(), expires);
                    }
                    Map<String, String> map = new HashMap<>();
                    map.put("jisulink", link);
                    map.put("expires", DateUtils.formatDate(expires, "yyyy-MM-dd HH:mm:ss"));
                    return new APIResult(map);
                } else {
                    return APIResult.builder()
                            .code(StateCode.Failure)
                            .message("授权码不存在或者已过期，请重新获取")
                            .build();
                }
            } catch (Exception ex) {
                return APIResult.builder()
                        .code(StateCode.Failure)
                        .message("授权码不存在或者已过期，请重新获取")
                        .build();
            }
        } else {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("授权码不存在或者已过期，请重新获取")
                    .build();
        }
    }
}
