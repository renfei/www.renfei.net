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
    private String title;
    private String keyword;
    private String content;
    private MultipartFile image;
}
