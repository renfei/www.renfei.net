package net.renfei.service;

import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseService;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * <p>Title: DomainNameService</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Service
public class DomainNameService extends BaseService {
    private final ExecCmdService execCmdService;

    public DomainNameService(ExecCmdService execCmdService) {
        this.execCmdService = execCmdService;
    }

    public APIResult<String> execDigTrace(String domain) {
        if (StringUtils.isDomain(domain)) {
            try {
                return new APIResult(execCmdService.execCmd("dig " + domain.trim() + " +trace"));
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                return APIResult.builder()
                        .code(StateCode.Error)
                        .message("内部服务器错误。\nInternal server error.")
                        .build();
            }
        } else {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("域名格式不正确。\nIncorrect format of domain name.")
                    .build();
        }
    }

    public APIResult<String> execWhois(String domain) {
        if (StringUtils.isDomain(domain)) {
            try {
                return new APIResult(execCmdService.execCmd("whois " + domain.trim()));
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                return APIResult.builder()
                        .code(StateCode.Error)
                        .message("内部服务器错误。\nInternal server error.")
                        .build();
            }
        } else {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("域名格式不正确。\nIncorrect format of domain name.")
                    .build();
        }
    }
}
