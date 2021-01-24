package net.renfei.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>Title: newWeiboVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 * @date : 2021-01-24 23:05
 */
@Data
public class NewWeiboVO {
    private String content;
    private MultipartFile image;
}
