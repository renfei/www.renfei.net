package net.renfei.service;

import net.renfei.base.BaseService;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.CategoryDTO;
import net.renfei.entity.FeedVO;
import net.renfei.entity.ListData;
import net.renfei.repository.entity.PostsDOWithBLOBs;
import net.renfei.sdk.utils.Builder;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: FeedService</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 * @date : 2020-12-06 20:58
 */
@Service
@CacheConfig(cacheNames = "FeedService")
public class FeedService extends BaseService {
    private final PostService postService;
    private final RenFeiConfig renFeiConfig;
    private final CategoryService categoryService;

    public FeedService(PostService postService,
                       RenFeiConfig renFeiConfig,
                       CategoryService categoryService) {
        this.postService = postService;
        this.renFeiConfig = renFeiConfig;
        this.categoryService = categoryService;
    }

    @Cacheable
    public FeedVO getFeed() {
        FeedVO feedVO = new FeedVO();
        feedVO.setTitle(renFeiConfig.getSiteName());
        feedVO.setAuthor("i@renfei.net (RenFei)");
        feedVO.setLink(renFeiConfig.getDomain());
        feedVO.setDescription("任霏博客是任霏的个人网站与博客，一个程序员自己写的网站，不仅仅是文章内容，还包括网站程序的代码。 对新鲜事物都十分感兴趣，利用这个站点向大家分享自己的所见所得，同时这个站点也是我的实验室。");
        feedVO.setLanguage("zh-CN");
        feedVO.setImage(Builder.of(FeedVO.Image::new)
                .with(FeedVO.Image::setLink, renFeiConfig.getDomain())
                .with(FeedVO.Image::setHeight, "32")
                .with(FeedVO.Image::setTitle, renFeiConfig.getSiteName())
                .with(FeedVO.Image::setUrl, "https://cdn.renfei.net/Logo/RF_white.svg")
                .with(FeedVO.Image::setWidth, "32")
                .build());
        List<FeedVO.Item> items = new ArrayList<>();
        ListData<PostsDOWithBLOBs> postsLis = postService.getAllPost("1", "10");
        if (postsLis != null && postsLis.getData() != null) {
            for (PostsDOWithBLOBs post : postsLis.getData()
            ) {
                FeedVO.Item item = new FeedVO.Item();
                item.setTitle(post.getTitle());
                item.setAuthor(post.getSourceName());
                item.setLink(renFeiConfig.getDomain() + "/posts/" + post.getId());
                item.setGuid(renFeiConfig.getDomain() + "/posts/" + post.getId());
                item.setDescription(post.getDescribes());
                CategoryDTO categoryDTO = categoryService.getCategoryById(post.getCategoryId());
                item.setCategory(categoryDTO.getZhName());
                item.setPubDate(post.getReleaseTime());
                items.add(item);
            }
        }
        feedVO.setItem(items);
        return feedVO;
    }
}
