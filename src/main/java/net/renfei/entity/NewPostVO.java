package net.renfei.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>Title: NewPostVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 * @date : 2021-01-24 23:07
 */
@Data
public class NewPostVO {
    private Long categoryId;
    private String title;
    private String keyword;
    private String describes;
    private String content;
    private String sourceName;
    private String sourceUrl;
    private String tags;
    private Boolean isOriginal;
    private MultipartFile image;
}
