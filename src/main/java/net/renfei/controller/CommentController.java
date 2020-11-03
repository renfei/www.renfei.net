package net.renfei.controller;

import lombok.extern.slf4j.Slf4j;
import net.renfei.annotation.SystemLog;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.*;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.utils.IpUtils;
import net.renfei.service.CommentsService;
import net.renfei.service.GlobalService;
import net.renfei.service.IpService;
import net.renfei.service.PaginationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>Title: CommentController</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@RestController
@RequestMapping("/api/comments")
public class CommentController extends BaseController {
    private final IpService ipService;

    protected CommentController(IpService ipService,
                                RenFeiConfig renFeiConfig,
                                GlobalService globalService,
                                CommentsService commentsService,
                                PaginationService paginationService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.ipService = ipService;
    }

    /**
     * 评论
     *
     * @param typeid  类别ID
     * @param id      被评论的文章ID
     * @param comment 评论实体
     * @return
     */
    @PostMapping("{typeid}/{id}")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.OPENAPI, logType = LogType.ADD, logDesc = "进行评论")
    public APIResult submitComments(@PathVariable("typeid") String typeid,
                                    @PathVariable("id") String id,
                                    CommentVO comment) {
        //[TODO]检查全局评论开关
        //[TODO]检查被评论的对象是否允许评论
        Long type = -1L, ID = -1L;
        try {
            type = Long.valueOf(typeid);
            ID = Long.valueOf(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("Failure of parameter conversion")
                    .build();
        }
        CommentDTO commentDTO = comment.convert();
        commentDTO.setTypeId(type);
        commentDTO.setTargetId(ID);
        commentDTO.setAddtime(new Date());
        String ip = IpUtils.getIpAddress(request);
        commentDTO.setAuthorIp(ip);
        IpInfoDTO ipInfoDTO = ipService.query(ip);
        if (ipInfoDTO != null) {
            commentDTO.setAuthorAddress(ipInfoDTO.getCity() + ", " + ipInfoDTO.getRegion() + ", " + ipInfoDTO.getCountryLong());
        }
        int i = commentsService.submi(commentDTO);
        if (i > 0) {
            commentsService.sendNotify(commentDTO);
            return APIResult.success();
        }
        return APIResult.builder()
                .code(StateCode.Failure)
                .message(StateCode.Failure.getDescribe())
                .build();
    }
}
