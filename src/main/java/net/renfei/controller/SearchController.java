package net.renfei.controller;

import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.ListData;
import net.renfei.entity.SearchItem;
import net.renfei.entity.TypeEnum;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.service.CommentsService;
import net.renfei.service.GlobalService;
import net.renfei.service.PaginationService;
import net.renfei.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;

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
    private final SearchService searchService;

    protected SearchController(RenFeiConfig renFeiConfig,
                               GlobalService globalService,
                               SearchService searchService,
                               CommentsService commentsService,
                               PaginationService paginationService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.searchService = searchService;
    }

    @RequestMapping("")
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
        }
        mv.setViewName("search");
        return mv;
    }
}
