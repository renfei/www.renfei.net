package net.renfei.entity;

import lombok.Data;

/**
 * <p>Title: UpdatePasswordVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
public class UpdatePasswordVO {
    private String oldPwd;
    private String newPwd;
    private String keyId;
}
