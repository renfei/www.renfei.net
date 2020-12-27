package net.renfei.controller;

import net.renfei.annotation.SystemLog;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.*;
import net.renfei.repository.entity.PageDOWithBLOBs;
import net.renfei.service.CommentsService;
import net.renfei.service.GlobalService;
import net.renfei.service.PageService;
import net.renfei.service.PaginationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * <p>Title: PageController</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Controller
@RequestMapping("/page")
public class PageController extends BaseController {
    private final PageService pageService;

    protected PageController(RenFeiConfig renFeiConfig,
                             GlobalService globalService,
                             CommentsService commentsService,
                             PaginationService paginationService,
                             PageService pageService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.pageService = pageService;
    }

    @RequestMapping("{id}")
    public ModelAndView getPage(@PathVariable("id") String id, ModelAndView mv) throws NoHandlerFoundException {
        PageDOWithBLOBs pageDTO = pageService.getPageByID(id);
        if (pageDTO != null) {
            PageVO pageVO = new PageVO();
            BeanUtils.copyProperties(pageDTO, pageVO);
            mv.addObject("pageVO", pageVO);
            setHead(mv, pageVO.getDescribes(), pageVO.getKeyword());
            ShareVO shareVO = new ShareVO();
            shareVO.setTitle(pageVO.getTitle());
            shareVO.setUrl(renFeiConfig.getDomain() + "/page/" + pageVO.getId());
            shareVO.setDescribes(pageVO.getDescribes());
            shareVO.setPics("https://cdn.renfei.net/Logo/ogimage.png");
            mv.addObject("title", shareVO.getTitle());
            mv.addObject("sharevo", shareVO);
        } else {
            throwNoHandlerFoundException();
        }
        mv.setViewName("pages/page");
        return mv;
    }
}
