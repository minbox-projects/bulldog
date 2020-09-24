package org.minbox.framework.bulldog.collect.support;

import lombok.Getter;
import org.minbox.framework.bulldog.collect.LogCollector;
import org.minbox.framework.bulldog.collect.LogPrinter;
import org.minbox.framework.fulldog.core.enums.LogLevel;
import org.minbox.framework.fulldog.core.pojo.GlobalLog;
import org.minbox.framework.fulldog.core.pojo.CallerData;

/**
 * Abstract implementation of {@link LogCollector}
 *
 * @author 恒宇少年
 */
@Getter
public abstract class AbstractLogCollector implements LogCollector {
    /**
     * The name of current {@link LogCollector}
     */
    private String name;
    /**
     * Log printer，print log to console
     */
    protected LogPrinter printer;

    public AbstractLogCollector(String name, Class sourceClass) {
        this.name = name;
        this.printer = new LogPrinter(sourceClass);
    }

    /**
     * Create {@link GlobalLog} instance
     * <p>
     * set call information to {@link GlobalLog}
     *
     * @param level   The log level
     * @param message The log message content
     * @return created {@link GlobalLog} instance
     */
    protected GlobalLog createGlobalLog(LogLevel level, String message) {
        StackTraceElement[] stackTraceElements = new Exception().getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[3];
        return GlobalLog.create(level, message)
                .setCallerData(new CallerData()
                        .setClassName(stackTraceElement.getClassName())
                        .setMethodName(stackTraceElement.getMethodName())
                        .setFileName(stackTraceElement.getFileName())
                        .setLineNumber(stackTraceElement.getLineNumber()));
    }

    /**
     * Replace placeholders for log content
     *
     * @param format    Unformatted log content
     * @param arguments List of parameters corresponding to log content
     * @return formatted log
     */
    protected String replacePlaceholder(String format, Object[] arguments) {
        if (arguments != null && arguments.length > 0) {
            for (int i = 0; i < arguments.length; i++) {
                format = format.replaceFirst("\\{\\}", String.valueOf(arguments[i]));
            }
        }
        return format;
    }
}
