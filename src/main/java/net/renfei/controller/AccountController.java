package net.renfei.controller;

import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.AccountDTO;
import net.renfei.entity.DiscuzInfo;
import net.renfei.entity.SaveU2FVO;
import net.renfei.entity.UpdatePasswordVO;
import net.renfei.exceptions.NeedU2FException;
import net.renfei.exceptions.ServiceException;
import net.renfei.repository.entity.AccountDO;
import net.renfei.repository.entity.VerificationCodeDO;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.utils.DateUtils;
import net.renfei.sdk.utils.GoogleAuthenticator;
import net.renfei.sdk.utils.PasswordUtils;
import net.renfei.sdk.utils.StringUtils;
import net.renfei.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>Title: AccountController</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {
    private final DiscuzService discuzService;
    private final AccountService accountService;
    private final SecretKeyService secretKeyService;
    private final VerificationCodeService verificationCodeService;

    protected AccountController(RenFeiConfig renFeiConfig,
                                GlobalService globalService,
                                CommentsService commentsService,
                                PaginationService paginationService,
                                DiscuzService discuzService,
                                AccountService accountService,
                                SecretKeyService secretKeyService,
                                VerificationCodeService verificationCodeService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.discuzService = discuzService;
        this.accountService = accountService;
        this.secretKeyService = secretKeyService;
        this.verificationCodeService = verificationCodeService;
    }

    @GetMapping("")
    public ModelAndView account() {
        return new ModelAndView("redirect:/account/manage");
    }

    @GetMapping("manage")
    public ModelAndView manage(ModelAndView mv) {
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return modelAndView;
        }
        DiscuzInfo discuzInfo = discuzService.getDiscuzInfo("renfei");
        mv.addObject("user", getUser());
        mv.addObject("discuzInfo", discuzInfo);
        mv.addObject("title", "管理您的账户 - " + renFeiConfig.getSiteName());
        mv.setViewName("account/manage");
        return mv;
    }

    @GetMapping("manage/email")
    public ModelAndView manageEmail(ModelAndView mv) {
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return modelAndView;
        }
        mv.addObject("user", getUser());
        mv.addObject("title", "管理您的电子邮箱地址 - " + renFeiConfig.getSiteName());
        mv.setViewName("account/email");
        return mv;
    }

    @ResponseBody
    @PostMapping("manage/email/verCode")
    public APIResult sendEmailVerCode(@RequestParam("newEmail") String newEmail) {
        if (getUser() == null) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("请先登录")
                    .build();
        }
        newEmail = newEmail.trim().toLowerCase();
        if (!StringUtils.isEmail(newEmail)) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("请填写正确的电子邮箱地址")
                    .build();
        }
        AccountDO accountDO = new AccountDO();
        BeanUtils.copyProperties(getUser(), accountDO);
        try {
            verificationCodeService.sendVerificationCode(false, DateUtils.nextMinutes(10), newEmail, "UPDATE_EMAIL", accountDO, null);
        } catch (ServiceException serviceException) {
            return APIResult.builder()
                    .code(serviceException.getStateCode())
                    .message(serviceException.getMessage())
                    .build();
        }
        return APIResult.success();
    }

    @ResponseBody
    @PostMapping("manage/email")
    public APIResult updateEmail(@RequestParam("newEmail") String newEmail,
                                 @RequestParam("verCode") String verCode) {
        AccountDTO accountDTO = getUser();
        if (accountDTO == null) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("请先登录")
                    .build();
        }
        newEmail = newEmail.trim().toLowerCase();
        if (!StringUtils.isEmail(newEmail)) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("请填写正确的电子邮箱地址")
                    .build();
        }
        if (accountDTO.getEmail().trim().toLowerCase().equals(newEmail)) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("新的电子邮箱地址不能跟旧的电子邮箱地址一样")
                    .build();
        }
        VerificationCodeDO verificationCodeDO =
                verificationCodeService.verificationCode(verCode, newEmail, "UPDATE_EMAIL");
        if (verificationCodeDO == null) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("验证码错误或已经过期")
                    .build();
        }
        // 查找新的电子邮箱地址是否被占用
        if (accountService.getAccountByEmail(newEmail) != null) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("新的电子邮箱地址已经被占用")
                    .build();
        }
        accountDTO.setEmail(newEmail);
        accountService.updateAccount(accountDTO);
        updateUser(accountDTO);
        return APIResult.success();
    }

    @GetMapping("manage/phone")
    public ModelAndView managePhone(ModelAndView mv) {
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return modelAndView;
        }
        mv.addObject("user", getUser());
        mv.addObject("title", "管理您的手机号码 - " + renFeiConfig.getSiteName());
        mv.setViewName("account/phone");
        return mv;
    }

    @ResponseBody
    @PostMapping("manage/phone/verCode")
    public APIResult sendPhoneVerCode(@RequestParam("newPhone") String newPhone) {
        if (getUser() == null) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("请先登录")
                    .build();
        }
        newPhone = newPhone.trim().toLowerCase();
        if (!StringUtils.isChinaPhone(newPhone)) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("只支持中国大陆的手机号码")
                    .build();
        }
        AccountDO accountDO = new AccountDO();
        BeanUtils.copyProperties(getUser(), accountDO);
        try {
            verificationCodeService.sendVerificationCode(false, DateUtils.nextMinutes(10), newPhone, "UPDATE_PHONE", accountDO, null);
        } catch (ServiceException serviceException) {
            return APIResult.builder()
                    .code(serviceException.getStateCode())
                    .message(serviceException.getMessage())
                    .build();
        }
        return APIResult.success();
    }

    @ResponseBody
    @PostMapping("manage/phone")
    public APIResult updatePhone(@RequestParam("newPhone") String newPhone,
                                 @RequestParam("verCode") String verCode) {
        AccountDTO accountDTO = getUser();
        if (accountDTO == null) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("请先登录")
                    .build();
        }
        newPhone = newPhone.trim().toLowerCase().replace("+86", "");
        if (!StringUtils.isChinaPhone(newPhone)) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("只支持中国大陆的手机号码")
                    .build();
        }
        if (!net.renfei.sdk.utils.BeanUtils.isEmpty(accountDTO.getPhone())) {
            if (accountDTO.getPhone().trim().toLowerCase().equals(newPhone)) {
                return APIResult.builder()
                        .code(StateCode.Failure)
                        .message("新的手机号码不能跟旧的手机号码一样")
                        .build();
            }
        }
        VerificationCodeDO verificationCodeDO =
                verificationCodeService.verificationCode(verCode, newPhone, "UPDATE_PHONE");
        if (verificationCodeDO == null) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("验证码错误或已经过期")
                    .build();
        }
        // 查找新的手机号码是否被占用
        if (accountService.getAccountByPhone(newPhone) != null) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("新的手机号已经被占用")
                    .build();
        }
        accountDTO.setPhone(newPhone);
        accountService.updateAccount(accountDTO);
        updateUser(accountDTO);
        return APIResult.success();
    }

    @GetMapping("manage/password")
    public ModelAndView managePassword(ModelAndView mv) {
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return modelAndView;
        }
        mv.addObject("title", "管理您的密码 - " + renFeiConfig.getSiteName());
        mv.setViewName("account/password");
        return mv;
    }

    @ResponseBody
    @PostMapping("manage/password")
    public APIResult updatePassword(@RequestBody UpdatePasswordVO updatePasswordVO) {
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("请先登录")
                    .build();
        }
        try {
            updatePasswordVO.setOldPwd(secretKeyService.decrypt(updatePasswordVO.getOldPwd(), updatePasswordVO.getKeyId()));
            updatePasswordVO.setNewPwd(secretKeyService.decrypt(updatePasswordVO.getNewPwd(), updatePasswordVO.getKeyId()));
            AccountDTO accountDTO = getUser();
            accountDTO.setPassword(accountService.updatePassword(getUser(), updatePasswordVO));
            updateUser(accountDTO);
            return APIResult.success();
        } catch (ServiceException serviceException) {
            return APIResult.builder().code(serviceException.getStateCode()).message(serviceException.getMessage()).build();
        } catch (PasswordUtils.CannotPerformOperationException cannotPerformOperationException) {
            return APIResult.builder().code(StateCode.Failure).message(cannotPerformOperationException.getMessage()).build();
        }
    }

    @GetMapping("manage/u2f")
    public ModelAndView manageU2F(ModelAndView mv) {
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return modelAndView;
        }
        mv.addObject("user", getUser());
        String secretKey = GoogleAuthenticator.generateSecretKey(renFeiConfig.getTotpSecret());
        mv.addObject("secretKey", secretKey);
        mv.addObject("totpString", GoogleAuthenticator.genTotpString("RENFEI.NET", getUser().getUserName(), secretKey));
        mv.addObject("title", "管理您的两步认证(U2F) - " + renFeiConfig.getSiteName());
        mv.setViewName("account/u2f");
        return mv;
    }

    @ResponseBody
    @PostMapping("manage/u2f")
    public APIResult manageU2FSave(@RequestBody SaveU2FVO saveU2FVO) {
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("请先登录")
                    .build();
        }
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(getUser().getTotp())) {
            saveU2FVO.setPwd(secretKeyService.decrypt(saveU2FVO.getPwd(), saveU2FVO.getKeyId()));
            saveU2FVO.setSecretKey(secretKeyService.decrypt(saveU2FVO.getSecretKey(), saveU2FVO.getKeyId()));
            if (PasswordUtils.verifyPassword(saveU2FVO.getPwd(), getUser().getPassword())) {
                if (GoogleAuthenticator.authcode(saveU2FVO.getTotp(), saveU2FVO.getSecretKey())) {
                    AccountDTO accountDTO = getUser();
                    accountDTO.setTotp(saveU2FVO.getSecretKey());
                    accountService.updateAccount(accountDTO);
                    updateUser(accountDTO);
                    return APIResult.success();
                } else {
                    return APIResult.builder()
                            .code(StateCode.Failure)
                            .message("您输入的两步认证码与二维码校验失败，请重试")
                            .build();
                }
            } else {
                return APIResult.builder()
                        .code(StateCode.Failure)
                        .message("密码不正确")
                        .build();
            }
        } else {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("两步认证U2F已经是开启状态，无需再次开启")
                    .build();
        }
    }

    @ResponseBody
    @DeleteMapping("manage/u2f")
    public APIResult closeU2F(@RequestBody SaveU2FVO saveU2FVO) {
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("请先登录")
                    .build();
        }
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(getUser().getTotp())) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("两步认证U2F已经是关闭状态，无需再次关闭")
                    .build();
        } else {
            saveU2FVO.setPwd(secretKeyService.decrypt(saveU2FVO.getPwd(), saveU2FVO.getKeyId()));
            if (PasswordUtils.verifyPassword(saveU2FVO.getPwd(), getUser().getPassword())) {
                if (GoogleAuthenticator.authcode(saveU2FVO.getTotp(), getUser().getTotp())) {
                    AccountDTO accountDTO = getUser();
                    accountDTO.setTotp(null);
                    accountService.updateAccount(accountDTO);
                    updateUser(accountDTO);
                    return APIResult.success();
                } else {
                    return APIResult.builder()
                            .code(StateCode.Failure)
                            .message("您输入的两步认证码校验失败，请重试")
                            .build();
                }
            } else {
                return APIResult.builder()
                        .code(StateCode.Failure)
                        .message("密码不正确")
                        .build();
            }
        }
    }

    @GetMapping({"manage/firstName", "manage/lastName"})
    public ModelAndView manageFirstName(ModelAndView mv) {
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return modelAndView;
        }
        mv.addObject("user", getUser());
        mv.addObject("title", "管理您的姓名称呼 - " + renFeiConfig.getSiteName());
        mv.setViewName("account/firstName");
        return mv;
    }

    @ResponseBody
    @PostMapping({"manage/firstName", "manage/lastName"})
    public APIResult updateFirstName(@RequestParam(value = "firstName", required = false) String firstName,
                                     @RequestParam(value = "lastName", required = false) String lastName) {
        ModelAndView modelAndView = checkSigned();
        if (modelAndView != null) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("请先登录")
                    .build();
        }
        AccountDTO accountDTO = getUser();
        if (!net.renfei.sdk.utils.BeanUtils.isEmpty(firstName)) {
            accountDTO.setFirstName(firstName);
        }
        if (!net.renfei.sdk.utils.BeanUtils.isEmpty(lastName)) {
            accountDTO.setLastName(lastName);
        }
        accountService.updateAccount(accountDTO);
        updateUser(accountDTO);
        return APIResult.success();
    }
}
