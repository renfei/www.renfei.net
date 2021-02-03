package net.renfei.controller.privateapi;

import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.NewPostVO;
import net.renfei.entity.NewWeiboVO;
import net.renfei.entity.SendEmailVO;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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

    @PostMapping("newPost")
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

    @PostMapping("newWeibo")
    @PreAuthorize("hasAuthority('weibo:add')")
    public APIResult newWeibo(NewWeiboVO newWeiboVO) {
        try {
            weiboService.addWeibo(newWeiboVO);
            return APIResult.success();
        } catch (Exception exception) {
            return APIResult.builder()
                    .code(StateCode.Error)
                    .message(exception.getMessage())
                    .build();
        }
    }
}
