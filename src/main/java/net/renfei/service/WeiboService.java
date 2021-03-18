package net.renfei.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.base.BaseService;
import net.renfei.entity.NewWeiboVO;
import net.renfei.entity.WeiboDOS;
import net.renfei.entity.WeiboDTO;
import net.renfei.repository.PhotoImgDOMapper;
import net.renfei.repository.WeiboDOMapper;
import net.renfei.repository.entity.PhotoImgDO;
import net.renfei.repository.entity.WeiboDO;
import net.renfei.repository.entity.WeiboDOExample;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.DateUtils;
import net.renfei.sdk.utils.ListUtils;
import net.renfei.sdk.utils.NumberUtils;
import net.renfei.service.aliyun.AliyunOSS;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: WeiboService</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Service
@CacheConfig(cacheNames = "WeiboService")
public class WeiboService extends BaseService {
    private final AliyunOSS aliyunOSS;
    private final PhotoService photoService;
    private final WeiboDOMapper weiboDOMapper;
    private final PhotoImgDOMapper photoImgDOMapper;

    public WeiboService(AliyunOSS aliyunOSS,
                        PhotoService photoService,
                        WeiboDOMapper weiboDOMapper,
                        PhotoImgDOMapper photoImgDOMapper) {
        this.aliyunOSS = aliyunOSS;
        this.photoService = photoService;
        this.weiboDOMapper = weiboDOMapper;
        this.photoImgDOMapper = photoImgDOMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public void addWeibo(NewWeiboVO newWeiboVO) throws Exception {
        PhotoImgDO photoImgDO = null;
        if (newWeiboVO.getImage() != null && (!newWeiboVO.getImage().isEmpty())) {
            String photoPath = aliyunOSS.upload("upload/image/" + DateUtils.getYear() + "/", newWeiboVO.getImage());
            photoImgDO = new PhotoImgDO();
            photoImgDO.setPhotoId(7L);
            photoImgDO.setUri(photoPath);
            photoImgDOMapper.insert(photoImgDO);
        }
        WeiboDO weiboDO = new WeiboDO();
        weiboDO.setContent(newWeiboVO.getContent());
        weiboDO.setReleaseTime(new Date());
        weiboDO.setThumbsDown(0L);
        weiboDO.setThumbsUp(0L);
        weiboDO.setViews(0L);
        if (photoImgDO != null) {
            weiboDO.setImgId(photoImgDO.getId());
        }
        weiboDOMapper.insertSelective(weiboDO);
    }

    /**
     * 获取微博列表
     *
     * @param pages 页码
     * @param rows  每页容量
     * @return
     */
    @Cacheable
    public WeiboDTO getAllPosts(String pages, String rows) {
        return getAllPosts(pages, rows, "release_time DESC");
    }

    /**
     * 获取微博列表
     *
     * @param pages 页码
     * @param rows  每页容量
     * @return
     */
    @Cacheable
    public WeiboDTO getAllPosts(String pages, String rows, String orderBy) {
        int intPage = NumberUtils.parseInt(pages, 1),
                intRows = NumberUtils.parseInt(rows, 10);
        WeiboDOExample weiboDOExample = new WeiboDOExample();
        weiboDOExample.setOrderByClause(orderBy);
        weiboDOExample
                .createCriteria()
                .andReleaseTimeLessThanOrEqualTo(new Date());
        return doSelect(weiboDOExample, intPage, intRows);
    }

    @CacheEvict(value = "WeiboService", allEntries = true)
    public void cleanAll() {
    }

    public List<WeiboDO> getAllPostsNotCache() {
        WeiboDOExample weiboDOExample = new WeiboDOExample();
        weiboDOExample.setOrderByClause("release_time DESC");
        weiboDOExample
                .createCriteria()
                .andReleaseTimeLessThanOrEqualTo(new Date());
        return weiboDOMapper.selectByExample(weiboDOExample);
    }

    /**
     * 执行查询并转换
     *
     * @param weiboDOExample
     * @param ipage
     * @param rows
     * @return
     */
    private WeiboDTO doSelect(WeiboDOExample weiboDOExample, int ipage, int rows) {
        Page page = PageHelper.startPage(ipage, rows);
        List<WeiboDO> weiboDOList = weiboDOMapper.selectByExample(weiboDOExample);
        return convert(weiboDOList, page.getTotal());
    }

    @Cacheable
    public WeiboDO getWeiboById(String id, boolean recordViews) {
        Long ID = 0L;
        if (!BeanUtils.isEmpty(id)) {
            try {
                ID = Long.valueOf(id);
                WeiboDOExample weiboDOExample = new WeiboDOExample();
                weiboDOExample.createCriteria()
                        .andIdEqualTo(ID)
                        .andReleaseTimeLessThanOrEqualTo(new Date());
                WeiboDO weiboDO = ListUtils.getOne(weiboDOMapper.selectByExample(weiboDOExample));
                if (weiboDO != null) {
                    if (recordViews) {
                        updateView(weiboDO);
                    }
                    return weiboDO;
                } else {
                    return null;
                }
            } catch (NumberFormatException nfe) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Async
    public void updateView(WeiboDO weiboDO) {
        weiboDO.setViews(weiboDO.getViews() + 1);
        update(weiboDO);
    }

    public void update(WeiboDO weiboDO) {
        weiboDOMapper.updateByPrimaryKeySelective(weiboDO);
    }

    private WeiboDTO convert(List<WeiboDO> weiboDOList, Long count) {
        WeiboDTO weiboDTO = new WeiboDTO();
        List<WeiboDOS> weiboDOSList = new ArrayList<>();
        for (WeiboDO weibo : weiboDOList
        ) {
            WeiboDOS weiboDOS = new WeiboDOS();
            org.springframework.beans.BeanUtils.copyProperties(weibo, weiboDOS);
            setImg(weiboDOS);
            updateView(weibo);
            weiboDOSList.add(weiboDOS);
        }
        weiboDTO.setWeiboDOList(weiboDOSList);
        weiboDTO.setCount(count);
        return weiboDTO;
    }

    public void setImg(WeiboDOS weiboDOS) {
        if (weiboDOS.getImgId() != null) {
            PhotoImgDO photoImgDO = photoService.getPhotoImgById(weiboDOS.getImgId().toString());
            if (photoImgDO != null) {
                weiboDOS.setImg(photoImgDO.getUri());
            }
        }
    }
}
