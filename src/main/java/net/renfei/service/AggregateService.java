package net.renfei.service;

import net.renfei.config.RenFeiConfig;
import net.renfei.discuz.repository.entity.DiscuzForumPostDO;
import net.renfei.entity.KitBoxMenus;
import net.renfei.entity.LinkVO;
import net.renfei.entity.SearchItem;
import net.renfei.entity.TypeEnum;
import net.renfei.repository.entity.*;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>Title: AggregateService</p>
 * <p>Description: 聚合服务</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Service
public class AggregateService {
    private final PostService postService;
    private final PageService pageService;
    private final RenFeiConfig renFeiConfig;
    private final VideoService videoService;
    private final PhotoService photoService;
    private final WeiboService weiboService;
    private final KitBoxService kitBoxService;
    private final DiscuzService discuzService;

    public AggregateService(PostService postService, PageService pageService,
                            RenFeiConfig renFeiConfig, VideoService videoService,
                            PhotoService photoService, WeiboService weiboService,
                            KitBoxService kitBoxService, DiscuzService discuzService) {
        this.postService = postService;
        this.pageService = pageService;
        this.renFeiConfig = renFeiConfig;
        this.videoService = videoService;
        this.photoService = photoService;
        this.weiboService = weiboService;
        this.kitBoxService = kitBoxService;
        this.discuzService = discuzService;
    }

    public List<SearchItem> getAllDataBySearchItemSite() {
        List<SearchItem> searchItemAll = new ArrayList<>();
        // == Post >>>>
        List<PostsDOWithBLOBs> postsAll = postService.getAllPostNotCache();
        if (!BeanUtils.isEmpty(postsAll)) {
            for (PostsDOWithBLOBs post : postsAll
            ) {
                SearchItem searchItem = new SearchItem();
                searchItem.setUuid(UUID.randomUUID().toString().toUpperCase());
                searchItem.setType(TypeEnum.POSTS.getName());
                searchItem.setTitle(post.getTitle());
                searchItem.setContent(StringUtils.delHtmlTags(post.getContent()));
                searchItem.setImage(getImgUrl(post.getFeaturedImage()));
                searchItem.setUrl(renFeiConfig.getDomain() + TypeEnum.POSTS.getUrl() + "/" + post.getId());
                searchItem.setOriginalId(post.getId());
                searchItem.setDate(post.getReleaseTime());
                searchItemAll.add(searchItem);
            }
        }
        // == Posts <<<< Pages >>>>
        List<PageDOWithBLOBs> pageAll = pageService.getAllPageNotCache();
        if (!BeanUtils.isEmpty(pageAll)) {
            for (PageDOWithBLOBs page : pageAll
            ) {
                SearchItem searchItem = new SearchItem();
                searchItem.setUuid(UUID.randomUUID().toString().toUpperCase());
                searchItem.setType(TypeEnum.PAGES.getName());
                searchItem.setTitle(page.getTitle());
                searchItem.setContent(StringUtils.delHtmlTags(page.getContent()));
                searchItem.setImage(getImgUrl(page.getFeaturedImage()));
                searchItem.setUrl(renFeiConfig.getDomain() + TypeEnum.PAGES.getUrl() + "/" + page.getId());
                searchItem.setOriginalId(page.getId());
                searchItem.setDate(page.getReleaseTime());
                searchItemAll.add(searchItem);
            }
        }
        // == Pages <<<< Video >>>>
        List<VideoDOWithBLOBs> videosAll = videoService.getAllVideoNotCache();
        if (!BeanUtils.isEmpty(videosAll)) {
            for (VideoDOWithBLOBs video : videosAll
            ) {
                SearchItem searchItem = new SearchItem();
                searchItem.setUuid(UUID.randomUUID().toString().toUpperCase());
                searchItem.setType(TypeEnum.VIDEO.getName());
                searchItem.setTitle(video.getTitle());
                searchItem.setContent(StringUtils.delHtmlTags(video.getDescribes()));
                searchItem.setImage(getImgUrl(video.getFeaturedImage()));
                searchItem.setUrl(renFeiConfig.getDomain() + TypeEnum.VIDEO.getUrl() + "/" + video.getId());
                searchItem.setOriginalId(video.getId());
                searchItem.setDate(video.getReleaseTime());
                searchItemAll.add(searchItem);
            }
        }
        // == Video <<<< Photo >>>>
        List<PhotoDOWithBLOBs> photosAll = photoService.getAllPhotosNotCache();
        if (!BeanUtils.isEmpty(photosAll)) {
            for (PhotoDOWithBLOBs photo : photosAll
            ) {
                SearchItem searchItem = new SearchItem();
                searchItem.setUuid(UUID.randomUUID().toString().toUpperCase());
                searchItem.setType(TypeEnum.PHOTO.getName());
                searchItem.setTitle(photo.getTitle());
                searchItem.setContent(StringUtils.delHtmlTags(photo.getDescribes()));
                searchItem.setImage(getImgUrl(photo.getFeaturedImage()));
                searchItem.setUrl(renFeiConfig.getDomain() + TypeEnum.PHOTO.getUrl() + "/" + photo.getId());
                searchItem.setOriginalId(photo.getId());
                searchItem.setDate(photo.getReleaseTime());
                searchItemAll.add(searchItem);
            }
        }
        // == Photo <<<< Weibo >>>>
        List<WeiboDO> weiboDOS = weiboService.getAllPostsNotCache();
        if (!BeanUtils.isEmpty(weiboDOS)) {
            for (WeiboDO weibo : weiboDOS
            ) {
                SearchItem searchItem = new SearchItem();
                searchItem.setUuid(UUID.randomUUID().toString().toUpperCase());
                searchItem.setType(TypeEnum.WEIBO.getName());
                searchItem.setTitle(StringUtils.delHtmlTags(weibo.getContent()));
                searchItem.setContent(StringUtils.delHtmlTags(weibo.getContent()));
                searchItem.setImage(getImgUrl(""));
                searchItem.setUrl(renFeiConfig.getDomain() + TypeEnum.WEIBO.getUrl() + "/" + weibo.getId());
                searchItem.setOriginalId(weibo.getId());
                searchItem.setDate(weibo.getReleaseTime());
                searchItemAll.add(searchItem);
            }
        }
        // == Weibo <<<< KitBox >>>>
        List<KitBoxMenus> kitBoxs = kitBoxService.getKitBoxMenus();
        if (!BeanUtils.isEmpty(kitBoxs)) {
            for (KitBoxMenus kitBox : kitBoxs
            ) {
                if (!BeanUtils.isEmpty(kitBox.getLinks())) {
                    for (LinkVO link : kitBox.getLinks()
                    ) {
                        SearchItem searchItem = new SearchItem();
                        searchItem.setUuid(UUID.randomUUID().toString().toUpperCase());
                        searchItem.setType(TypeEnum.KITBOX.getName());
                        searchItem.setTitle(link.getText());
                        searchItem.setContent(link.getRel());
                        searchItem.setImage(getImgUrl(""));
                        searchItem.setUrl(renFeiConfig.getDomain() + link.getHref());
                        searchItem.setOriginalId(0L);
                        searchItem.setDate(new Date());
                        searchItemAll.add(searchItem);
                    }
                }
            }
        }
        // == KitBox <<<<
        return searchItemAll;
    }

    public List<SearchItem> getAllDataBySearchItem() {
        List<SearchItem> searchItemAll = getAllDataBySearchItemSite();
        if (searchItemAll == null) {
            searchItemAll = new ArrayList<>();
        }
        // == Discuz >>>>
        List<DiscuzForumPostDO> discuzForumPostList = discuzService.getAllPost();
        if (!BeanUtils.isEmpty(discuzForumPostList)) {
            for (DiscuzForumPostDO post : discuzForumPostList
            ) {
                SearchItem searchItem = new SearchItem();
                searchItem.setUuid(UUID.randomUUID().toString().toUpperCase());
                searchItem.setType(TypeEnum.DISCUZ.getName());
                searchItem.setTitle(post.getSubject());
                searchItem.setContent(StringUtils.delHtmlTags(post.getMessage()));
                searchItem.setImage(getImgUrl(null));
                searchItem.setUrl(TypeEnum.DISCUZ.getUrl() + "/thread-" + post.getTid() + "-1-1.html");
                searchItem.setOriginalId(Long.valueOf(post.getTid()));
                searchItem.setDate(new Date((long) post.getDateline() * 1000));
                searchItemAll.add(searchItem);
            }
        }
        // == Discuz <<<<
        return searchItemAll;
    }

    private String getImgUrl(String url) {
        if (BeanUtils.isEmpty(url)) {
            return "https://cdn.renfei.net/images/default_posts.jpg";
        }
        return url;
    }
}
