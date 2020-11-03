package net.renfei.annotation;

import net.renfei.entity.LogLevel;
import net.renfei.entity.LogModule;
import net.renfei.entity.LogType;

import java.lang.annotation.*;

/**
 * <p>Title: OperLog</p>
 * <p>Description: 操作日志</p>
 *
 * @author RenFei
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemLog {
    LogLevel logLevel() default LogLevel.INFO; // 日志等级

    LogModule logModule() default LogModule.CMS; // 操作模块

    LogType logType() default LogType.GET;  // 操作类型

    String logDesc() default "";  // 操作说明
}
