package net.renfei.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.renfei.annotation.SystemLog;
import net.renfei.repository.SystemLogMapper;
import net.renfei.repository.entity.SystemLogWithBLOBs;
import net.renfei.sdk.utils.IpUtils;
import net.renfei.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: LogAspect</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Aspect
@Component
public class LogAspect {
    private final LogService logService;

    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(net.renfei.annotation.SystemLog)")
    public void logPointCut() {
    }

    @AfterReturning(value = "logPointCut()", returning = "keys")
    public void saveSystemLog(JoinPoint joinPoint, Object keys) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //设置子线程共享
        RequestContextHolder.setRequestAttributes(servletRequestAttributes, true);
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            logService.insert(joinPoint.getTarget().getClass().getName(),
                    method.getName(), method.getAnnotation(SystemLog.class), keys, request);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
