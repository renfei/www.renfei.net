package net.renfei.controller;

import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.ShareVO;
import net.renfei.entity.VideoDTO;
import net.renfei.entity.VideoListDTO;
import net.renfei.repository.entity.VideoDOWithBLOBs;
import net.renfei.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: VideoController</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Controller
@RequestMapping("/video")
public class VideoController extends BaseController {
    private final VideoService videoService;
    private final CategoryService categoryService;

    protected VideoController(RenFeiConfig renFeiConfig,
                              GlobalService globalService,
                              CommentsService commentsService,
                              PaginationService paginationService,
                              VideoService videoService,
                              CategoryService categoryService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.videoService = videoService;
        this.categoryService = categoryService;
    }

    @RequestMapping("")
    public ModelAndView getAllVideoList(@RequestParam(value = "page", required = false) String page,
                                        ModelAndView mv) {
        VideoListDTO videoListDTO = videoService.getAllVideo(page, "10");
        List<VideoDTO> videoDTOList = new ArrayList<>();
        for (VideoDOWithBLOBs v : videoListDTO.getVideos()
        ) {
            VideoDTO videoDTO = new VideoDTO();
            BeanUtils.copyProperties(v, videoDTO);
            videoDTOList.add(videoDTO);
        }
        setHead(mv, "任霏的视频博客（Vlog）频道，自制视频记录生活。", "任霏,视频,博客,RenFei,Vlog");
        setPagination(mv, page, videoListDTO.getCount(), "/video?page=");
        mv.addObject("videoDTOList", videoDTOList);
        mv.addObject("title", "任霏的视频博客 - Vlog - " + renFeiConfig.getSiteName());
        mv.setViewName("video/index");
        return mv;
    }

    @RequestMapping("{id}")
    public ModelAndView playVideo(@PathVariable("id") String id, ModelAndView mv) throws NoHandlerFoundException {
        VideoDTO videoDTO = videoService.getVideoDTOByID(id);
        if (videoDTO != null) {
            mv.addObject("video", videoDTO);
            setHead(mv, videoDTO.getDescribes(), videoDTO.getTitle());
            setComment(mv, 3L, videoDTO.getId());
            ShareVO shareVO = new ShareVO();
            shareVO.setTitle(videoDTO.getTitle());
            shareVO.setUrl(renFeiConfig.getDomain() + "/video/" + videoDTO.getId());
            shareVO.setDescribes(videoDTO.getDescribes());
            shareVO.setPics(videoDTO.getFeaturedImage());
            mv.addObject("sharevo", shareVO);
            mv.addObject("title", videoDTO.getTitle() + " - 任霏的视频博客");
            mv.setViewName("video/play");
        } else {
            throwNoHandlerFoundException();
        }
        return mv;
    }
}
