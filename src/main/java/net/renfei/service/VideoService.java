package net.renfei.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.base.BaseService;
import net.renfei.entity.VideoDTO;
import net.renfei.entity.VideoListDTO;
import net.renfei.repository.VideoDOMapper;
import net.renfei.repository.VideoSourceDOMapper;
import net.renfei.repository.VideoTrackDOMapper;
import net.renfei.repository.entity.*;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.ListUtils;
import net.renfei.sdk.utils.NumberUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@CacheConfig(cacheNames = "VideoService")
public class VideoService extends BaseService {
    private final VideoDOMapper videoDOMapper;
    private final VideoTrackDOMapper videoTrackDOMapper;
    private final VideoSourceDOMapper videoSourceDOMapper;

    public VideoService(VideoDOMapper videoDOMapper,
                        VideoTrackDOMapper videoTrackDOMapper,
                        VideoSourceDOMapper videoSourceDOMapper) {
        this.videoDOMapper = videoDOMapper;
        this.videoTrackDOMapper = videoTrackDOMapper;
        this.videoSourceDOMapper = videoSourceDOMapper;
    }

    /**
     * 获取全部视频并分页
     *
     * @param pages 页码
     * @param rows  每页容量
     * @return
     */
    @Cacheable
    public VideoListDTO getAllVideo(String pages, String rows) {
        int intPage = NumberUtils.parseInt(pages, 1),
                intRows = NumberUtils.parseInt(rows, 10);
        VideoDOExample videoDOExample = new VideoDOExample();
        videoDOExample.setOrderByClause("release_time DESC");
        videoDOExample.createCriteria()
                .andIsDeleteEqualTo(false)
                .andReleaseTimeLessThanOrEqualTo(new Date());
        Page page = PageHelper.startPage(intPage, intRows);
        List<VideoDOWithBLOBs> videoDOWithBLOBs = videoDOMapper.selectByExampleWithBLOBs(videoDOExample);
        return convert(videoDOWithBLOBs, page.getTotal());
    }

    public List<VideoDOWithBLOBs> getAllVideoNotCache() {
        VideoDOExample videoDOExample = new VideoDOExample();
        videoDOExample.setOrderByClause("release_time DESC");
        videoDOExample.createCriteria()
                .andIsDeleteEqualTo(false)
                .andReleaseTimeLessThanOrEqualTo(new Date());
        return videoDOMapper.selectByExampleWithBLOBs(videoDOExample);
    }

    @Cacheable
    public VideoListDTO getAllVideoByCatID(Long catID, String pages, String rows) {
        int intPage = NumberUtils.parseInt(pages, 1),
                intRows = NumberUtils.parseInt(rows, 10);
        VideoDOExample videoDOExample = new VideoDOExample();
        videoDOExample.setOrderByClause("release_time DESC");
        videoDOExample.createCriteria()
                .andIsDeleteEqualTo(false)
                .andReleaseTimeLessThanOrEqualTo(new Date())
                .andCategoryIdEqualTo(catID);
        Page page = PageHelper.startPage(intPage, intRows);
        List<VideoDOWithBLOBs> videoDOWithBLOBs = videoDOMapper.selectByExampleWithBLOBs(videoDOExample);
        return convert(videoDOWithBLOBs, page.getTotal());
    }

    @Cacheable
    public Long getCountByCategoryId(Long catID) {
        VideoDOExample videoDOExample = new VideoDOExample();
        videoDOExample.createCriteria()
                .andCategoryIdEqualTo(catID)
                .andIsDeleteEqualTo(false)
                .andReleaseTimeLessThanOrEqualTo(new Date());
        Page page = PageHelper.startPage(1, 1);
        videoDOMapper.selectByExampleWithBLOBs(videoDOExample);
        return page.getTotal();
    }

    /**
     * 根据ID获取视频
     *
     * @param id 视频ID
     * @return
     */
    @Cacheable
    public VideoDOWithBLOBs getVideoByID(String id, boolean updateView) {
        Long ID = 0L;
        if (!BeanUtils.isEmpty(id)) {
            try {
                ID = Long.valueOf(id);
                VideoDOExample videoDOExample = new VideoDOExample();
                videoDOExample.createCriteria()
                        .andIsDeleteEqualTo(false)
                        .andReleaseTimeLessThanOrEqualTo(new Date())
                        .andIdEqualTo(ID);
                return ListUtils.getOne(videoDOMapper.selectByExampleWithBLOBs(videoDOExample));
            } catch (NumberFormatException nfe) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 根据ID获取视频
     *
     * @param id 视频ID
     * @return
     */
    @Cacheable
    public VideoDTO getVideoDTOByID(String id, boolean updateView) {
        Long ID = 0L;
        if (!BeanUtils.isEmpty(id)) {
            try {
                ID = Long.valueOf(id);
                VideoDOExample videoDOExample = new VideoDOExample();
                videoDOExample.createCriteria()
                        .andIsDeleteEqualTo(false)
                        .andReleaseTimeLessThanOrEqualTo(new Date())
                        .andIdEqualTo(ID);
                List<VideoDOWithBLOBs> videoDOWithBLOBsList = videoDOMapper.selectByExampleWithBLOBs(videoDOExample);
                if (videoDOWithBLOBsList != null && videoDOWithBLOBsList.size() > 0) {
                    VideoDTO videoDTO = new VideoDTO();
                    org.springframework.beans.BeanUtils.copyProperties(videoDOWithBLOBsList.get(0), videoDTO);
                    videoDTO.setVideoSource(getvideoSourceByVideoId(videoDTO.getId()));
                    videoDTO.setVideoTrack(getVideoTrackByVideoId(videoDTO.getId()));
                    if (updateView) {
                        updateView(videoDOWithBLOBsList.get(0));
                    }
                    return videoDTO;
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

    /**
     * 根据ID获取视频
     *
     * @param id 视频ID
     * @return
     */
    @Cacheable
    public VideoDOWithBLOBs getVideoByID(String id) {
        return getVideoByID(id, true);
    }

    @Cacheable
    public VideoDTO getVideoDTOByID(String id) {
        return getVideoDTOByID(id, true);
    }

    @Cacheable
    public List<VideoSourceDOWithBLOBs> getvideoSourceByVideoId(Long videoId) {
        VideoSourceDOExample videoSourceDOExample = new VideoSourceDOExample();
        videoSourceDOExample.createCriteria()
                .andVideoIdEqualTo(videoId);
        return videoSourceDOMapper.selectByExampleWithBLOBs(videoSourceDOExample);
    }

    @Cacheable
    public List<VideoTrackDOWithBLOBs> getVideoTrackByVideoId(Long videoId) {
        VideoTrackDOExample videoTrackDOExample = new VideoTrackDOExample();
        videoTrackDOExample.createCriteria()
                .andVideoIdEqualTo(videoId);
        return videoTrackDOMapper.selectByExampleWithBLOBs(videoTrackDOExample);
    }

    private VideoListDTO convert(List<VideoDOWithBLOBs> videoDOWithBLOBs, Long count) {
        VideoListDTO videoListDTO = new VideoListDTO();
        videoListDTO.setVideos(videoDOWithBLOBs);
        videoListDTO.setCount(count);
        return videoListDTO;
    }

    /**
     * 视频浏览量自增
     *
     * @param videoDOWithBLOBs
     */
    @Async
    protected void updateView(VideoDOWithBLOBs videoDOWithBLOBs) {
        videoDOWithBLOBs.setViews(videoDOWithBLOBs.getViews() + 1);
        videoDOMapper.updateByPrimaryKeySelective(videoDOWithBLOBs);
    }
}
