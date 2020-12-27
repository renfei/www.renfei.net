package net.renfei.entity;

import lombok.Data;

/**
 * <p>Title: SignUpVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
public class SignUpVO {
    private String userName;
    private String password;
    private String email;
    private String keyUuid;
    private String reCAPTCHAToken;
}
