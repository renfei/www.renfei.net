package net.renfei.controller;

import net.renfei.annotation.SystemLog;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.LogLevel;
import net.renfei.entity.LogModule;
import net.renfei.entity.LogType;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.service.CommentsService;
import net.renfei.service.GlobalService;
import net.renfei.service.GoMapService;
import net.renfei.service.PaginationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 跳转控制器
 *
 * @author renfei
 */
@RestController
@RequestMapping("/go")
public class GoController extends BaseController {
    private final GoMapService goMapService;

    protected GoController(RenFeiConfig renFeiConfig,
                           GlobalService globalService,
                           CommentsService commentsService,
                           PaginationService paginationService,
                           GoMapService goMapService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.goMapService = goMapService;
    }

    @GetMapping("{code}")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "跳转控制器")
    public void go(@PathVariable(value = "code", required = false) String code, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        if (BeanUtils.isEmpty(code)) {
            response.setHeader("Location", "/");
        } else {
            response.setHeader("Location", goMapService.getGoMapUrl(code));
        }

    }
}
