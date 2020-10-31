package net.renfei.controller;

import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.*;
import net.renfei.repository.entity.PostsDOWithBLOBs;
import net.renfei.repository.entity.VideoDOWithBLOBs;
import net.renfei.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: CatController</p>
 * <p>Description: 分类</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Controller
@RequestMapping("/cat")
public class CatController extends BaseController {
    private final PostService postService;
    private final VideoService videoService;
    private final PhotoService photoService;
    private final CategoryService categoryService;

    protected CatController(RenFeiConfig renFeiConfig,
                            GlobalService globalService,
                            CommentsService commentsService,
                            PaginationService paginationService,
                            PostService postService,
                            VideoService videoService,
                            PhotoService photoService,
                            CategoryService categoryService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.postService = postService;
        this.videoService = videoService;
        this.photoService = photoService;
        this.categoryService = categoryService;
    }

    @RequestMapping("{typeName}/{enName}")
    public ModelAndView getList(
            @PathVariable(value = "typeName") String typeName,
            @PathVariable(value = "enName") String enName,
            @Param(value = "page") String page,
            ModelAndView mv) throws NoHandlerFoundException {
        CategoryDTO categoryDTO = categoryService.getCategoryByEnNaeme(enName);
        if (categoryDTO != null) {
            switch (typeName.toLowerCase()) {
                case "posts":
                    ListData<PostsDOWithBLOBs> postsListDTO = postService.getAllPostsByCatID(categoryDTO.getId(), page, "15");

                    setPagination(mv, page, postsListDTO.getTotal(), "/cat/" + typeName + "/" + enName + "?page=");
                    mv.addObject("title", categoryDTO.getZhName() + " - 文档分类 - " + renFeiConfig.getSiteName());
                    mv.addObject("catTitle", categoryDTO.getZhName());
                    mv.addObject("lists", postsListDTO.getData());
                    mv.addObject("PostSidebar", postService.getPostSidebar());
                    mv.setViewName("posts/list");
                    break;
                case "video":
                    VideoListDTO videoListDTO = videoService.getAllVideoByCatID(categoryDTO.getId(), page, "10");
                    List<VideoDTO> videoDTOList = new ArrayList<>();
                    for (VideoDOWithBLOBs v : videoListDTO.getVideos()
                    ) {
                        VideoDTO videoDTO = new VideoDTO();
                        BeanUtils.copyProperties(v, videoDTO);
                        videoDTOList.add(videoDTO);
                    }
                    setHead(mv, "任霏的视频博客（Vlog）频道，自制视频记录生活。", "任霏,视频,博客,RenFei,Vlog");
                    setPagination(mv, page, videoListDTO.getCount(), "/cat/" + typeName + "/" + enName + "?page=");
                    mv.addObject("videoDTOList", videoDTOList);
                    mv.addObject("title", categoryDTO.getZhName() + " - 视频分类 - " + renFeiConfig.getSiteName());
                    mv.setViewName("video/index");
                    break;
                case "photo":
                    setHead(mv, "Photo", "The RenFei Web Albums");
                    PhotoListDTO photoListDTO = photoService.getAllPhotosCatID(categoryDTO.getId(), page, "10");
                    mv.addObject("photoList", photoListDTO.getPhotoDOWithBLOBs());
                    setPagination(mv, page, photoListDTO.getCount(), "/cat/" + typeName + "/" + enName + "?page=");
                    mv.addObject("title", categoryDTO.getZhName() + " - 相册分类 - " + renFeiConfig.getSiteName());
                    mv.setViewName("photo/index");
                    break;
                default:
                    throwNoHandlerFoundException();
                    break;
            }
        } else {
            throwNoHandlerFoundException();
        }
        return mv;
    }
}
