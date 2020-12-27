package net.renfei.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: AccountDTO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
public class AccountDTO implements Serializable {
    private Long id;
    private String uuid;
    private String userName;
    private String email;
    private String phone;
    private Date registrationDate;
    private String password;
    private String totp;
    private String registrationIp;
    private Integer trialErrorTimes;
    private Date lockTime;
    private Integer stateCode;
    private String lastName;
    private String firstName;
    private String ucScript;
}
