package net.renfei.controller;

import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>Title: BackstageController</p>
 * <p>Description: 网站后台管理界面</p>
 *
 * @author RenFei(i @ renfei.net)
 * @date : 2021-01-09 22:25
 */
@Slf4j
@Controller
@RequestMapping("/backstage")
@PreAuthorize("hasRole('ROLE_backstage')")
public class BackstageController extends BaseController {
    private final TagService tagService;
    private final CategoryService categoryService;

    protected BackstageController(RenFeiConfig renFeiConfig,
                                  GlobalService globalService,
                                  CommentsService commentsService,
                                  PaginationService paginationService,
                                  TagService tagService,
                                  CategoryService categoryService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.tagService = tagService;
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ModelAndView index(ModelAndView mv) {
        mv.addObject("title", "仪表板");
        mv.setViewName("backstage/index");
        return mv;
    }

    @GetMapping("sendEmail")
    @PreAuthorize("hasAuthority('email:send')")
    public ModelAndView sendEmail(ModelAndView mv) {
        mv.addObject("title", "发送系统邮件");
        mv.setViewName("backstage/sendEmail");
        return mv;
    }

    @GetMapping("newPost")
    @PreAuthorize("hasAuthority('post:add')")
    public ModelAndView newPostView(ModelAndView mv) {
        mv.addObject("title", "发布新文章");
        mv.addObject("category", categoryService.getAllCategoryByType(1L));
        mv.addObject("tags", tagService.getAllTag());
        mv.setViewName("backstage/newPost");
        return mv;
    }

    @GetMapping("newWeibo")
    @PreAuthorize("hasAuthority('weibo:add')")
    public ModelAndView newWeiboView(ModelAndView mv) {
        mv.addObject("title", "发布新微博");
        mv.setViewName("backstage/newWeibo");
        return mv;
    }
}
