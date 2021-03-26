package net.renfei.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.renfei.annotation.SystemLog;
import net.renfei.base.BaseService;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.AccountDTO;
import net.renfei.entity.LogLevel;
import net.renfei.entity.LogModule;
import net.renfei.entity.LogType;
import net.renfei.repository.SystemLogMapper;
import net.renfei.repository.entity.SystemLogWithBLOBs;
import net.renfei.sdk.utils.IpUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static net.renfei.base.BaseController.SESSION_KEY;

/**
 * <p>Title: LogService</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 * @date : 2020-11-03 21:44
 */
@Slf4j
@Service
public class LogService extends BaseService {
    private final RenFeiConfig renFeiConfig;
    private final SystemLogMapper systemLogMapper;

    public LogService(RenFeiConfig renFeiConfig,
                      SystemLogMapper systemLogMapper) {
        this.renFeiConfig = renFeiConfig;
        this.systemLogMapper = systemLogMapper;
    }

    @Async
    public void log(LogLevel level, LogModule module, LogType type, String desc, HttpServletRequest request) {
        SystemLogWithBLOBs systemLog = new SystemLogWithBLOBs();
        systemLog.setLogLevel(level.toString());
        systemLog.setLogModel(module.toString());
        systemLog.setLogType(type.toString());
        systemLog.setLogDesc(desc);
        if (request != null) {
            AccountDTO accountDTO = null;
            if ("SESSION".equals(renFeiConfig.getAuthMode())) {
                Object session = request.getSession().getAttribute(SESSION_KEY);
                if (session instanceof AccountDTO) {
                    accountDTO = (AccountDTO) session;
                }
            } else {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication.getPrincipal() instanceof AccountDTO) {
                    accountDTO = (AccountDTO) authentication.getPrincipal();
                }
            }
            if (accountDTO != null) {
                systemLog.setUserUuid(accountDTO.getUuid());
                systemLog.setUserName(systemLog.getUserName());
            }
            // 请求的参数
            try {
                Map<String, String> rtnMap = converMap(request.getParameterMap());
                // 将参数所在的数组转换成json
                String params = JSON.toJSONString(rtnMap);
                systemLog.setRequParam(params);
                systemLog.setRequIp(IpUtils.getIpAddress(request));
                systemLog.setRequAgent(request.getHeader("User-Agent"));
                systemLog.setRequUri(request.getRequestURI());
            } catch (Exception exception) {
                log.error(exception.getMessage(), exception);
            }
        }
        systemLog.setLogTime(new Date());
        this.insert(systemLog);
    }

    @Async
    public void insert(String className, String methodName, SystemLog logAnnotation, Object keys, HttpServletRequest request) {
        SystemLogWithBLOBs systemLog = new SystemLogWithBLOBs();
        if (logAnnotation != null) {
            String logLevel = logAnnotation.logLevel().toString();
            String logModule = logAnnotation.logModule().toString();
            String logType = logAnnotation.logType().toString();
            String logDesc = logAnnotation.logDesc();
            systemLog.setLogLevel(logLevel);
            systemLog.setLogModel(logModule);
            systemLog.setLogType(logType);
            systemLog.setLogDesc(logDesc);
        }
        methodName = className + "." + methodName;
        // 请求方法
        systemLog.setRequMethod(methodName);
        // 请求的参数
        if (request != null) {
            Map<String, String> rtnMap = converMap(request.getParameterMap());
            // 将参数所在的数组转换成json
            String params = JSON.toJSONString(rtnMap);
            systemLog.setRequParam(params);
            systemLog.setRequIp(IpUtils.getIpAddress(request));
            systemLog.setRequAgent(request.getHeader("User-Agent"));
            systemLog.setRequUri(request.getRequestURI());
        }
        if (keys != null) {
            systemLog.setRespParam(JSON.toJSONString(keys));
        }
        systemLog.setLogTime(new Date());
        this.insert(systemLog);
    }

    @Async
    public void insert(SystemLogWithBLOBs systemLog) {
        systemLogMapper.insertSelective(systemLog);
    }

    /**
     * 转换request 请求参数
     *
     * @param paramMap request获取的参数数组
     */
    public static Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }
}
