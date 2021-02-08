package net.renfei.controller;

import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.AccountDTO;
import net.renfei.entity.ReportPublicKeyVO;
import net.renfei.entity.SignInVO;
import net.renfei.entity.SignUpVO;
import net.renfei.exceptions.NeedU2FException;
import net.renfei.exceptions.ServiceException;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.IpUtils;
import net.renfei.sdk.utils.PasswordUtils;
import net.renfei.service.*;
import net.renfei.util.JwtUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * <p>Title: AuthController</p>
 * <p>Description: 认证</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {
    private final JwtUtils jwtUtils;
    private final AccountService accountService;
    private final ReCaptchaService reCaptchaService;
    private final SecretKeyService secretKeyService;

    protected AuthController(RenFeiConfig renFeiConfig,
                             GlobalService globalService,
                             CommentsService commentsService,
                             PaginationService paginationService,
                             JwtUtils jwtUtils, AccountService accountService,
                             ReCaptchaService reCaptchaService,
                             SecretKeyService secretKeyService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.jwtUtils = jwtUtils;
        this.accountService = accountService;
        this.reCaptchaService = reCaptchaService;
        this.secretKeyService = secretKeyService;
    }

    /**
     * 申请获取服务器公钥
     */
    @ResponseBody
    @GetMapping("secretKey")
    public APIResult<String> getSecretKey() {
        Map<Integer, String> map = secretKeyService.secretKey();
        if (BeanUtils.isEmpty(map)) {
            return APIResult.builder()
                    .code(StateCode.Error)
                    .build();
        }
        return APIResult.builder()
                .code(StateCode.OK)
                .message(map.get(1))
                .data(map.get(0))
                .build();
    }

    /**
     * 上报客户端公钥，并下发AES秘钥
     */
    @ResponseBody
    @PostMapping("secretKey")
    public APIResult<Map<String, String>> setSecretKey(@RequestBody ReportPublicKeyVO reportPublicKeyVO) {
        try {
            return new APIResult<>(secretKeyService.setSecretKey(reportPublicKeyVO));
        } catch (ServiceException serviceException) {
            return APIResult.builder().code(serviceException.getStateCode()).message(serviceException.getMessage()).build();
        }
    }

    /**
     * 登陆界面
     */
    @GetMapping("signIn")
    public ModelAndView signInPage(ModelAndView mv,
                                   @RequestParam(value = "callback", required = false) String callback) {
        AccountDTO accountDTO = getUser();
        mv.addObject("signed", accountDTO != null ? "1" : "0");
        mv.addObject("ucScript", accountDTO != null ? accountDTO.getUcScript() : "");
        mv.addObject("callback", getCallBack(callback));
        mv.addObject("ReCAPTCHA_Client_Key", renFeiConfig.getGoogle().getReCAPTCHA().getClientKey());
        mv.addObject("title", "登录 - " + renFeiConfig.getSiteName());
        mv.setViewName("auth/signIn");
        return mv;
    }

    /**
     * 登陆接口
     */
    @ResponseBody
    @PostMapping("signIn")
    public APIResult<String> doSignIn(@RequestBody SignInVO signInVO) {
        if (getUser() != null) {
            if ("SESSION".equals(renFeiConfig.getAuthMode())) {
                return new APIResult<>(StateCode.OK.getDescribe());
            }else {
                SecurityContextHolder.clearContext();
            }
        }
        if (!BeanUtils.isEmpty(signInVO.getReCAPTCHAToken())
                && reCaptchaService.verifying(signInVO.getReCAPTCHAToken(), IpUtils.getIpAddress(request))) {
            try {
                signInVO.setPassword(secretKeyService.decrypt(signInVO.getPassword(), signInVO.getKeyUuid()));
                AccountDTO accountDTO = accountService.signIn(signInVO, request);
                accountDTO = accountService.fillRolePermissions(accountDTO);
                if ("SESSION".equals(renFeiConfig.getAuthMode())) {
                    request.getSession().setAttribute(SESSION_KEY, accountDTO);
                    return new APIResult<>(accountDTO.getUcScript());
                } else {
                    // 签发TOKEN
                    String token = jwtUtils.createJWT(accountDTO.getUuid(), accountDTO.getRoles(), accountDTO.getAuthorities(), accountDTO.getUuid());
                    return new APIResult<>(token);
                }
            } catch (ServiceException serviceException) {
                return APIResult.builder().code(serviceException.getStateCode()).message(serviceException.getMessage()).build();
            } catch (NeedU2FException needU2FException) {
                return APIResult.builder().code(needU2FException.getStateCode()).message(needU2FException.getMessage()).build();
            } catch (Exception exception) {
                log.error(exception.getMessage(), exception);
                return APIResult.builder().code(StateCode.Error).message("内部服务器错误，请联系管理员").build();
            }
        } else {
            return APIResult.builder().code(StateCode.Failure).message("我们的服务器好像对你很感兴趣，想知道你是人类还是同类？刷新一下再试试").build();
        }
    }

    /**
     * 发送登陆验证码
     */
    @ResponseBody
    @PostMapping("signIn/sendVerificationCode")
    public APIResult sendSignInVerificationCode(@RequestParam("userName") String userName) {
        if (getUser() != null) {
            return APIResult.builder().code(StateCode.Failure).message("您已经登录了，无需再发送登陆验证码了").build();
        }
        try {
            accountService.sendSignInVerificationCode(userName);
            return APIResult.success();
        } catch (ServiceException serviceException) {
            return APIResult.builder().code(serviceException.getStateCode()).message(serviceException.getMessage()).build();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            return APIResult.builder().code(StateCode.Error).message("内部服务器错误，请联系管理员").build();
        }
    }

    /**
     * 注册界面
     */
    @GetMapping("signUp")
    public ModelAndView signUpPage(ModelAndView mv) {
        if (getUser() != null) {
            return new ModelAndView("redirect:/");
        }
        mv.addObject("ReCAPTCHA_Client_Key", renFeiConfig.getGoogle().getReCAPTCHA().getClientKey());
        mv.addObject("title", "创建您的账户 - " + renFeiConfig.getSiteName());
        mv.setViewName("auth/signUp");
        return mv;
    }

    /**
     * 注册接口
     */
    @ResponseBody
    @PostMapping("signUp")
    public APIResult doSignUp(@RequestBody SignUpVO signUpVO) {
        if (getUser() != null) {
            return APIResult.builder().code(StateCode.Failure).message("您已经登录，无需重复注册。").build();
        }
        if (!BeanUtils.isEmpty(signUpVO.getReCAPTCHAToken())
                && reCaptchaService.verifying(signUpVO.getReCAPTCHAToken(), IpUtils.getIpAddress(request))) {
            try {
                signUpVO.setPassword(secretKeyService.decrypt(signUpVO.getPassword(), signUpVO.getKeyUuid()));
                accountService.signUp(signUpVO, request);
                return APIResult.success();
            } catch (ServiceException serviceException) {
                return APIResult.builder().code(serviceException.getStateCode()).message(serviceException.getMessage()).build();
            } catch (PasswordUtils.CannotPerformOperationException cannotPerformOperationException) {
                log.error(cannotPerformOperationException.getMessage(), cannotPerformOperationException);
                return APIResult.builder().code(StateCode.Error).message("内部服务器错误，请联系管理员").build();
            } catch (Exception exception) {
                log.error(exception.getMessage(), exception);
                return APIResult.builder().code(StateCode.Error).message("内部服务器错误，请联系管理员").build();
            }
        } else {
            return APIResult.builder().code(StateCode.Failure).message("我们的服务器好像对你很感兴趣，想知道你是人类还是同类？").build();
        }
    }

    /**
     * 注册完成界面
     */
    @GetMapping("signUp/success")
    public ModelAndView signUpSuccessPage(ModelAndView mv) {
        mv.addObject("title", "您已成功创建了账户 - " + renFeiConfig.getSiteName());
        mv.setViewName("auth/signUpSuccess");
        return mv;
    }

    /**
     * 注册邮箱验证
     */
    @GetMapping("signUp/activation")
    public ModelAndView signUpActivation(ModelAndView mv, @RequestParam(value = "code", required = false) String code) {
        boolean pass = false;
        if (!BeanUtils.isEmpty(code)) {
            try {
                accountService.signUpActivation(code);
                pass = true;
            } catch (ServiceException ignored) {
            }
        }
        mv.addObject("pass", pass);
        if (pass) {
            mv.addObject("title", "您的账户已经激活 - " + renFeiConfig.getSiteName());
        } else {
            mv.addObject("title", "未能激活您的账户 - " + renFeiConfig.getSiteName());
        }
        mv.setViewName("auth/signUpActivation");
        return mv;
    }

    /**
     * 登出界面
     */
    @GetMapping("signOut")
    public ModelAndView signOut(ModelAndView mv,
                                @RequestParam(value = "callback", required = false) String callback) {
        mv.addObject("title", "您已安全登出我们的系统 - " + renFeiConfig.getSiteName());
        AccountDTO accountDTO = getUser();
        String script = "";
        if (accountDTO != null) {
            script = accountService.signOut(accountDTO);
        }
        request.getSession().removeAttribute(SESSION_KEY);
        SecurityContextHolder.clearContext();
        mv.addObject("script", script);
        String callBack = getCallBack(callback);
        if (BeanUtils.isEmpty(callBack)) {
            callBack = renFeiConfig.getDomain();
        }
        if (!callBack.startsWith("http")) {
            callBack = "http://" + callBack;
        }
        mv.addObject("callback", callBack);
        mv.setViewName("auth/signOut");
        return mv;
    }
}
