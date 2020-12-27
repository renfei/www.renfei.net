package net.renfei.entity;

import lombok.Data;

/**
 * <p>Title: SignInVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
public class SignInVO {
    private String userName;
    private String password;
    private String tOtp;
    private String keyUuid;
    private Boolean useVerCode;
    private String reCAPTCHAToken;
}
