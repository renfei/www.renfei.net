package net.renfei.controller;

import lombok.extern.slf4j.Slf4j;
import net.renfei.annotation.SystemLog;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.*;
import net.renfei.repository.entity.PostsDOWithBLOBs;
import net.renfei.repository.entity.TagDO;
import net.renfei.repository.entity.TagRelationDO;
import net.renfei.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: PostsController</p>
 * <p>Description: 文档控制器</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Controller
@RequestMapping("/posts")
public class PostsController extends BaseController {
    private final TagService tagService;
    private final RenFeiConfig renFeiConfig;
    private final PostService postService;
    private final JsonLdService jsonLdService;

    protected PostsController(GlobalService globalService,
                              RenFeiConfig renFeiConfig,
                              PostService postService,
                              JsonLdService jsonLdService,
                              CommentsService commentsService,
                              PaginationService paginationService,
                              TagService tagService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.renFeiConfig = renFeiConfig;
        this.postService = postService;
        this.jsonLdService = jsonLdService;
        this.tagService = tagService;
    }

    @RequestMapping("")
    public ModelAndView getPostList(ModelAndView mv,
                                    @RequestParam(value = "page", required = false) String page) {
        ListData<PostsDOWithBLOBs> postsDOWithBLOBsListData = postService.getAllPost(page, "15");
        mv.addObject("title", "任霏博客 - " + renFeiConfig.getSiteName());
        mv.addObject("catTitle", "全部文档");
        mv.addObject("lists", postsDOWithBLOBsListData.getData());
        mv.addObject("PostSidebar", postService.getPostSidebar());
        setHead(mv,
                "任霏的个人博客，分享关注软件开发技术，推动并帮助开发者通过互联网获得知识，从而让更多开发者从中受益。",
                "任霏,RenFei,NeilRen,个人,博客,blog,开发,技术,posts");
        setPagination(mv, page, postsDOWithBLOBsListData.getTotal(), "/posts?page=");
        mv.setViewName("posts/list");
        return mv;
    }

    /**
     * 根据标签获取所有文章列表
     *
     * @param page 页码
     * @return
     */
    @RequestMapping("tag/{enName}")
    public ModelAndView getAllPostsListByTag(@RequestParam(value = "page", required = false) String page,
                                             @PathVariable("enName") String enName,
                                             ModelAndView mv) throws NoHandlerFoundException {
        List<TagDO> tagDOS = tagService.getTagByEnName(enName);
        if (tagDOS == null || tagDOS.size() < 1) {
            throwNoHandlerFoundException();
        }
        List<TagRelationDO> tagRelationDOS = tagService.getTagRelationByEnName(enName, 1L);
        if (tagRelationDOS == null || tagRelationDOS.size() < 1) {
            throwNoHandlerFoundException();
        }
        ListData<PostsDOWithBLOBs> postsListDTO = postService.getAllPostsByTag(page, "10", enName);
        if (postsListDTO == null || postsListDTO.getTotal() < 1) {
            throwNoHandlerFoundException();
        }
        mv.addObject("catTitle", "标签：" + tagDOS.get(0).getZhName());
        mv.addObject("lists", postsListDTO.getData());
        mv.addObject("title", "标签：" + tagDOS.get(0).getZhName() + " - 博客文章标签分类 - " + renFeiConfig.getSiteName());
        setHead(mv, "博客文章标签分类：" + tagDOS.get(0).getZhName() + "。共同类型的文章在这里聚合等待您的查阅。",
                tagDOS.get(0).getZhName() + ",博客,blog,开发,技术,posts");
        setPagination(mv, page, postsListDTO.getTotal(), "/posts/tag/" + enName + "?page=");
        mv.addObject("PostSidebar", postService.getPostSidebar());
        mv.setViewName("posts/list");
        return mv;
    }

    @RequestMapping("{id}")
    public ModelAndView getPostInfo(ModelAndView mv,
                                    @PathVariable("id") String id) throws NoHandlerFoundException {
        long postId = 0;
        try {
            postId = Long.parseLong(id);
        } catch (NumberFormatException numberFormatException) {
            throwNoHandlerFoundException();
        }
        PostsDOWithBLOBs postsDOWithBLOBs = postService.getPostById(postId);
        postService.updateView(postId);
        if (postsDOWithBLOBs == null) {
            throwNoHandlerFoundException();
        }
        List<PostsDOWithBLOBs> postRelated = postService.getRelated(postsDOWithBLOBs);
        // 获取文章扩展信息
        postService.getPostsExtraByID(postId, mv, request);
        OGprotocol opg = new OGprotocol();
        opg.setType("article");
        opg.setAuthor(postsDOWithBLOBs.getSourceName());
        opg.setDescription(postsDOWithBLOBs.getDescribes());
        opg.setImage(postsDOWithBLOBs.getFeaturedImage());
        opg.setLocale("zh_CN");
        opg.setReleaseDate(postsDOWithBLOBs.getReleaseTime());
        opg.setSiteName("RenFei.Net");
        opg.setTitle(postsDOWithBLOBs.getTitle());
        opg.setUrl(renFeiConfig.getDomain() + "/posts/" + postsDOWithBLOBs.getId());
        setHead(mv, postsDOWithBLOBs.getDescribes(), postsDOWithBLOBs.getKeyword(), opg);
        mv.addObject("post", postsDOWithBLOBs);
        mv.addObject("related", postRelated);
        mv.addObject("title", postsDOWithBLOBs.getTitle() + " - Posts - " + renFeiConfig.getSiteName());
        mv.addObject("jsonld", jsonLdService.getJsonld(postsDOWithBLOBs));
        mv.addObject("PostSidebar", postService.getPostSidebar());
        //查询评论
        setComment(mv, 1L, postsDOWithBLOBs.getId());
        ShareVO shareVO = new ShareVO();
        shareVO.setTitle(postsDOWithBLOBs.getTitle());
        shareVO.setUrl(renFeiConfig.getDomain() + "/posts/" + postsDOWithBLOBs.getId());
        shareVO.setDescribes(postsDOWithBLOBs.getDescribes());
        shareVO.setPics(postsDOWithBLOBs.getFeaturedImage());
        shareVO.setViews(postsDOWithBLOBs.getViews().toString());
        mv.addObject("sharevo", shareVO);
        if (postsDOWithBLOBs.getContent().contains("code class=")) {
            //检测到有代码显示，需要增加代码高亮插件
            mv.addObject("HighlightJS", 1);
        }
        mv.setViewName("posts/post");
        return mv;
    }
}
