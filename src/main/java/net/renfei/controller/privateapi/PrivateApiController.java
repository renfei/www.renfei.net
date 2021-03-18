package net.renfei.controller.privateapi;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.renfei.annotation.SystemLog;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.*;
import net.renfei.repository.entity.CategoryDO;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * <p>Title: PrivateApiController</p>
 * <p>Description: 私有接口</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@ApiIgnore
@RestController
@RequestMapping("/private/api")
public class PrivateApiController extends BaseController {
    private final PostService postService;
    private final MailService mailService;
    private final WeiboService weiboService;

    protected PrivateApiController(RenFeiConfig renFeiConfig,
                                   GlobalService globalService,
                                   CommentsService commentsService,
                                   PaginationService paginationService,
                                   PostService postService,
                                   MailService mailService,
                                   WeiboService weiboService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.postService = postService;
        this.mailService = mailService;
        this.weiboService = weiboService;
    }

    @PostMapping("sendEmail")
    @PreAuthorize("hasAuthority('email:send')")
    public APIResult sendEmailDo(@RequestBody SendEmailVO sendEmailVO) {
        try {
            mailService.send(sendEmailVO.getTo(), sendEmailVO.getName(), sendEmailVO.getSubject(), sendEmailVO.getContents());
            return APIResult.success();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            return APIResult.builder()
                    .code(StateCode.Error)
                    .message(exception.getMessage())
                    .build();
        }
    }

    @PostMapping("post")
    @PreAuthorize("hasAuthority('post:add')")
    public APIResult newPost(NewPostVO newPostVO) {
        try {
            postService.addPost(newPostVO);
            return APIResult.success();
        } catch (Exception exception) {
            return APIResult.builder()
                    .code(StateCode.Error)
                    .message(exception.getMessage())
                    .build();
        }
    }

    @PutMapping("cleanAllPostCache")
    @PreAuthorize("hasAuthority('post:add')")
    public APIResult cleanAllPostCache() {
        try {
            postService.cleanAll();
            return APIResult.success();
        } catch (Exception exception) {
            return APIResult.builder()
                    .code(StateCode.Error)
                    .message(exception.getMessage())
                    .build();
        }
    }

    @PostMapping("weibo")
    @PreAuthorize("hasAuthority('weibo:add')")
    public APIResult newWeibo(NewWeiboVO newWeiboVO) {
        try {
            weiboService.addWeibo(newWeiboVO);
            weiboService.cleanAll();
            return APIResult.success();
        } catch (Exception exception) {
            return APIResult.builder()
                    .code(StateCode.Error)
                    .message(exception.getMessage())
                    .build();
        }
    }

    @PreAuthorize("hasRole('ROLE_backstage')")
    @RequestMapping("ueditor/controller")
    public String ueditorConfig(@RequestParam("action") String action, MultipartFile upfile) throws Exception {
        if ("config".equals(action)) {
            return "{" +
                    "    \"imageActionName\": \"uploadimage\", " +
                    "    \"imageFieldName\": \"upfile\", " +
                    "    \"imageMaxSize\": 2048000, " +
                    "    \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], " +
                    "    \"imageCompressEnable\": true, " +
                    "    \"imageCompressBorder\": 1600, " +
                    "    \"imageInsertAlign\": \"none\", " +
                    "    \"imageUrlPrefix\": \"\", " +
                    "    \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"scrawlActionName\": \"uploadscrawl\", " +
                    "    \"scrawlFieldName\": \"upfile\", " +
                    "    \"scrawlPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"scrawlMaxSize\": 2048000, " +
                    "    \"scrawlUrlPrefix\": \"\", " +
                    "    \"scrawlInsertAlign\": \"none\"," +
                    "    \"snapscreenActionName\": \"uploadimage\", " +
                    "    \"snapscreenPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"snapscreenUrlPrefix\": \"\", " +
                    "    \"snapscreenInsertAlign\": \"none\", " +
                    "    \"catcherLocalDomain\": [\"127.0.0.1\", \"localhost\", \"img.baidu.com\"]," +
                    "    \"catcherActionName\": \"catchimage\", " +
                    "    \"catcherFieldName\": \"source\", " +
                    "    \"catcherPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"catcherUrlPrefix\": \"\", " +
                    "    \"catcherMaxSize\": 2048000, " +
                    "    \"catcherAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], " +
                    "    \"videoActionName\": \"uploadvideo\", " +
                    "    \"videoFieldName\": \"upfile\", " +
                    "    \"videoPathFormat\": \"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"videoUrlPrefix\": \"\", " +
                    "    \"videoMaxSize\": 102400000, " +
                    "    \"videoAllowFiles\": [" +
                    "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\"," +
                    "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"], " +
                    "    \"fileActionName\": \"uploadfile\", " +
                    "    \"fileFieldName\": \"upfile\", " +
                    "    \"filePathFormat\": \"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\", " +
                    "    \"fileUrlPrefix\": \"\", " +
                    "    \"fileMaxSize\": 51200000, " +
                    "    \"fileAllowFiles\": [" +
                    "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"," +
                    "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\"," +
                    "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"," +
                    "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\"," +
                    "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"" +
                    "    ], " +
                    "    \"imageManagerActionName\": \"listimage\", " +
                    "    \"imageManagerListPath\": \"/ueditor/jsp/upload/image/\", " +
                    "    \"imageManagerListSize\": 20, " +
                    "    \"imageManagerUrlPrefix\": \"\", " +
                    "    \"imageManagerInsertAlign\": \"none\", " +
                    "    \"imageManagerAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], " +
                    "    \"fileManagerActionName\": \"listfile\", " +
                    "    \"fileManagerListPath\": \"/ueditor/jsp/upload/file/\", " +
                    "    \"fileManagerUrlPrefix\": \"\", " +
                    "    \"fileManagerListSize\": 20, " +
                    "    \"fileManagerAllowFiles\": [" +
                    "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"," +
                    "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\"," +
                    "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"," +
                    "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\"," +
                    "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"" +
                    "    ] " +
                    "}";
        } else if ("uploadimage".equals(action) || "uploadscrawl".equals(action)) {
            // 上传图片
            return JSON.toJSONString(postService.uEditorUpload(upfile));
        }
        return "Unsupported action. -By RenFei.Net";
    }
}
