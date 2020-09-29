package org.minbox.framework.bulldog.collect.log4j2;

import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.minbox.framework.bulldog.collect.LogContainer;
import org.minbox.framework.fulldog.core.enums.LogLevel;
import org.minbox.framework.fulldog.core.pojo.GlobalLog;
import org.minbox.framework.util.StackTraceUtil;

import java.io.Serializable;

import static org.minbox.framework.bulldog.collect.log4j2.Log4jCollectorAppender.PLUGIN_NAME;

/**
 * Log collector implemented by log4j appender
 * <p>
 * Collect {@link GlobalLog} to {@link LogContainer}，and will output the log to the console
 *
 * @author 恒宇少年
 */
@Plugin(name = PLUGIN_NAME, category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE, printObject = true)
public class Log4jCollectorAppender extends AbstractAppender {
    /**
     * The name of this log collector
     */
    public static final String PLUGIN_NAME = "LogCollector";
    private static final int SOURCE_CLASS_STACK_INDEX = 18;

    public Log4jCollectorAppender(String name, Filter filter,
                                  Layout<? extends Serializable> layout, boolean ignoreExceptions, Property[] properties) {
        super(name, filter, layout, ignoreExceptions, properties);
    }

    /**
     * Collect log to {@link LogContainer}
     *
     * @param event The log4j provide event instance
     */
    @Override
    public void append(LogEvent event) {
        String message = event.getMessage().getFormattedMessage();
        LogLevel level = LogLevel.valueOf(event.getLevel().toString());
        GlobalLog globalLog = this.createGlobalLog(level, message);
        // If it is Error level log，Collect exception stack
        if (LogLevel.ERROR.equals(level)) {
            if (event.getThrown() != null) {
                String stack = StackTraceUtil.getStackTrace(event.getThrown());
                globalLog.setExceptionStack(stack);
            }
        }
        // Collect log
        LogContainer.collect(globalLog);
    }

    /**
     * Create {@link GlobalLog} instance
     *
     * @param level   The {@link LogLevel}
     * @param message The log message content
     * @return The {@link GlobalLog} instance
     */
    private GlobalLog createGlobalLog(LogLevel level, String message) {
        StackTraceElement[] elements = (new Exception()).getStackTrace();
        StackTraceElement element = elements[SOURCE_CLASS_STACK_INDEX];
        return GlobalLog.create(level, message)
                .setCallFileName(element.getFileName())
                .setCallClassName(element.getClassName())
                .setCallMethodName(element.getMethodName())
                .setCallLineNumber(element.getLineNumber());
    }

    /**
     * Provide a method to create the current Appender
     * <p>
     * Classes annotated with {@link PluginFactory} must be static
     *
     * @param name       The name of plugin
     * @param layout     Output content format, if not configured, use the default
     * @param filter     Log filtering
     * @param properties Attribute collection
     * @return The {@link Log4jCollectorAppender} instance
     */
    @PluginFactory
    public static Log4jCollectorAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginElement("Filter") final Filter filter,
            @PluginElement("Property") Property[] properties) {
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new Log4jCollectorAppender(name, filter, layout, true, properties);
    }
}
