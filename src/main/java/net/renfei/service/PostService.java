package net.renfei.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseService;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.*;
import net.renfei.repository.CommentsDOMapper;
import net.renfei.repository.PostsDOMapper;
import net.renfei.repository.PostsExtraDOMapper;
import net.renfei.repository.TagRelationDOMapper;
import net.renfei.repository.entity.*;
import net.renfei.sdk.utils.*;
import net.renfei.service.aliyun.AliyunOSS;
import net.renfei.util.PageRankUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: PostService</p>
 * <p>Description: 文章服务</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "PostService")
public class PostService extends BaseService {
    private static final Double DATE_WEIGHTED = 37.5D;
    private static final Double VIEW_WEIGHTED = 57.5D;
    private static final Double COMMENTHTED = 5D;
    private final IpService ipService;
    private final AliyunOSS aliyunOSS;
    private final TagService tagService;
    private final RenFeiConfig renFeiConfig;
    private final PostsDOMapper postsDOMapper;
    private final DownloadService downloadService;
    private final CategoryService categoryService;
    private final CommentsDOMapper commentsDOMapper;
    private final PostsExtraDOMapper postsExtraDOMapper;
    private final TagRelationDOMapper tagRelationDOMapper;

    public PostService(IpService ipService,
                       AliyunOSS aliyunOSS,
                       TagService tagService,
                       RenFeiConfig renFeiConfig,
                       PostsDOMapper postsDOMapper,
                       DownloadService downloadService,
                       CategoryService categoryService,
                       CommentsDOMapper commentsDOMapper,
                       PostsExtraDOMapper postsExtraDOMapper,
                       TagRelationDOMapper tagRelationDOMapper) {
        this.ipService = ipService;
        this.aliyunOSS = aliyunOSS;
        this.tagService = tagService;
        this.renFeiConfig = renFeiConfig;
        this.postsDOMapper = postsDOMapper;
        this.downloadService = downloadService;
        this.categoryService = categoryService;
        this.commentsDOMapper = commentsDOMapper;
        this.postsExtraDOMapper = postsExtraDOMapper;
        this.tagRelationDOMapper = tagRelationDOMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public void addPost(NewPostVO newPostVO) throws Exception {
        String photoPath = aliyunOSS.upload("upload/" + DateUtils.getYear() + "/", newPostVO.getImage());
        PostsDOWithBLOBs posts = new PostsDOWithBLOBs();
        posts.setCategoryId(newPostVO.getCategoryId());
        posts.setFeaturedImage(photoPath);
        posts.setTitle(newPostVO.getTitle());
        // 将 ueditor 的代码格式替换成 highlightjs 的
        posts.setContent(
                newPostVO.getContent()
                        .replace("<pre class=\"brush:bash;toolbar:false\">", "<pre><code class=\"bash\">")
                        .replace("<pre class=\"brush:java;toolbar:false\">", "<pre><code class=\"java\">")
                        .replace("<pre class=\"brush:scala;toolbar:false\">", "<pre><code class=\"scala\">")
                        .replace("<pre class=\"brush:xml;toolbar:false\">", "<pre><code class=\"xml\">")
                        .replace("<pre class=\"brush:sql;toolbar:false\">", "<pre><code class=\"sql\">")
                        .replace("<pre class=\"brush:js;toolbar:false\">", "<pre><code class=\"js\">")
                        .replace("<pre class=\"brush:php;toolbar:false\">", "<pre><code class=\"php\">")
                        .replace("<pre class=\"brush:html;toolbar:false\">", "<pre><code class=\"html\">")
                        .replace("<pre class=\"brush:css;toolbar:false\">", "<pre><code class=\"css\">")
                        .replace("<pre class=\"brush:c#;toolbar:false\">", "<pre><code class=\"csharp\">")
                        .replace("<pre class=\"brush:python;toolbar:false\">", "<pre><code class=\"python\">")
                        .replace("<pre class=\"brush:ruby;toolbar:false\">", "<pre><code class=\"ruby\">")
                        .replace("<pre class=\"brush:vb;toolbar:false\">", "<pre><code class=\"vb\">")
                        .replace("<pre class=\"brush:cpp;toolbar:false\">", "<pre><code class=\"cpp\">")
                        .replace("</pre>", "</code></pre>")
        );
        posts.setIsOriginal(newPostVO.getIsOriginal());
        posts.setSourceName(newPostVO.getSourceName());
        posts.setSourceUrl(newPostVO.getSourceUrl());
        posts.setViews(0L);
        posts.setThumbsUp(0L);
        posts.setThumbsDown(0L);
        posts.setAddTime(new Date());
        posts.setReleaseTime(new Date());
        posts.setDescribes(newPostVO.getDescribes());
        posts.setKeyword(newPostVO.getKeyword());
        posts.setIsDelete(false);
        posts.setIsComment(true);
        postsDOMapper.insertSelective(posts);
        String[] tags = newPostVO.getTags().split(",");
        for (String tagId : tags
        ) {
            TagRelationDO tagRelation = new TagRelationDO();
            tagRelation.setTagId(Long.parseLong(tagId));
            tagRelation.setTargetId(posts.getId());
            tagRelation.setTypeId(1L);
            tagRelationDOMapper.insertSelective(tagRelation);
        }
    }

    public UEditorUploadVO uEditorUpload(MultipartFile files) throws Exception {
        String originalFilename = files.getOriginalFilename();
        assert originalFilename != null;
        String type = originalFilename.substring(originalFilename.indexOf("."));
        long size = files.getSize();
        String photoPath = aliyunOSS.upload("upload/" + DateUtils.getYear() + "/", files);
        UEditorUploadVO uEditorUploadVO = new UEditorUploadVO();
        uEditorUploadVO.setOriginal(originalFilename);
        uEditorUploadVO.setSize(size);
        uEditorUploadVO.setState("SUCCESS");
        uEditorUploadVO.setTitle(originalFilename);
        uEditorUploadVO.setType(type);
        uEditorUploadVO.setUrl(photoPath);
        return uEditorUploadVO;
    }

    /**
     * 获取全部文章
     *
     * @param pages
     * @param rows
     * @return
     */
    @Cacheable
    public ListData<PostsDOWithBLOBs> getAllPost(String pages, String rows) {
        ListData<PostsDOWithBLOBs> postsDOWithBLOBsListData = new ListData<>();
        PostsDOExample example = new PostsDOExample();
        example.setOrderByClause("release_time DESC");
        example.createCriteria()
                .andReleaseTimeLessThanOrEqualTo(new Date())
                .andIsDeleteEqualTo(false);
        Page<PostsDOWithBLOBs> page =
                PageHelper.startPage(NumberUtils.parseInt(pages, 1), NumberUtils.parseInt(rows, 10));
        postsDOMapper.selectByExampleWithBLOBs(example);
        postsDOWithBLOBsListData.setTotal(page.getTotal());
        postsDOWithBLOBsListData.setData(page.getResult());
        return postsDOWithBLOBsListData;
    }

    public List<PostsDOWithBLOBs> getAllPostNotCache() {
        PostsDOExample example = new PostsDOExample();
        example.setOrderByClause("release_time DESC");
        example.createCriteria()
                .andReleaseTimeLessThanOrEqualTo(new Date())
                .andIsDeleteEqualTo(false);
        return postsDOMapper.selectByExampleWithBLOBs(example);
    }

    @Cacheable
    public ListData<PostsDOWithBLOBs> getAllPostsByCatID(Long catId, String pages, String rows) {
        ListData<PostsDOWithBLOBs> postsDOWithBLOBsListData = new ListData<>();
        PostsDOExample example = new PostsDOExample();
        example.setOrderByClause("release_time DESC");
        example.createCriteria()
                .andReleaseTimeLessThanOrEqualTo(new Date())
                .andCategoryIdEqualTo(catId)
                .andIsDeleteEqualTo(false);
        Page<PostsDOWithBLOBs> page =
                PageHelper.startPage(NumberUtils.parseInt(pages, 1), NumberUtils.parseInt(rows, 10));
        postsDOMapper.selectByExampleWithBLOBs(example);
        postsDOWithBLOBsListData.setTotal(page.getTotal());
        postsDOWithBLOBsListData.setData(page.getResult());
        return postsDOWithBLOBsListData;
    }

    /**
     * 获取文章列表
     *
     * @param pages     页码
     * @param rows      每页容量
     * @param tagEnName 标签
     * @return
     */
    @Cacheable
    public ListData<PostsDOWithBLOBs> getAllPostsByTag(String pages, String rows, String tagEnName) {
        ListData<PostsDOWithBLOBs> postsDOWithBLOBsListData = new ListData<>();
        List<TagRelationDO> tagRelationDOS = tagService.getTagRelationByEnName(tagEnName, 1L);
        if (tagRelationDOS != null && tagRelationDOS.size() > 0) {
            List<Long> ids = new ArrayList<>();
            for (TagRelationDO tag : tagRelationDOS
            ) {
                ids.add(tag.getTargetId());
            }
            PostsDOExample postsDOExample = new PostsDOExample();
            postsDOExample.setOrderByClause("page_rank DESC,release_time DESC");
            postsDOExample
                    .createCriteria()
                    .andIsDeleteEqualTo(false)
                    .andReleaseTimeLessThanOrEqualTo(new Date())
                    .andIdIn(ids);
            Page<PostsDOWithBLOBs> page =
                    PageHelper.startPage(NumberUtils.parseInt(pages, 1), NumberUtils.parseInt(rows, 10));
            postsDOMapper.selectByExampleWithBLOBs(postsDOExample);
            postsDOWithBLOBsListData.setTotal(page.getTotal());
            postsDOWithBLOBsListData.setData(page.getResult());
            return postsDOWithBLOBsListData;
        }
        return null;
    }

    /**
     * 根据ID获取文章详情
     *
     * @param id 文章ID
     * @return
     */
    @Cacheable
    public PostsDOWithBLOBs getPostById(long id) {
        PostsDOExample example = new PostsDOExample();
        example.createCriteria()
                .andIsDeleteEqualTo(false)
                .andReleaseTimeLessThanOrEqualTo(new Date())
                .andIdEqualTo(id);
        PostsDOWithBLOBs postsDOWithBLOBs = ListUtils.getOne(postsDOMapper.selectByExampleWithBLOBs(example));
        return postsDOWithBLOBs;
    }

    /**
     * 获取相关推荐
     *
     * @return
     */
    @Cacheable
    public List<PostsDOWithBLOBs> getRelated(PostsDOWithBLOBs postsDOWithBLOBs) {
        //1、先拿到文章的标签组
        List<TagRelationDO> tagRelationDOS = tagService.getTagRelationByTargetId(postsDOWithBLOBs.getId());
        List<Long> ids = new ArrayList<>();
        if (tagRelationDOS != null && tagRelationDOS.size() > 0) {
            for (TagRelationDO tagRelationDO : tagRelationDOS
            ) {
                ids.add(tagRelationDO.getTagId());
            }
        }
        PostsDOExample postsDOExample;
        if (ids.size() > 0) {
            //2、根据标签组拿到所有文章id
            List<TagRelationDO> tagRelationDOS1 = tagService.getTagRelationByTagIds(ids);
            ids = new ArrayList<>();
            if (tagRelationDOS1 != null && tagRelationDOS1.size() > 0) {
                for (TagRelationDO tagRelationDO : tagRelationDOS1
                ) {
                    ids.add(tagRelationDO.getTargetId());
                }
            }
            //3、根据文章ID获得所有文章
            postsDOExample = new PostsDOExample();
            postsDOExample.setDistinct(true);
            postsDOExample.setOrderByClause("page_rank DESC,release_time DESC");
            if (ids.size() > 0) {
                postsDOExample.createCriteria()
                        .andIdIn(ids)
                        .andIsDeleteEqualTo(false)
                        .andIdNotEqualTo(postsDOWithBLOBs.getId())
                        .andReleaseTimeLessThanOrEqualTo(new Date());
                PageHelper.startPage(1, 10);
                return postsDOMapper.selectByExampleWithBLOBs(postsDOExample);
            }
        }
        //兜底
        postsDOExample = new PostsDOExample();
        postsDOExample.setDistinct(true);
        postsDOExample.setOrderByClause("page_rank DESC,release_time DESC");
        postsDOExample.createCriteria()
                .andIsDeleteEqualTo(false)
                .andIdNotEqualTo(postsDOWithBLOBs.getId())
                .andReleaseTimeLessThanOrEqualTo(new Date());
        PageHelper.startPage(1, 10);
        return postsDOMapper.selectByExampleWithBLOBs(postsDOExample);
    }

    /**
     * 获取文章扩展信息
     *
     * @param id 文章ID
     * @param mv 视图对象
     */
    public void getPostsExtraByID(Long id, ModelAndView mv, HttpServletRequest request) {
        PostsExtraDOExample postsExtraDOExample = new PostsExtraDOExample();
        postsExtraDOExample.createCriteria()
                .andPostIdEqualTo(id);
        List<PostsExtraDO> postsExtraDOS = postsExtraDOMapper.selectByExampleWithBLOBs(postsExtraDOExample);
        if (postsExtraDOS != null && postsExtraDOS.size() > 0) {
            for (PostsExtraDO postsExtra : postsExtraDOS
            ) {
                if ("download".equals(postsExtra.getExtraType())) {
                    //文章扩展下载服务
                    DownloadDO downloadDO = downloadService.getDownloadInfoById(postsExtra.getExtraValue());
                    if (!BeanUtils.isEmpty(downloadDO.getDisableArea())) {
                        String[] disableArea = downloadDO.getDisableArea().split(",");
                        IpInfoDTO ipdto = ipService.query(IpUtils.getIpAddress(request));
                        for (String dis : disableArea
                        ) {
                            if (ipdto.getCountryShort().toLowerCase().equals(dis.toLowerCase())) {
                                downloadDO.setBaiduYunPanUrl(null);
                                downloadDO.setBaiduYunPanCode("应版权方要求，您所在的地区暂不支持下载，请更换来访IP尝试。");
                                downloadDO.setFilePath(null);
                                break;
                            }
                        }
                    }
                    mv.addObject("downloadDO", downloadDO);
                }
                //....可以扩展其他类型
            }
        }
    }

    /**
     * 获取文章侧边栏
     *
     * @return
     */
    @Cacheable
    public PostSidebarVO getPostSidebar() {
        // 文档分类
        List<CategoryDO> categoryDOS = categoryService.getAllCategoryByType(1L);
        List<LinkVO> categoryLinks = new ArrayList<>();
        if (!BeanUtils.isEmpty(categoryDOS)) {
            categoryDOS.forEach(categoryDO -> categoryLinks.add(Builder.of(LinkVO::new)
                    .with(LinkVO::setHref, renFeiConfig.getDomain() + "/cat/Posts/" + categoryDO.getEnName())
                    .with(LinkVO::setText, categoryDO.getZhName())
                    .build()));
        }
        // 标签
        List<TagDO> tagDOS = tagService.getAllTag();
        List<LinkVO> tagLinks = new ArrayList<>();
        if (!BeanUtils.isEmpty(tagDOS)) {
            tagDOS.forEach(tagDO -> tagLinks.add(Builder.of(LinkVO::new)
                    .with(LinkVO::setHref, renFeiConfig.getDomain() + "/posts/tag/" + tagDO.getEnName())
                    .with(LinkVO::setText, tagDO.getZhName())
                    .build()));
        }
        // 最新留言
        CommentsDOExample commentDOExample = new CommentsDOExample();
        commentDOExample.setOrderByClause("addtime DESC");
        commentDOExample.createCriteria()
                .andIsDeleteEqualTo(false)
                .andParentIdIsNull();
        PageHelper.startPage(1, 10);
        List<CommentsDOWithBLOBs> commentsDOWithBLOBs = commentsDOMapper.selectByExampleWithBLOBs(commentDOExample);
        List<LinkVO> commentsLinks = new ArrayList<>();
        if (!BeanUtils.isEmpty(commentsDOWithBLOBs)) {
            commentsDOWithBLOBs.forEach(commentsDO -> commentsLinks.add(Builder.of(LinkVO::new)
                    .with(LinkVO::setHref, renFeiConfig.getDomain() + "/posts/" + commentsDO.getTargetId() + "#cmt" + commentsDO.getId())
                    .with(LinkVO::setText, commentsDO.getContent())
                    .build()));
        }
        // 热文排行
        List<PostsDOWithBLOBs> postsDOWithBLOBs = this.getHotPost();
        List<LinkVO> hotPostLinks = new ArrayList<>();
        if (!BeanUtils.isEmpty(postsDOWithBLOBs)) {
            postsDOWithBLOBs.forEach(post -> hotPostLinks.add(Builder.of(LinkVO::new)
                    .with(LinkVO::setHref, renFeiConfig.getDomain() + "/posts/" + post.getId())
                    .with(LinkVO::setText, post.getTitle())
                    .build()));
        }
        return Builder.of(PostSidebarVO::new)
                .with(PostSidebarVO::setPostSidebars, new ArrayList<PostSidebarVO.PostSidebar>() {
                    {
                        // 文档分类
                        this.add(Builder.of(PostSidebarVO.PostSidebar::new)
                                .with(PostSidebarVO.PostSidebar::setTitle, "文档分类")
                                .with(PostSidebarVO.PostSidebar::setLink, categoryLinks)
                                .build());
                        // 标签云
                        this.add(Builder.of(PostSidebarVO.PostSidebar::new)
                                .with(PostSidebarVO.PostSidebar::setTitle, "标签云")
                                .with(PostSidebarVO.PostSidebar::setLink, tagLinks)
                                .build());
                        // 最新留言
                        this.add(Builder.of(PostSidebarVO.PostSidebar::new)
                                .with(PostSidebarVO.PostSidebar::setTitle, "最新留言")
                                .with(PostSidebarVO.PostSidebar::setLink, commentsLinks)
                                .build());
                        // 热文排行
                        this.add(Builder.of(PostSidebarVO.PostSidebar::new)
                                .with(PostSidebarVO.PostSidebar::setTitle, "热文排行")
                                .with(PostSidebarVO.PostSidebar::setLink, hotPostLinks)
                                .build());
                    }
                })
                .build();
    }

    /**
     * 获取最热文章Top10
     *
     * @return
     */
    @Cacheable
    public List<PostsDOWithBLOBs> getHotPost() {
        PostsDOExample postsDOExample = new PostsDOExample();
        postsDOExample.setOrderByClause("avg_views DESC,release_time DESC");
        postsDOExample
                .createCriteria()
                .andIsDeleteEqualTo(false)
                .andReleaseTimeLessThanOrEqualTo(new Date());
        PageHelper.startPage(1, 10);
        return postsDOMapper.selectByExampleWithBLOBs(postsDOExample);
    }

    /**
     * 更新文章的权重
     */
    public void updatePageRank() {
        PostsDOExample postsDOExample = new PostsDOExample();
        postsDOExample.createCriteria();
        List<PostsDO> postsDOS = postsDOMapper.selectByExample(postsDOExample);
        for (PostsDO post : postsDOS
        ) {
            setPageRank(post);
            postsDOMapper.updateByPrimaryKey(post);
        }
    }

    @CacheEvict(value = "PostService", allEntries = true)
    public void cleanAll() {
    }

    private void setPageRank(PostsDO postsDO) {
        PageRankUtil pageRankUtil = new PageRankUtil();
        postsDO.setPageRank(pageRankUtil.getPageRank(
                postsDO.getReleaseTime(),
                postsDO.getViews(),
                0L,
                DATE_WEIGHTED, VIEW_WEIGHTED, COMMENTHTED
        ));
        postsDO.setAvgViews(pageRankUtil.getAvgViews(
                postsDO.getReleaseTime(),
                postsDO.getViews()
        ));
        postsDO.setAvgComment(pageRankUtil.getAvgComments(
                postsDO.getReleaseTime(),
                0L
        ));
        postsDO.setPageRankUpdateTime(new Date());
    }

    /**
     * 文章浏览量自增
     */
    @Async
    public void updateView(long id) {
        PostsDOExample example = new PostsDOExample();
        example.createCriteria()
                .andIsDeleteEqualTo(false)
                .andReleaseTimeLessThanOrEqualTo(new Date())
                .andIdEqualTo(id);
        PostsDOWithBLOBs postsDOWithBLOBs = ListUtils.getOne(postsDOMapper.selectByExampleWithBLOBs(example));
        if (postsDOWithBLOBs != null) {
            postsDOWithBLOBs.setViews(postsDOWithBLOBs.getViews() + 1);
            postsDOMapper.updateByPrimaryKeySelective(postsDOWithBLOBs);
        }
    }
}
