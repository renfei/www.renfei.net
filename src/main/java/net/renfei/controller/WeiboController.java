package net.renfei.controller;

import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.OGprotocol;
import net.renfei.entity.WeiboDOS;
import net.renfei.entity.WeiboDTO;
import net.renfei.repository.entity.WeiboDO;
import net.renfei.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * <p>Title: WeiboController</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Controller
@RequestMapping("/weibo")
public class WeiboController extends BaseController {
    private final WeiboService weiboService;
    private final JsonLdService jsonLdService;

    protected WeiboController(RenFeiConfig renFeiConfig,
                              WeiboService weiboService,
                              GlobalService globalService,
                              JsonLdService jsonLdService,
                              CommentsService commentsService,
                              PaginationService paginationService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.jsonLdService = jsonLdService;
        this.weiboService = weiboService;
    }

    @RequestMapping("")
    public ModelAndView weibo(@RequestParam(value = "page", required = false) String page,
                              ModelAndView mv) {
        WeiboDTO weiboDTO = weiboService.getAllPosts(page, "10");
        mv.addObject("weiboDTO", weiboDTO.getWeiboDOList());
        setPagination(mv, page, weiboDTO.getCount(), "/weibo?page=");
        setHead(mv, "任霏的的微型博客和微型网志。", "任霏,RenFei,NeilRen,个人,博客,blog,开发,技术,posts");
        mv.addObject("title", "任霏的微博客 - Weibo - " + renFeiConfig.getSiteName());
        mv.setViewName("weibo/index");
        return mv;
    }

    @RequestMapping("{id}")
    public ModelAndView getPostsByID(@PathVariable("id") String id, ModelAndView mv) throws NoHandlerFoundException {
        WeiboDO weiboDO = weiboService.getWeiboById(id, true);
        if (weiboDO == null) {
            throwNoHandlerFoundException();
        }
        WeiboDOS weiboDOS = new WeiboDOS();
        BeanUtils.copyProperties(weiboDO, weiboDOS);
        weiboService.setImg(weiboDOS);
        mv.addObject("weibo", weiboDOS);
        OGprotocol opg = new OGprotocol();
        opg.setType("article");
        opg.setAuthor("任霏");
        opg.setDescription(weiboDOS.getContent());
        if (weiboDOS.getImg() != null) {
            opg.setImage(weiboDOS.getImg());
        }
        opg.setLocale("zh_CN");
        opg.setReleaseDate(weiboDOS.getReleaseTime());
        opg.setSiteName("RenFei.Net");
        opg.setTitle(getTitle(weiboDOS.getContent()) + " - Weibo");
        opg.setUrl(renFeiConfig.getDomain() + "/weibo/" + weiboDOS.getId());
        setHead(mv, weiboDOS.getContent(), "", opg);
        mv.addObject("title", getTitle(weiboDOS.getContent()) + " - 任霏的微博客");
        //查询评论
        setComment(mv, 6L, weiboDO.getId());
        mv.setViewName("weibo/weibo");
        return mv;
    }

    private String getTitle(String content) {
        if (content.length() < 100) {
            return content;
        } else {
            return content.substring(0, 100);
        }
    }
}
