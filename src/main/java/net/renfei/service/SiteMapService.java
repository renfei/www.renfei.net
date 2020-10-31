package net.renfei.service;

import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseService;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.*;
import net.renfei.repository.entity.PostsDOWithBLOBs;
import net.renfei.repository.entity.TagDO;
import net.renfei.sdk.utils.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: SiteMapService</p>
 * <p>Description: 站点地图服务</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "SiteMapService")
public class SiteMapService extends BaseService {
    private final TagService tagService;
    private final PostService postService;
    private final WeiboService weiboService;
    private final PhotoService photoService;
    private final VideoService videoService;
    private final RenFeiConfig renFeiConfig;
    private final KitBoxService kitBoxService;
    private final CategoryService categoryService;
    private final AggregateService aggregateService;

    public SiteMapService(TagService tagService,
                          PostService postService,
                          WeiboService weiboService,
                          PhotoService photoService,
                          VideoService videoService,
                          RenFeiConfig renFeiConfig,
                          KitBoxService kitBoxService,
                          CategoryService categoryService,
                          AggregateService aggregateService) {
        this.tagService = tagService;
        this.postService = postService;
        this.weiboService = weiboService;
        this.photoService = photoService;
        this.videoService = videoService;
        this.renFeiConfig = renFeiConfig;
        this.kitBoxService = kitBoxService;
        this.categoryService = categoryService;
        this.aggregateService = aggregateService;
    }

    public List<SiteMapXml> getSiteMaps() {
        Date postDate = new Date();
        ListData<PostsDOWithBLOBs> posts = postService.getAllPost("1", "1");
        if (posts != null && posts.getTotal() > 0 && posts.getData() != null && posts.getData().size() > 0) {
            postDate = posts.getData().get(0).getReleaseTime();
        }
        List<SiteMapXml> siteMapXmls = new ArrayList<>();
        // 首页
        siteMapXmls.add(new SiteMapXml(renFeiConfig.getDomain(), Changefreq.daily, "1", postDate));
        // 文章
        siteMapXmls.add(new SiteMapXml(renFeiConfig.getDomain() + "/posts", Changefreq.daily, "1", postDate));
        // 微博
        WeiboDTO weibo = weiboService.getAllPosts("1", "1");
        if (weibo != null && weibo.getCount() > 0 && weibo.getWeiboDOList() != null && weibo.getWeiboDOList().size() > 0) {
            siteMapXmls.add(new SiteMapXml(renFeiConfig.getDomain() + "/weibo", Changefreq.daily, "1", weibo.getWeiboDOList().get(0).getReleaseTime()));
        }
        // 视频
        VideoListDTO video = videoService.getAllVideo("1", "1");
        if (video != null && video.getCount() > 0 && video.getVideos() != null && video.getVideos().size() > 0) {
            siteMapXmls.add(new SiteMapXml(renFeiConfig.getDomain() + "/video", Changefreq.daily, "1", video.getVideos().get(0).getReleaseTime()));
        }
        // 相册
        PhotoListDTO photo = photoService.getAllPhotos("1", "1");
        if (photo != null && photo.getCount() > 0 && photo.getPhotoDOWithBLOBs() != null && photo.getPhotoDOWithBLOBs().size() > 0) {
            siteMapXmls.add(new SiteMapXml(renFeiConfig.getDomain() + "/photo", Changefreq.daily, "1", photo.getPhotoDOWithBLOBs().get(0).getReleaseTime()));
        }
        // 工具箱
        siteMapXmls.add(new SiteMapXml(renFeiConfig.getDomain() + "/kitbox", Changefreq.daily, "1", new Date()));
        List<KitBoxMenus> kitBoxs = kitBoxService.getKitBoxMenus();
        if (!BeanUtils.isEmpty(kitBoxs)) {
            for (KitBoxMenus kitBox : kitBoxs
            ) {
                if (!BeanUtils.isEmpty(kitBox.getLinks())) {
                    for (LinkVO link : kitBox.getLinks()
                    ) {
                        siteMapXmls.add(new SiteMapXml(renFeiConfig.getDomain() + link.getHref(), Changefreq.daily, "0.8", new Date()));
                    }
                }
            }
        }
        List<SearchItem> searchItems = aggregateService.getAllDataBySearchItem();
        if (BeanUtils.isEmpty(searchItems)) {
            return siteMapXmls;
        }
        for (SearchItem item : searchItems
        ) {
            siteMapXmls.add(new SiteMapXml(item.getUrl(), Changefreq.daily, "0.9", item.getDate()));
        }
        // 分类
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategory();
        if (categoryDTOS != null && categoryDTOS.size() > 0) {
            for (CategoryDTO cat : categoryDTOS
            ) {
                siteMapXmls.add(new SiteMapXml(renFeiConfig.getDomain() + "/cat" + cat.getUriPath() + "/" + cat.getEnName(), Changefreq.daily, "0.8", postDate));
            }
        }
        // 标签
        List<TagDO> tagDOS = tagService.getAllTag();
        if (tagDOS != null && tagDOS.size() > 0) {
            for (TagDO tag : tagDOS
            ) {
                siteMapXmls.add(new SiteMapXml(renFeiConfig.getDomain() + "/posts/tag/" + tag.getEnName(), Changefreq.daily, "0.8", postDate));
            }
        }
        return siteMapXmls;
    }
}
