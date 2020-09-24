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
     * The log caller instance
     */
    private CallerData callerData;
    /**
     * the global log create time
     * default is current time millis
     */
    private Long createTime = System.currentTimeMillis();

    /**
     * Create a {@link GlobalLog} new instance
     *
     * @param level   The log level
     * @param message The log message content
     * @return created {@link GlobalLog} instance
     */
    public static GlobalLog create(LogLevel level, String message) {
        return new GlobalLog()
                .setLevel(level)
                .setMessage(message);
    }
}
