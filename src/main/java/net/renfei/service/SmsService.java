package net.renfei.service;

import net.renfei.entity.Sms;
import net.renfei.exceptions.ServiceException;

/**
 * <p>Title: SmsService</p>
 * <p>Description: 短信服务</p>
 *
 * @author RenFei
 */
public interface SmsService {
    String sendSms(Sms sms) throws ServiceException;
}
