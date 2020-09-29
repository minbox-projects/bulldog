package org.minbox.framework.fulldog.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.minbox.framework.fulldog.core.annotation.SourceMapping;
import org.minbox.framework.fulldog.core.enums.LogLevel;
import org.minbox.framework.fulldog.core.storage.Columns;

import java.sql.Timestamp;

/**
 * The Global log
 *
 * @author 恒宇少年
 */
@Data
@Accessors(chain = true)
public class GlobalLog {
    /**
     * The global log id
     */
    @SourceMapping(sourceName = Columns.GlobalLog.GLOBAL_LOG_ID)
    private String globalLogId;
    /**
     * Log id to which it belongs
     */
    @SourceMapping(sourceName = Columns.GlobalLog.LOG_ID)
    private String logId;
    /**
     * The log level
     */
    @SourceMapping(sourceName = Columns.GlobalLog.LOG_LEVEL)
    private LogLevel level;
    /**
     * Log message content
     */
    @SourceMapping(sourceName = Columns.GlobalLog.MESSAGE)
    private String message;
    /**
     * If it is an error log, record the exception stack
     */
    @SourceMapping(sourceName = Columns.GlobalLog.EXCEPTION_STACK)
    private String exceptionStack;
    /**
     * Call log class name
     */
    @SourceMapping(sourceName = Columns.GlobalLog.CALL_CLASS_NAME)
    private String callClassName;
    /**
     * Call log method name
     */
    @SourceMapping(sourceName = Columns.GlobalLog.CALL_METHOD_NAME)
    private String callMethodName;
    /**
     * Call log java file name
     */
    @SourceMapping(sourceName = Columns.GlobalLog.CALL_FILE_NAME)
    private String callFileName;
    /**
     * Call log code line number
     */
    @SourceMapping(sourceName = Columns.GlobalLog.CALL_LINE_NUMBER)
    private int callLineNumber;
    /**
     * the global log create time
     * default is current time millis
     */
    @SourceMapping(sourceName = Columns.GlobalLog.CREATE_TIME)
    private Timestamp createTime;

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
