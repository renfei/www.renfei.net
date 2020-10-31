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
 * <p>Title: ErrorController</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Controller
@RequestMapping("/error")
public class ErrorController extends BaseController {
    protected ErrorController(RenFeiConfig renFeiConfig,
                              GlobalService globalService,
                              CommentsService commentsService,
                              PaginationService paginationService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
    }

    @RequestMapping("401")
    public ModelAndView error401(ModelAndView mv) {
        mv.addObject("title", "Error 401 (Unauthorized)!! - " + renFeiConfig.getSiteName());
        setHead(mv, "Error 401 (Unauthorized)!!", "error,401,unauthorized");
        mv.setViewName("error/401");
        return mv;
    }

    @RequestMapping("403")
    public ModelAndView error403(ModelAndView mv) {
        mv.addObject("title", "Error 403 (Forbidden)!! - " + renFeiConfig.getSiteName());
        setHead(mv, "Error 403 (Forbidden)!!", "error,403,forbidden");
        mv.setViewName("error/403");
        return mv;
    }

    @RequestMapping("404")
    public ModelAndView error404(ModelAndView mv) {
        mv.addObject("title", "Error 404 (Not Found)!! - " + renFeiConfig.getSiteName());
        setHead(mv, "Error 404 (Not Found)!!", "error,404,not found");
        mv.setViewName("error/404");
        return mv;
    }

    @RequestMapping("405")
    public ModelAndView error405(ModelAndView mv) {
        mv.addObject("title", "Error 405 (Method Not Allowed)!! - " + renFeiConfig.getSiteName());
        setHead(mv, "Error 405 (Method Not Allowed)!!", "error,405,method not allowed");
        mv.setViewName("error/405");
        return mv;
    }

    @RequestMapping("500")
    public ModelAndView error500(ModelAndView mv) {
        mv.addObject("title", "Error 500 (Internal Server Error)!! - " + renFeiConfig.getSiteName());
        setHead(mv, "Error 500 (Internal Server Error)!!", "error,405,Internal Server Error");
        mv.setViewName("error/500");
        return mv;
    }
}
