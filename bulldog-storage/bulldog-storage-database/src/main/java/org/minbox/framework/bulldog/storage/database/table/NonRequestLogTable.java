package org.minbox.framework.bulldog.storage.database.table;

import static org.minbox.framework.bulldog.storage.database.table.NonRequestLogTable.COLUMNS.*;

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
     * The "bulldog_non_request_logs" table columns
     */
    public static class COLUMNS {
        public static final String LOG_ID = "log_id";
        public static final String TRACE_ID = "trace_id";
        public static final String SPAN_ID = "span_id";
        public static final String PARENT_SPAN_ID = "parent_span_id";
        public static final String SERVICE_ID = "service_id";
        public static final String START_TIME = "start_time";
        public static final String END_TIME = "end_time";
        public static final String TIME_CONSUMING = "time_consuming";
        public static final String METADATA = "metadata";
        public static final String GROUP = "group_name";
        public static final String TAG = "tag";
    }

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
