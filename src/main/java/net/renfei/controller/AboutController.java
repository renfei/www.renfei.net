package net.renfei.controller;

import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.service.CommentsService;
import net.renfei.service.GlobalService;
import net.renfei.service.PaginationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>Title: AboutController</p>
 * <p>Description: 关于</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Controller
@RequestMapping("/about")
public class AboutController extends BaseController {
    private final RenFeiConfig renFeiConfig;
    private final PaginationService paginationService;

    protected AboutController(GlobalService globalService,
                              RenFeiConfig renFeiConfig,
                              CommentsService commentsService,
                              PaginationService paginationService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.renFeiConfig = renFeiConfig;
        this.paginationService = paginationService;
    }

    @RequestMapping("")
    public ModelAndView getAbout(ModelAndView mv) {
        mv.addObject("title", "关于任霏 - " + renFeiConfig.getSiteName());
        setHead(mv, "任霏，软件开发工程师。一只90后程序猿，大学是 C#.NET 专业毕业，后自学 Java 进行技术转型。你现在看到的这个网站就是我从后端到前端完整的手写的。",
                "任霏,renfei,关于");
        mv.setViewName("about");
        return mv;
    }
}
