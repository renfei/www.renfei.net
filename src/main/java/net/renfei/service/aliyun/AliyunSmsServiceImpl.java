package net.renfei.service.aliyun;

import com.aliyun.oss.common.utils.DateUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseService;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.Sms;
import net.renfei.exceptions.ServiceException;
import net.renfei.repository.VerificationCodeDOMapper;
import net.renfei.repository.entity.VerificationCodeDOExample;
import net.renfei.sdk.utils.DateUtils;
import net.renfei.service.SmsService;
import org.springframework.stereotype.Service;

/**
 * <p>Title: SmsServiceImpl</p>
 * <p>Description: 短信服务</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Service
public class AliyunSmsServiceImpl extends BaseService implements SmsService {
    private final RenFeiConfig renFeiConfig;
    private final VerificationCodeDOMapper verificationCodeMapper;

    public AliyunSmsServiceImpl(RenFeiConfig renFeiConfig,
                                VerificationCodeDOMapper verificationCodeMapper) {
        this.renFeiConfig = renFeiConfig;
        this.verificationCodeMapper = verificationCodeMapper;
    }

    @Override
    public String sendSms(Sms sms) throws ServiceException {
        // 检查发送频率，1条/分钟，5条/小时，10条/24小时
        VerificationCodeDOExample example = new VerificationCodeDOExample();
        example.createCriteria()
                .andAddresseeEqualTo(sms.getPhoneNumbers())
                .andChannelEqualTo("SMS")
                .andSendTimeGreaterThanOrEqualTo(DateUtils.nextDay(-1));
        Page page = PageHelper.startPage(1, 1);
        verificationCodeMapper.selectByExample(example);
        if (page.getTotal() >= 11) {
            throw new ServiceException("当前手机号码当天发送短信配额已达上限，请明日再试");
        }
        example = new VerificationCodeDOExample();
        example.createCriteria()
                .andAddresseeEqualTo(sms.getPhoneNumbers())
                .andChannelEqualTo("SMS")
                .andSendTimeGreaterThanOrEqualTo(DateUtils.nextHours(-1));
        page = PageHelper.startPage(1, 1);
        verificationCodeMapper.selectByExample(example);
        if (page.getTotal() >= 6) {
            throw new ServiceException("当前手机号码短信发送量已达上限，请稍后再试");
        }
        example = new VerificationCodeDOExample();
        example.createCriteria()
                .andAddresseeEqualTo(sms.getPhoneNumbers())
                .andChannelEqualTo("SMS")
                .andSendTimeGreaterThanOrEqualTo(DateUtils.nextMinutes(-1));
        page = PageHelper.startPage(1, 1);
        verificationCodeMapper.selectByExample(example);
        if (page.getTotal() >= 2) {
            throw new ServiceException("发送请求太快了，请稍后再试");
        }
        DefaultProfile profile = DefaultProfile.getProfile(renFeiConfig.getAliyun().getSms().getRegionId(),
                renFeiConfig.getAliyun().getAccessKeyId(), renFeiConfig.getAliyun().getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", renFeiConfig.getAliyun().getSms().getRegionId());
        request.putQueryParameter("PhoneNumbers", sms.getPhoneNumbers());
        request.putQueryParameter("SignName", sms.getSignName());
        request.putQueryParameter("TemplateCode", sms.getTemplateCode());
        request.putQueryParameter("TemplateParam", sms.getTemplateParam());
        request.putQueryParameter("OutId", sms.getOutId());
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            log.error(e.getMessage(), e);
        } catch (ClientException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
