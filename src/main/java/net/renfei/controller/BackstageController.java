package net.renfei.controller;

import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.service.CommentsService;
import net.renfei.service.GlobalService;
import net.renfei.service.PaginationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>Title: BackstageController</p>
 * <p>Description: 网站后台管理界面</p>
 *
 * @author RenFei(i @ renfei.net)
 * @date : 2021-01-09 22:25
 */
@Controller
@RequestMapping("/backstage")
@PreAuthorize("isAuthenticated()")
public class BackstageController extends BaseController {
    protected BackstageController(RenFeiConfig renFeiConfig,
                                  GlobalService globalService,
                                  CommentsService commentsService,
                                  PaginationService paginationService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
    }

    @GetMapping("")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("backstage/index");
        return mv;
    }
}
