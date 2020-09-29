package org.minbox.framework.bulldog.storage.database.table;

import static org.minbox.framework.fulldog.core.storage.Columns.NonRequestLog.*;

/**
 * The "bulldog_non_request_logs" table define
 *
 * @author 恒宇少年
 */
public class NonRequestLogTable {
    /**
     * The table name of "bulldog_non_request_logs"
     */
    public static final String TABLE_NAME = "bulldog_non_request_logs";

    /**
     * All SQL statement definitions
     */
    public static class SQL {
        public static class INSERT {
            public static String single() {
                return SQLPatterns.insertSql(TABLE_NAME,
                        LOG_ID,
                        TRACE_ID,
                        SPAN_ID,
                        PARENT_SPAN_ID,
                        SERVICE_ID,
                        START_TIME,
                        END_TIME,
                        TIME_CONSUMING,
                        METADATA,
                        GROUP,
                        TAG
                );
            }
        }
    }
}
