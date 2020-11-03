package net.renfei.controller;

import net.renfei.annotation.SystemLog;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.LogLevel;
import net.renfei.entity.LogModule;
import net.renfei.entity.LogType;
import net.renfei.service.CommentsService;
import net.renfei.service.GlobalService;
import net.renfei.service.PaginationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * <p>Title: RewriteController</p>
 * <p>Description: 对旧地址进行重定向</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Controller
public class RewriteController extends BaseController {
    protected RewriteController(RenFeiConfig renFeiConfig,
                                GlobalService globalService,
                                CommentsService commentsService,
                                PaginationService paginationService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
    }

    @RequestMapping("/article/{path}")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取旧地址进行重定向页面")
    public ModelAndView getOldArticle(@PathVariable("path") String path) {
        RedirectView red = new RedirectView("/posts/" + path, true);
        red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return new ModelAndView(red);
    }

    @RequestMapping("/toolkit/{path}")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取旧地址进行重定向页面")
    public ModelAndView getOldToolkit(@PathVariable("path") String path) {
        RedirectView red = new RedirectView("/kitbox/" + path, true);
        red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return new ModelAndView(red);
    }

    @RequestMapping("/toolkit/{path1}/{path2}")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取旧地址进行重定向页面")
    public ModelAndView getOldToolkit(@PathVariable("path1") String path1, @PathVariable("path2") String path2) {
        RedirectView red = new RedirectView("/kitbox/" + path1 + "/" + path2, true);
        red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return new ModelAndView(red);
    }
}
