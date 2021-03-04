package net.renfei.controller;

import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.repository.entity.OnlineDocumentsDO;
import net.renfei.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * <p>Title: DocsController</p>
 * <p>Description: 在线文档</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Controller
@RequestMapping("/docs")
public class DocsController extends BaseController {
    private final DocsService docsService;
    private final RenFeiConfig renFeiConfig;

    protected DocsController(RenFeiConfig renFeiConfig,
                             GlobalService globalService,
                             CommentsService commentsService,
                             PaginationService paginationService,
                             DocsService docsService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.renFeiConfig = renFeiConfig;
        this.docsService = docsService;
    }

    @RequestMapping("")
    public ModelAndView getDocs(ModelAndView mv) {
        mv.addObject("title", "在线文档 - " + renFeiConfig.getSiteName());
        setHead(mv, "提供在线开发文档与手册服务，部分中文文档基于 Google Translation 机器翻译，请结合原版阅读。",
                "开发,文档,手册,在线");
        mv.addObject("KitBoxMenus", docsService.getDocs());
        mv.setViewName("docs/index");
        return mv;
    }

    @RequestMapping("{category}/{title}/{version}/{lang}")
    public ModelAndView getEmbed(ModelAndView mv,
                                 @PathVariable("category") String category,
                                 @PathVariable("title") String title,
                                 @PathVariable("version") String version,
                                 @PathVariable("lang") String lang) throws NoHandlerFoundException {
        OnlineDocumentsDO onlineDocumentsDO = docsService.getEmbed(category, title, version, lang);
        if (onlineDocumentsDO == null) {
            throwNoHandlerFoundException();
        }
        assert onlineDocumentsDO != null;
        mv.addObject("title", onlineDocumentsDO.getTitle() + " (" + onlineDocumentsDO.getVersion() + ") - 在线文档 - " + renFeiConfig.getSiteName());
        setHead(mv, onlineDocumentsDO.getDescribe(), "开发,文档,手册,在线");
        mv.addObject("onlineDocuments", onlineDocumentsDO);
        mv.setViewName("docs/embed");
        return mv;
    }
}
