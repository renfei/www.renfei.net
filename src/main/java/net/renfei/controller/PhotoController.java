package net.renfei.controller;

import net.renfei.annotation.SystemLog;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.*;
import net.renfei.repository.entity.PhotoDOWithBLOBs;
import net.renfei.service.CommentsService;
import net.renfei.service.GlobalService;
import net.renfei.service.PaginationService;
import net.renfei.service.PhotoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
@RequestMapping("/photo")
public class PhotoController extends BaseController {
    private final PhotoService photoService;

    protected PhotoController(RenFeiConfig renFeiConfig,
                              GlobalService globalService,
                              CommentsService commentsService,
                              PaginationService paginationService,
                              PhotoService photoService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.photoService = photoService;
    }

    @RequestMapping("")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取相册列表页面")
    public ModelAndView getAllPhotoList(@RequestParam(value = "page", required = false) String page,
                                        ModelAndView mv) {
        setHead(mv, "任霏的网络相册");
        PhotoListDTO photoListDTO = photoService.getAllPhotos(page, "10");
        mv.addObject("photoList", photoListDTO.getPhotoDOWithBLOBs());
        setPagination(mv, page, photoListDTO.getCount(), "/photo?page=");
        mv.addObject("title", "任霏的网络相册 - " + renFeiConfig.getSiteName());
        mv.setViewName("photo/index");
        return mv;
    }

    @RequestMapping("{id}")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.CMS, logType = LogType.GET, logDesc = "获取相册详情页面")
    public ModelAndView getPhotoById(@PathVariable("id") String id, ModelAndView mv) throws NoHandlerFoundException {
        PhotoDOWithBLOBs photoDTO = photoService.getPhotoById(id);
        PhotoImgDTO photoImgDTO = photoService.getPhotoImgByPhotoId(id);
        if (photoImgDTO != null) {
            mv.addObject("photo", photoDTO);
            mv.addObject("photoImg", photoImgDTO);
            setHead(mv, photoDTO.getDescribes());
        } else {
            throwNoHandlerFoundException();
        }
        mv.addObject("title", photoDTO.getTitle() + " - 任霏的网络相册 - " + renFeiConfig.getSiteName());
        mv.setViewName("photo/show");
        return mv;
    }
}
