package net.renfei.controller;

import lombok.extern.slf4j.Slf4j;
import net.renfei.annotation.SystemLog;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.*;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.List;

/**
 * <p>Title: SearchController</p>
 * <p>Description: 站内搜索</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Controller
@RequestMapping("/search")
public class SearchController extends BaseController {
    private final LogService logService;
    private final SearchService searchService;

    protected SearchController(RenFeiConfig renFeiConfig,
                               GlobalService globalService,
                               SearchService searchService,
                               CommentsService commentsService,
                               PaginationService paginationService,
                               LogService logService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.searchService = searchService;
        this.logService = logService;
    }

    @RequestMapping("")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取站内搜索页面")
    public ModelAndView search(ModelAndView mv,
                               @RequestParam(value = "type", required = false) String type,
                               @RequestParam(value = "w", required = false) String query,
                               @RequestParam(value = "p", required = false) String page) {
        if (BeanUtils.isEmpty(query)) {
            mv.addObject("title", "站内搜索 - " + renFeiConfig.getSiteName());
        } else {
            if (BeanUtils.isEmpty(type)) {
                type = "all";
            }
            Long startTime = System.nanoTime();
            TypeEnum typeEnum = null;
            switch (type.toLowerCase().trim()) {
                case "post":
                    typeEnum = TypeEnum.POSTS;
                    break;
                case "page":
                    typeEnum = TypeEnum.PAGES;
                    break;
                case "video":
                    typeEnum = TypeEnum.VIDEO;
                    break;
                case "photo":
                    typeEnum = TypeEnum.PHOTO;
                    break;
                case "weibo":
                    typeEnum = TypeEnum.WEIBO;
                    break;
                case "kitbox":
                    typeEnum = TypeEnum.KITBOX;
                    break;
                default:
                    type = "all";
                    break;
            }
            ListData<SearchItem> searchItemListData = searchService.search(typeEnum, query, page, "10");
            mv.addObject("queryTitle", "搜索：" + query);
            mv.addObject("query", query);
            mv.addObject("item", searchItemListData.getData());
            mv.addObject("total", searchItemListData.getTotal());
            mv.addObject("type", type.toLowerCase().trim());
            mv.addObject("title", "搜索：" + query + " - 站内搜索 - " + renFeiConfig.getSiteName());
            Long endTime = System.nanoTime();
            double timed = (endTime - startTime) / 1000000000D;
            DecimalFormat df = new DecimalFormat("######0.000000");
            mv.addObject("searchTime", df.format(timed));
            setPagination(mv, page, searchItemListData.getTotal(), "/search?type=" + type + "&w=" + query + "&p=");
            mv.addObject("hotSearchList",searchService.getHotSearchList());
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            //设置子线程共享
            RequestContextHolder.setRequestAttributes(servletRequestAttributes, true);
            assert servletRequestAttributes != null;
            HttpServletRequest request = servletRequestAttributes.getRequest();
            logService.log(LogLevel.INFO, LogModule.SEARCH, LogType.GET, query, request);
        }
        mv.setViewName("search");
        return mv;
    }

    @RequestMapping(value = "search.xml")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取sitemap.xml")
    public ModelAndView getSearchXml(ModelAndView mv, HttpServletResponse response) {
        mv.addObject("siteName", renFeiConfig.getSiteName());
        mv.addObject("domain", renFeiConfig.getDomain());
        response.setHeader("content-type", "application/xml;charset=UTF-8");
        response.setContentType("application/xml;charset=UTF-8");
        mv.setViewName("searchxml");
        return mv;
    }
}
