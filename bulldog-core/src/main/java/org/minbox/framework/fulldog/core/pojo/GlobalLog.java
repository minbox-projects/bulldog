package org.minbox.framework.fulldog.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.minbox.framework.fulldog.core.enums.LogLevel;

/**
 * The Global log
 *
 * @author 恒宇少年
 */
@Data
@Accessors(chain = true)
public class GlobalLog {
    /**
     * The log level
     */
    private LogLevel level;
    /**
     * Log message content
     */
    private String message;
    /**
     * If it is an error log, record the exception stack
     */
    private String exceptionStack;
    /**
     * caller class name
     * {@link StackTraceElement#getClassName()}
     */
    private String callerClass;
    /**
     * caller method name
     * {@link StackTraceElement#getMethodName()}
     */
    private String callerMethod;
    /**
     * caller code line number
     * {@link StackTraceElement#getLineNumber()}
     */
    private int callerCodeLineNumber;
    /**
     * the global log create time
     * default is current time millis
     */
    private Long createTime = System.currentTimeMillis();
}
