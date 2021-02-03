package net.renfei.entity;

import lombok.Data;

/**
 * <p>Title: SendEmailVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 * @date : 2021-02-03 17:17
 */
@Data
public class SendEmailVO {
    private String to;
    private String name;
    private String subject;
    private String contents;
}
