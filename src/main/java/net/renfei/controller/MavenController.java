package net.renfei.controller;

import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.service.CommentsService;
import net.renfei.service.GlobalService;
import net.renfei.service.PaginationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>Title: MavenController</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Controller
@RequestMapping("/maven")
public class MavenController extends BaseController {
    private final RenFeiConfig renFeiConfig;

    protected MavenController(GlobalService globalService,
                              RenFeiConfig renFeiConfig,
                              CommentsService commentsService,
                              PaginationService paginationService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.renFeiConfig = renFeiConfig;
    }

    @RequestMapping("")
    public ModelAndView kitbox(ModelAndView mv) {
        mv.addObject("title", "Maven公共代理仓库 - " + renFeiConfig.getSiteName());
        mv.setViewName("maven");
        return mv;
    }
}
