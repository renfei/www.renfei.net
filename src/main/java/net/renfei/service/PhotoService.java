package net.renfei.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.base.BaseService;
import net.renfei.entity.PhotoImgDTO;
import net.renfei.entity.PhotoListDTO;
import net.renfei.repository.PhotoDOMapper;
import net.renfei.repository.PhotoImgDOMapper;
import net.renfei.repository.entity.PhotoDOExample;
import net.renfei.repository.entity.PhotoDOWithBLOBs;
import net.renfei.repository.entity.PhotoImgDO;
import net.renfei.repository.entity.PhotoImgDOExample;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.ListUtils;
import net.renfei.sdk.utils.NumberUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: PhotoService</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Service
@CacheConfig(cacheNames = "PhotoService")
public class PhotoService extends BaseService {
    private final PhotoDOMapper photoDOMapper;
    private final PhotoImgDOMapper photoImgDOMapper;

    public PhotoService(PhotoDOMapper photoDOMapper,
                        PhotoImgDOMapper photoImgDOMapper) {
        this.photoDOMapper = photoDOMapper;
        this.photoImgDOMapper = photoImgDOMapper;
    }

    @Cacheable
    public PhotoImgDO getPhotoImgById(String id) {
        Long ID = 0L;
        if (!BeanUtils.isEmpty(id)) {
            try {
                ID = Long.valueOf(id);
            } catch (Exception e) {
                return null;
            }
            PhotoImgDOExample photoImgDOExample = new PhotoImgDOExample();
            photoImgDOExample.createCriteria()
                    .andIdEqualTo(ID);
            return ListUtils.getOne(photoImgDOMapper.selectByExampleWithBLOBs(photoImgDOExample));
        } else {
            return null;
        }
    }

    @Cacheable
    public PhotoDOWithBLOBs getPhotoById(String id) {
        Long ID = 0L;
        if (!BeanUtils.isEmpty(id)) {
            try {
                ID = Long.valueOf(id);
                PhotoDOExample photoDOExample = new PhotoDOExample();
                photoDOExample.createCriteria()
                        .andIdEqualTo(ID)
                        .andReleaseTimeLessThanOrEqualTo(new Date())
                        .andIsDeleteEqualTo(false);
                return ListUtils.getOne(photoDOMapper.selectByExampleWithBLOBs(photoDOExample));
            } catch (NumberFormatException nfe) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Cacheable
    public PhotoListDTO getAllPhotosCatID(Long catID, String pages, String rows) {
        int intPage = NumberUtils.parseInt(pages, 1),
                intRows = NumberUtils.parseInt(rows, 10);
        PhotoDOExample photoDOExample = new PhotoDOExample();
        photoDOExample.setOrderByClause("release_time DESC");
        photoDOExample
                .createCriteria()
                .andIsDeleteEqualTo(false)
                .andReleaseTimeLessThanOrEqualTo(new Date())
                .andCategoryIdEqualTo(catID);
        Page page = PageHelper.startPage(intPage, intRows);
        List<PhotoDOWithBLOBs> photoDOWithBLOBs = photoDOMapper.selectByExampleWithBLOBs(photoDOExample);

        return convert(photoDOWithBLOBs, page.getTotal());
    }

    @Cacheable
    public PhotoImgDTO getPhotoImgByPhotoId(String id) {
        Long ID = 0L;
        if (!BeanUtils.isEmpty(id)) {
            try {
                ID = Long.valueOf(id);
                PhotoDOExample photoDOExample = new PhotoDOExample();
                photoDOExample.createCriteria()
                        .andIdEqualTo(ID)
                        .andReleaseTimeLessThanOrEqualTo(new Date())
                        .andIsDeleteEqualTo(false);
                List<PhotoDOWithBLOBs> photoDOWithBLOBs = photoDOMapper.selectByExampleWithBLOBs(photoDOExample);
                if (photoDOWithBLOBs != null && photoDOWithBLOBs.size() > 0) {
                    PhotoImgDOExample photoImgDOExample = new PhotoImgDOExample();
                    photoImgDOExample.createCriteria()
                            .andPhotoIdEqualTo(ID);
                    List<PhotoImgDO> photoImgDOS = photoImgDOMapper.selectByExampleWithBLOBs(photoImgDOExample);
                    PhotoImgDTO photoImgDTO = new PhotoImgDTO();
                    photoImgDTO.setPhotoImgs(photoImgDOS);
                    return photoImgDTO;
                } else {
                    return null;
                }
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Cacheable
    public PhotoListDTO getAllPhotos(String pages, String rows) {
        int intPage = NumberUtils.parseInt(pages, 1),
                intRows = NumberUtils.parseInt(rows, 10);
        PhotoDOExample photoDOExample = new PhotoDOExample();
        photoDOExample.setOrderByClause("release_time DESC");
        photoDOExample
                .createCriteria()
                .andIsDeleteEqualTo(false)
                .andReleaseTimeLessThanOrEqualTo(new Date());
        Page page = PageHelper.startPage(intPage, intRows);
        List<PhotoDOWithBLOBs> photoDOWithBLOBs = photoDOMapper.selectByExampleWithBLOBs(photoDOExample);

        return convert(photoDOWithBLOBs, page.getTotal());
    }

    public List<PhotoDOWithBLOBs> getAllPhotosNotCache() {
        PhotoDOExample photoDOExample = new PhotoDOExample();
        photoDOExample.setOrderByClause("release_time DESC");
        photoDOExample
                .createCriteria()
                .andIsDeleteEqualTo(false)
                .andReleaseTimeLessThanOrEqualTo(new Date());
        return photoDOMapper.selectByExampleWithBLOBs(photoDOExample);
    }

    private PhotoListDTO convert(List<PhotoDOWithBLOBs> photoDOWithBLOBs, Long count) {
        PhotoListDTO photoListDTO = new PhotoListDTO();
        photoListDTO.setPhotoDOWithBLOBs(photoDOWithBLOBs);
        photoListDTO.setCount(count);
        return photoListDTO;
    }
}
