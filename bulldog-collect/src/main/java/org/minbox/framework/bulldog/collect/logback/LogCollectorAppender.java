package org.minbox.framework.bulldog.collect.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import lombok.extern.slf4j.Slf4j;
import org.minbox.framework.bulldog.collect.LogContainer;
import org.minbox.framework.fulldog.core.BullDogLogException;
import org.minbox.framework.fulldog.core.enums.LogLevel;
import org.minbox.framework.fulldog.core.pojo.GlobalLog;
import org.minbox.framework.util.StackTraceUtil;

/**
 * Log collector implemented by logback appender
 * <p>
 * Collect {@link GlobalLog} to {@link LogContainer}，and will output the log to the console
 *
 * @author 恒宇少年
 */
@Slf4j
public class LogCollectorAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    /**
     * Generated log source class stack index
     */
    private static final int SOURCE_CLASS_STACK_INDEX = 0;

    /**
     * Collect log to {@link LogContainer}
     *
     * @param event The logback logging event
     */
    @Override
    protected void append(ILoggingEvent event) {
        String message = event.getFormattedMessage();
        LogLevel level = LogLevel.valueOf(event.getLevel().levelStr);
        StackTraceElement stackTraceElement = getSourceStackTraceElement(event.getCallerData());
        GlobalLog globalLog = createGlobalLog(level, message, stackTraceElement);
        // If it is Error level log，Collect exception stack
        if (LogLevel.ERROR.equals(level)) {
            Throwable throwable = getThrowable(event.getThrowableProxy());
            if (throwable != null) {
                String stack = StackTraceUtil.getStackTrace(throwable);
                globalLog.setExceptionStack(stack);
            }
        }
        // Collect log
        LogContainer.collect(globalLog);
    }

    /**
     * Collect {@link GlobalLog} to {@link LogContainer}
     *
     * @param level             The log level
     * @param message           The log message content
     * @param stackTraceElement The generated log class {@link StackTraceElement}
     * @return The {@link GlobalLog} instance
     */
    private GlobalLog createGlobalLog(LogLevel level, String message, StackTraceElement stackTraceElement) {
        return GlobalLog.create(level, message)
                .setCallFileName(stackTraceElement.getFileName())
                .setCallClassName(stackTraceElement.getClassName())
                .setCallMethodName(stackTraceElement.getMethodName())
                .setCallLineNumber(stackTraceElement.getLineNumber());
    }

    /**
     * Get the exception instance of the error level log
     *
     * @param iThrowableProxy The {@link ILoggingEvent#getThrowableProxy()}
     * @return The {@link Throwable} instance
     */
    private Throwable getThrowable(IThrowableProxy iThrowableProxy) {
        ThrowableProxy throwableProxy = (ThrowableProxy) iThrowableProxy;
        return throwableProxy.getThrowable();
    }

    /**
     * Get the generated log class {@link StackTraceElement} instance
     *
     * @param elements The {@link ILoggingEvent#getCallerData()} array
     * @return Source class {@link StackTraceElement}
     */
    private StackTraceElement getSourceStackTraceElement(StackTraceElement[] elements) {
        if (elements == null || elements.length == 0) {
            throw new BullDogLogException("The StackTraceElement array cannot be empty.");
        }
        return elements[SOURCE_CLASS_STACK_INDEX];
    }
}
