package org.minbox.framework.bulldog.collect;

import org.minbox.framework.bulldog.common.utils.Assert;
import org.minbox.framework.fulldog.core.enums.LogLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Log printer
 * <p>
 * Provide methods to print logs of different levels
 *
 * @author 恒宇少年
 */
public class LogPrinter {
    /**
     * The root {@link LogLevel}
     * <p>
     * The default is {@link LogLevel#DEBUG}，console will only print logs higher than this level
     */
    private LogLevel rootLevel;
    private Logger logger;

    public LogPrinter(Class<?> sourceClass) {
        this(LogLevel.DEBUG, sourceClass);
    }

    public LogPrinter(LogLevel rootLevel, Class<?> sourceClass) {
        this.rootLevel = rootLevel;
        this.logger = LoggerFactory.getLogger(sourceClass);
    }

    /**
     * Print error log to console
     *
     * @param message   Output log content
     * @param throwable Exception object
     */
    public void print(String message, Throwable throwable) {
        this.print(LogLevel.ERROR, message, throwable);
    }

    /**
     * Print long to console
     *
     * @param level   The log level
     * @param message Output log content
     */
    public void print(LogLevel level, String message) {
        this.print(level, message, null);
    }

    /**
     * Print log to console
     *
     * @param message  Output log content
     * @param logLevel Log level of output
     */
    private void print(LogLevel logLevel, String message, Throwable throwable) {
        Assert.notEmpty(message, "The log content cannot be empty.");
        Assert.notNull(logLevel, "The log level cannot be null.");
        if (logLevel.thanLevel(rootLevel)) {
            // Choose to output logs of different levels
            switch (logLevel) {
                case TRACE:
                    logger.trace(message);
                    break;
                case DEBUG:
                    logger.debug(message);
                    break;
                case INFO:
                    logger.info(message);
                    break;
                case WARN:
                    logger.warn(message);
                    break;
                case ERROR:
                    if (throwable != null) {
                        logger.error(message, throwable);
                    } else {
                        logger.error(message);
                    }
                    break;
            }
        }
    }
}
