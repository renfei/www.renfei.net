package net.renfei.service;

import com.github.pagehelper.PageHelper;
import net.renfei.base.BaseService;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.CommentDTO;
import net.renfei.repository.CommentsDOMapper;
import net.renfei.repository.entity.*;
import net.renfei.sdk.utils.ListUtils;
import net.renfei.service.aliyun.AliyunGreen;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: CommentsService</p>
 * <p>Description: 评论服务</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Service
@CacheConfig(cacheNames = "CommentsService")
public class CommentsService extends BaseService {
    private final TypeService typeService;
    private final AliyunGreen aliyunGreen;
    private final MailService mailService;
    private final PostService postService;
    private final RenFeiConfig renFeiConfig;
    private final VideoService videoService;
    private final CommentsDOMapper commentsDOMapper;

    public CommentsService(TypeService typeService,
                           AliyunGreen aliyunGreen,
                           MailService mailService,
                           PostService postService,
                           RenFeiConfig renFeiConfig,
                           VideoService videoService,
                           CommentsDOMapper commentsDOMapper) {
        this.typeService = typeService;
        this.aliyunGreen = aliyunGreen;
        this.mailService = mailService;
        this.postService = postService;
        this.renFeiConfig = renFeiConfig;
        this.videoService = videoService;
        this.commentsDOMapper = commentsDOMapper;
    }

    @Cacheable
    public List<CommentsDOWithBLOBs> getLastComment() {
        CommentsDOExample commentDOExample = new CommentsDOExample();
        commentDOExample.setOrderByClause("addtime DESC");
        commentDOExample.createCriteria()
                .andIsDeleteEqualTo(false)
                .andParentIdIsNull();
        PageHelper.startPage(1, 10);
        return commentsDOMapper.selectByExampleWithBLOBs(commentDOExample);
    }

    @Cacheable
    public List<CommentDTO> getComment(Long typeid, Long id) {
        CommentsDOExample commentsDOExample = new CommentsDOExample();
        commentsDOExample.createCriteria()
                .andTypeIdEqualTo(typeid)
                .andTargetIdEqualTo(id)
                .andIsDeleteEqualTo(false)
                .andParentIdIsNull();
        List<CommentsDOWithBLOBs> commentsDOWithBLOBs = commentsDOMapper.selectByExampleWithBLOBs(commentsDOExample);
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(commentsDOWithBLOBs)) {
            return null;
        }
        List<CommentDTO> commentDTOS = new ArrayList<>();
        commentsDOWithBLOBs.forEach(commentsDO -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(commentsDO, commentDTO);
            commentDTOS.add(commentDTO);
        });
        getCommentByParentID(commentDTOS);
        return commentDTOS;
    }

    public int submi(CommentDTO comment) {
        CommentsDOWithBLOBs commentsDOWithBLOBs = new CommentsDOWithBLOBs();
        BeanUtils.copyProperties(comment, commentsDOWithBLOBs);
        commentsDOWithBLOBs.setIsDelete(true);
        //核验检查评论内容
        audit(commentsDOWithBLOBs);
        return commentsDOMapper.insertSelective(commentsDOWithBLOBs);
    }

    public void audit(CommentsDOWithBLOBs commentsDOWithBLOBs) {
        commentsDOWithBLOBs.setIsDelete(!aliyunGreen.textScan(commentsDOWithBLOBs.getContent()));
    }

    public CommentDTO getCommentById(Long id) {
        CommentsDOExample commentsDOExample = new CommentsDOExample();
        commentsDOExample.createCriteria()
                .andIdEqualTo(id);
        CommentsDOWithBLOBs commentDO = ListUtils.getOne(commentsDOMapper.selectByExampleWithBLOBs(commentsDOExample));
        if (commentDO != null) {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(commentDO, commentDTO);
            return commentDTO;
        } else {
            return null;
        }
    }

    @Async
    public void sendNotify(CommentDTO comment) {
        String sent = "", sentName = "";
        CommentDTO commentDTO = null;
        if (comment.getParentId() == null || comment.getParentId() == 0L) {
            sent = "i@renfei.net";
            sentName = "RenFei";
        } else {
            commentDTO = getCommentById(comment.getParentId());
            if (commentDTO != null) {
                sent = commentDTO.getAuthorEmail();
                sentName = commentDTO.getAuthor();
            } else {
                return;
            }
        }
        List<String> stringList = new ArrayList<>();
        stringList.add("很高兴能与你取得联系。您的评论留言收到了回复：");
        stringList.add(comment.getContent());
        stringList.add("----");
        String link = renFeiConfig.getDomain();
        TypeDO typeDO = typeService.getTypeByID(comment.getTypeId());
        if (typeDO == null) {
            return;
        }
        link += typeDO.getUriPath() + "/" + comment.getTargetId();
        switch (typeDO.getTypeName()) {
            case "Posts":
                PostsDOWithBLOBs post = postService.getPostById(comment.getTargetId());
                stringList.add("回顾：<a href=\"" + link + "\">" + post.getTitle() + "</a>");
                break;
            case "Video":
                VideoDOWithBLOBs video = videoService.getVideoByID(comment.getTargetId().toString());
                stringList.add("回顾：<a href=\"" + link + "\">" + video.getTitle() + "</a>");
                break;
            default:
                return;
        }

        if (commentDTO != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            stringList.add("在 " + sdf.format(commentDTO.getAddtime()) + " 时您写到：");
            stringList.add(commentDTO.getContent());
        }
        stringList.add("Visit Topic to respond.");
        mailService.send(sent, sentName, "您在[RENFEI.NET]的评论留言有新的回复消息", stringList);
    }

    private void getCommentByParentID(List<CommentDTO> commentDTOS) {
        if (commentDTOS != null && commentDTOS.size() > 0) {
            for (CommentDTO commentDTO : commentDTOS
            ) {
                CommentsDOExample commentsDOExample = new CommentsDOExample();
                commentsDOExample.createCriteria()
                        .andParentIdEqualTo(commentDTO.getId());
                List<CommentsDOWithBLOBs> commentsDOWithBLOBs = commentsDOMapper.selectByExampleWithBLOBs(commentsDOExample);
                if (net.renfei.sdk.utils.BeanUtils.isEmpty(commentsDOWithBLOBs)) {
                    continue;
                }
                List<CommentDTO> child = new ArrayList<>();
                commentsDOWithBLOBs.forEach(commentsDO -> {
                    CommentDTO commentChildDTO = new CommentDTO();
                    BeanUtils.copyProperties(commentsDO, commentChildDTO);
                    child.add(commentChildDTO);
                });
                if (child.size() > 0) {
                    getCommentByParentID(child);
                    commentDTO.setChild(child);
                }
            }
        }
    }
}
