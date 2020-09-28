package org.minbox.framework.bulldog.storage.database.table;

/**
 * The "bulldog_global_logs" table define
 *
 * @author 恒宇少年
 */
public class GlobalLogTable {
    /**
     * The table name of "bulldog_global_logs"
     */
    public static final String TABLE_NAME = "bulldog_global_logs";

    /**
     * The "bulldog_global_logs" table columns
     */
    public static class COLUMNS {
        public static final String GLOBAL_LOG_ID = "global_log_id";
        public static final String LOG_ID = "log_id";
        public static final String LOG_LEVEL = "log_level";
        public static final String MESSAGE = "message";
        public static final String CALL_CLASS_NAME = "call_class_name";
        public static final String CALL_METHOD_NAME = "call_method_name";
        public static final String CALL_FILE_NAME = "call_file_name";
        public static final String CALL_LINE_NUMBER = "call_line_number";
        public static final String EXCEPTION_STACK = "exception_stack";
        public static final String CREATE_TIME = "create_time";
    }

    /**
     * All SQL statement definitions
     */
    public static class SQL {
        public static class INSERT {
            public static String getSql() {
                return SQLPatterns.insertSql(TABLE_NAME,
                        COLUMNS.GLOBAL_LOG_ID,
                        COLUMNS.LOG_ID,
                        COLUMNS.LOG_LEVEL,
                        COLUMNS.MESSAGE,
                        COLUMNS.CALL_CLASS_NAME,
                        COLUMNS.CALL_METHOD_NAME,
                        COLUMNS.CALL_FILE_NAME,
                        COLUMNS.CALL_LINE_NUMBER,
                        COLUMNS.EXCEPTION_STACK
                );
            }
        }

        public static class SELECT {
            /**
             * Select by {@link COLUMNS#LOG_ID}
             *
             * @return formatted sql
             */
            public static String byLogId() {
                return SQLPatterns.selectSql(TABLE_NAME, SQLPatterns.ALL_COLUMN) +
                        SQLPatterns.whereAndSql(COLUMNS.LOG_ID);
            }
        }
    }
}
