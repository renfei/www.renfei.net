package net.renfei.controller;

import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.FooterVO;
import net.renfei.entity.OGprotocol;
import net.renfei.entity.SiteMapXml;
import net.renfei.service.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: IndexController</p>
 * <p>Description: 首页控制层</p>
 *
 * @author RenFei
 */
@Slf4j
@Controller
public class IndexController extends BaseController {
    @Value("classpath:xml/sitemap.xsl")
    private Resource siteMapXslXml;
    private final RenFeiConfig renFeiConfig;
    private final PostService postService;
    private final SiteMapService siteMapService;
    private final PaginationService paginationService;

    public IndexController(PostService postService,
                           RenFeiConfig renFeiConfig,
                           GlobalService globalService,
                           SiteMapService siteMapService,
                           CommentsService commentsService,
                           PaginationService paginationService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.renFeiConfig = renFeiConfig;
        this.postService = postService;
        this.siteMapService = siteMapService;
        this.paginationService = paginationService;
    }

    @RequestMapping("/")
    public ModelAndView index(ModelAndView mv) {
        mv.addObject("posts", postService.getAllPost("1", "15").getData());
        mv.addObject("title", "任霏博客 - The RenFei Blog");
        Map<String, Object> models = mv.getModel();
        FooterVO footerVO = (FooterVO) models.get(FOOTER_KEY);
        footerVO.setShowFriendlyLink(true);
        mv.addObject(FOOTER_KEY, footerVO);
        OGprotocol opg = new OGprotocol();
        opg.setType("blog");
        opg.setAuthor("任霏");
        opg.setDescription("任霏博客是任霏的个人网站与博客，一个程序员自己写的网站，不仅仅是文章内容，还包括网站程序的代码。 对新鲜事物都十分感兴趣，利用这个站点向大家分享自己的所见所得，同时这个站点也是我的实验室。");
        opg.setImage("https://cdn.renfei.net/logo/ogimage.png");
        opg.setLocale("zh-CN");
        opg.setReleaseDate(new Date());
        opg.setSiteName("RenFei.Net");
        opg.setTitle("任霏博客 - The RenFei Blog");
        opg.setUrl("https://www.renfei.net");
        setHead(mv,
                "任霏博客是任霏的个人网站与博客，一个程序员自己写的网站，不仅仅是文章内容，还包括网站程序的代码。 对新鲜事物都十分感兴趣，利用这个站点向大家分享自己的所见所得，同时这个站点也是我的实验室。",
                "任霏,博客,任霏博客,RenFei,NeilRen,技术,blog", opg);
        mv.setViewName("index");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "robots.txt", produces = "text/plain")
    public String getRobotsTxt() {
        String robots = "#\n" +
                "# robots.txt for RENFEI.NET\n" +
                "# Version: " + renFeiConfig.getVersion() + "\n" +
                "# Last Build Time: " + renFeiConfig.getBuildTime() + "\n" +
                "#\n" +
                "\n" +
                "User-agent: *\n" +
                "\n" +
                "Disallow: /private/\n" +
                "\n" +
                "Sitemap: " + renFeiConfig.getDomain() + "/sitemap.xml";
        return robots;
    }

    @ResponseBody
    @RequestMapping(value = "ads.txt", produces = "text/plain")
    public String getGoogleAds() throws NoHandlerFoundException {
        String ads;
        ads = renFeiConfig.getGoogle().getAds();
        if (ads == null || ads.length() == 0) {
            throwNoHandlerFoundException();
        }
        return ads;
    }

    @RequestMapping(value = "sitemap.xml")
    public ModelAndView getSiteMapXml(ModelAndView mv, HttpServletResponse response) {
        List<SiteMapXml> siteMapXmls = siteMapService.getSiteMaps();
        mv.addObject("data", siteMapXmls);
        response.setHeader("content-type", "text/xml;charset=UTF-8");
        response.setContentType("text/xml;charset=UTF-8");
        mv.setViewName("sitemap");
        return mv;
    }

    @RequestMapping(value = "sitemap.xsl")
    public ModelAndView getSiteMapXsl(ModelAndView mv, HttpServletResponse response) {
        response.setHeader("content-type", "application/octet-stream;charset=UTF-8");
        response.setContentType("application/octet-stream;charset=UTF-8");
        mv.setViewName("sitemapxsl");
        return mv;
    }
}
