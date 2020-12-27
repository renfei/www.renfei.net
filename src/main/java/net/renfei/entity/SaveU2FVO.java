package net.renfei.entity;

import lombok.Data;

/**
 * <p>Title: OpenU2FVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
public class SaveU2FVO {
    private String pwd;
    private String totp;
    private String keyId;
    private String secretKey;
}
