package net.renfei.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Data
public class CommentVO {
    private Long id;
    private Long reply;
    private String author;
    private String authoremail;
    private String link;
    private String content;
    private Date datetime;
    private String address;
    private Boolean isOwner;
    private List<CommentVO> child;

    public CommentDTO convert() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setAddtime(datetime);
        commentDTO.setIsDelete(false);
        commentDTO.setParentId(reply);
        commentDTO.setAuthor(author);
        commentDTO.setAuthorEmail(authoremail);
        commentDTO.setAuthorUrl(link);
        commentDTO.setContent(address);
        commentDTO.setContent(content);
        commentDTO.setIsOwner(isOwner);
        if (this.child != null && this.child.size() > 0) {
            List<CommentDTO> commentDTOS = new ArrayList<>();
            for (net.renfei.entity.CommentVO comment : this.child
            ) {
                CommentDTO commentDTO1 = comment.convert();
                commentDTOS.add(commentDTO1);
            }
            commentDTO.setChild(commentDTOS);
        }
        return commentDTO;
    }

    public net.renfei.entity.CommentVO convert(CommentDTO commentDTO) {
        net.renfei.entity.CommentVO commentVO = new net.renfei.entity.CommentVO();
        commentVO.setId(commentDTO.getId());
        if (commentDTO != null && commentDTO.getAuthorUrl() != null) {
            commentVO.setLink("/other/urlredirect?url="
                    + Base64.getEncoder().encodeToString(
                    commentDTO.getAuthorUrl().getBytes()
            ));
        }else {
            commentVO.setLink("javascript:void(0);");
        }
        commentVO.setDatetime(commentDTO.getAddtime());
        commentVO.setContent(commentDTO.getContent());
        commentVO.setAuthor(commentDTO.getAuthor());
        commentVO.setAddress(commentDTO.getAuthorAddress());
        commentVO.setReply(commentDTO.getParentId());
        commentVO.setIsOwner(commentDTO.getIsOwner());
        if (commentDTO.getChild() != null && commentDTO.getChild().size() > 0) {
            List<CommentVO> child = new ArrayList<>();
            for (CommentDTO comment : commentDTO.getChild()
            ) {
                net.renfei.entity.CommentVO commentVO1 = this.convert(comment);
                child.add(commentVO1);
            }
            commentVO.setChild(child);
        }
        return commentVO;
    }
}
