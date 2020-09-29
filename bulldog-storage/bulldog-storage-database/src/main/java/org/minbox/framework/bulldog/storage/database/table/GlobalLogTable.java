package org.minbox.framework.bulldog.storage.database.table;

import static org.minbox.framework.fulldog.core.storage.Columns.GlobalLog.*;

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
     * All SQL statement definitions
     */
    public static class SQL {
        public static class INSERT {
            public static String getSql() {
                return SQLPatterns.insertSql(TABLE_NAME,
                        GLOBAL_LOG_ID,
                        LOG_ID,
                        LOG_LEVEL,
                        MESSAGE,
                        CALL_CLASS_NAME,
                        CALL_METHOD_NAME,
                        CALL_FILE_NAME,
                        CALL_LINE_NUMBER,
                        EXCEPTION_STACK
                );
            }
        }

        public static class SELECT {
            /**
             * Select by {@link org.minbox.framework.fulldog.core.storage.Columns.GlobalLog#LOG_ID}
             *
             * @return formatted sql
             */
            public static String byLogId() {
                return SQLPatterns.selectSql(TABLE_NAME, SQLPatterns.ALL_COLUMN) +
                        SQLPatterns.whereAndSql(LOG_ID) +
                        SQLPatterns.orderBySql(SortBy.desc, CREATE_TIME);
            }
        }
    }
}
