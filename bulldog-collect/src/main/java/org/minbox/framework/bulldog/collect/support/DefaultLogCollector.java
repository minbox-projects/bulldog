package org.minbox.framework.bulldog.collect.support;

import org.minbox.framework.bulldog.collect.LogContainer;
import org.minbox.framework.bulldog.collect.LogCollector;
import org.minbox.framework.fulldog.core.enums.LogLevel;
import org.minbox.framework.fulldog.core.pojo.GlobalLog;
import org.minbox.framework.util.StackTraceUtil;

/**
 * The default implementation class of the {@link LogCollector}
 * <p>
 * This class can only be instantiated through {@link LogCollectorFactory}, and objects cannot be created through new
 *
 * @author 恒宇少年
 */
public class DefaultLogCollector extends AbstractLogCollector {

    public DefaultLogCollector(String name, Class sourceClass) {
        super(name, sourceClass);
    }

    @Override
    public void trace(String message) {
        GlobalLog traceLog = createGlobalLog(LogLevel.TRACE, message);
        LogContainer.collect(traceLog);
        printer.print(LogLevel.TRACE, message);
    }

    @Override
    public void trace(String format, Object... arguments) {
        String message = replacePlaceholder(format, arguments);
        this.debug(message);
    }

    @Override
    public void debug(String message) {
        GlobalLog debugLog = createGlobalLog(LogLevel.DEBUG, message);
        LogContainer.collect(debugLog);
        printer.print(LogLevel.DEBUG, message);
    }

    @Override
    public void debug(String format, Object... arguments) {
        String message = replacePlaceholder(format, arguments);
        this.debug(message);
    }

    @Override
    public void info(String message) {
        GlobalLog infoLog = createGlobalLog(LogLevel.INFO, message);
        LogContainer.collect(infoLog);
        printer.print(LogLevel.INFO, message);
    }

    @Override
    public void info(String format, Object... arguments) {
        String message = replacePlaceholder(format, arguments);
        this.info(message);
    }

    @Override
    public void warn(String message) {
        GlobalLog warnLog = createGlobalLog(LogLevel.WARN, message);
        LogContainer.collect(warnLog);
        printer.print(LogLevel.WARN, message);
    }

    @Override
    public void warn(String format, Object... arguments) {
        String message = replacePlaceholder(format, arguments);
        this.warn(message);
    }

    @Override
    public void error(String message) {
        GlobalLog errorLog = createGlobalLog(LogLevel.ERROR, message);
        LogContainer.collect(errorLog);
        printer.print(LogLevel.ERROR, message);
    }

    @Override
    public void error(String format, Object... arguments) {
        String message = replacePlaceholder(format, arguments);
        this.error(message);
    }

    @Override
    public void error(String message, Throwable throwable) {
        String stack = StackTraceUtil.getStackTrace(throwable);
        GlobalLog errorLog = createGlobalLog(LogLevel.ERROR, message)
                .setExceptionStack(stack);
        LogContainer.collect(errorLog);
        printer.print(message, throwable);
    }

    @Override
    public void error(String format, Throwable throwable, Object... arguments) {
        String message = replacePlaceholder(format, arguments);
        this.error(message, throwable);
    }
}
