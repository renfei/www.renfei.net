package net.renfei.service;

import net.renfei.base.BaseService;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.Sms;
import net.renfei.exceptions.ServiceException;
import net.renfei.repository.AccountDOMapper;
import net.renfei.repository.VerificationCodeDOMapper;
import net.renfei.repository.entity.AccountDO;
import net.renfei.repository.entity.AccountDOExample;
import net.renfei.repository.entity.VerificationCodeDO;
import net.renfei.repository.entity.VerificationCodeDOExample;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.Builder;
import net.renfei.sdk.utils.ListUtils;
import net.renfei.sdk.utils.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>Title: VerificationCodeService</p>
 * <p>Description: 验证码服务</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Service
public class VerificationCodeService extends BaseService {
    private final SmsService smsService;
    private final MailService mailService;
    private final RenFeiConfig renFeiConfig;
    private final AccountDOMapper accountMapper;
    private final VerificationCodeDOMapper verificationCodeMapper;

    public VerificationCodeService(MailService mailService,
                                   RenFeiConfig renFeiConfig,
                                   AccountDOMapper accountMapper,
                                   VerificationCodeDOMapper verificationCodeMapper,
                                   @Qualifier("aliyunSmsServiceImpl") SmsService aliyunSmsServiceImpl) {
        this.smsService = aliyunSmsServiceImpl;
        this.mailService = mailService;
        this.verificationCodeMapper = verificationCodeMapper;
        this.renFeiConfig = renFeiConfig;
        this.accountMapper = accountMapper;
    }

    public void sendVerificationCode(boolean longVerificationCode, Date expires, String addressee, String authType, AccountDO account, String callBack) {
        String verificationCodeString;
        if (longVerificationCode) {
            verificationCodeString = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        } else {
            verificationCodeString = StringUtils.getRandomNumber(6);
        }
        VerificationCodeDO verificationCode = new VerificationCodeDO();
        verificationCode.setUuid(UUID.randomUUID().toString().toUpperCase());
        verificationCode.setCode(verificationCodeString);
        verificationCode.setExpires(expires);
        verificationCode.setAddressee(addressee);
        verificationCode.setAuthType(authType);
        if (account != null) {
            verificationCode.setAccountUuid(account.getUuid());
        }
        verificationCode.setBeUsed(false);
        boolean isEmail = false;
        if (StringUtils.isEmail(addressee)) {
            verificationCode.setChannel("EMAIL");
            isEmail = true;
        } else if (StringUtils.isChinaPhone(addressee)) {
            verificationCode.setChannel("SMS");
        } else {
            throw new ServiceException("验证码发送失败发送地址不正确");
        }
        verificationCode.setSendTime(new Date());
        verificationCodeMapper.insertSelective(verificationCode);
        if (isEmail) {
            List<String> contents = new ArrayList<>();
            String userName = "先生/女生";
            if (account != null) {
                userName = account.getUserName();
            }
            contents.add("尊敬的 " + userName + " :");
            contents.add("这封信是由[RENFEI.NET]系统自动发送的。");
            contents.add("您收到这封邮件，是由于在[RENFEI.NET]创建了新的账户或修改 Email 使用了这个邮箱地址，又或者您正在进行敏感操作需要验证您的身份。如果您并没有访问过[RENFEI.NET]或没有进行上述操作，请忽略这封邮件。您不需要退订或进行其他进一步的操作。");
            contents.add("----------------------------------------------------------------------");
            contents.add("帐号验证说明");
            contents.add("----------------------------------------------------------------------");
            contents.add("如果您是[RENFEI.NET]的新用户，或在修改您的注册 Email 时使用了此邮箱地址，又或者您正在进行敏感操作，我们需要对您的操作有效性进行验证以避免非本人操作或服务滥用。");
            if (BeanUtils.isEmpty(callBack)) {
                contents.add("您的验证码是：");
                contents.add("<span style=\"color:red;font-size:18px;font-weight:800;\">" + verificationCodeString + "</span>");
            } else {
                contents.add("您只需点击下面的链接即可：");
                contents.add("<a href=\"" + callBack + "?code=" + verificationCodeString + "\">" + callBack + "?code=" + verificationCodeString + "</a>");
                contents.add("(如果上面不是链接形式，请将该地址手工粘贴到浏览器地址栏再访问)");
            }
            contents.add("----");
            contents.add("感谢您的访问，祝您使用愉快！");
            mailService.send(addressee, userName, "验证邮件：验证您在[RENFEI.NET]的账户", contents);
        } else {
            Sms sms = Builder.of(Sms::new)
                    .with(Sms::setPhoneNumbers, addressee)
                    .with(Sms::setSignName, renFeiConfig.getAliyun().getSms().getSignName())
                    .with(Sms::setTemplateCode, renFeiConfig.getAliyun().getSms().getTemplateCode())
                    .with(Sms::setTemplateParam, "{\"code\":\"" + verificationCodeString + "\"}")
                    .with(Sms::setOutId, verificationCode.getUuid())
                    .build();
            smsService.sendSms(sms);
        }
    }

    public VerificationCodeDO verificationCode(String code, String addressee, String authType) {
        VerificationCodeDOExample example = new VerificationCodeDOExample();
        example.createCriteria()
                .andCodeEqualTo(code)
                .andAddresseeEqualTo(addressee)
                .andAuthTypeEqualTo(authType)
                .andExpiresGreaterThanOrEqualTo(new Date())
                .andBeUsedEqualTo(false);
        VerificationCodeDO verificationCode = ListUtils.getOne(verificationCodeMapper.selectByExample(example));
        if (verificationCode != null) {
            verificationCode.setBeUsed(true);
            verificationCodeMapper.updateByPrimaryKeySelective(verificationCode);
        }
        return verificationCode;
    }

    public VerificationCodeDO verificationCode(String code, String authType) {
        VerificationCodeDOExample example = new VerificationCodeDOExample();
        example.createCriteria()
                .andCodeEqualTo(code)
                .andAuthTypeEqualTo(authType)
                .andExpiresGreaterThanOrEqualTo(new Date())
                .andBeUsedEqualTo(false);
        VerificationCodeDO verificationCode = ListUtils.getOne(verificationCodeMapper.selectByExample(example));
        if (verificationCode != null) {
            verificationCode.setBeUsed(true);
            verificationCodeMapper.updateByPrimaryKeySelective(verificationCode);
        }
        return verificationCode;
    }
}
