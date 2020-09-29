package org.minbox.framework.bulldog.storage.database.table;

import static org.minbox.framework.fulldog.core.storage.Columns.RequestLog.*;

/**
 * The "bulldog_request_logs" table define
 *
 * @author 恒宇少年
 */
public class RequestLogTable {
    /**
     * The table name of "bulldog_request_logs"
     */
    public static final String TABLE_NAME = "bulldog_request_logs";

    /**
     * All SQL statement definitions
     */
    public static class SQL {

        public static class INSERT {
            /**
             * Insert single {@link org.minbox.framework.fulldog.core.pojo.RequestLogDetails}
             *
             * @return formatted sql string
             */
            public static String single() {
                return SQLPatterns.insertSql(TABLE_NAME,
                        LOG_ID,
                        SERVICE_ID,
                        TRACE_ID,
                        PARENT_SPAN_ID,
                        SPAN_ID,
                        START_TIME,
                        END_TIME,
                        TIME_CONSUMING,
                        METADATA,
                        REQUEST_URI,
                        REQUEST_METHOD,
                        REQUEST_IP,
                        REQUEST_URL_PARAMS,
                        REQUEST_BODY_PARAMS,
                        REQUEST_HEADERS,
                        RESPONSE_BODY,
                        RESPONSE_STATUS,
                        RESPONSE_HEADERS,
                        EXCEPTION_STACK
                );
            }
        }

        public static class SELECT {
            /**
             * Query request log based on traceId
             *
             * @return formatted sql string
             */
            public static String byTraceId() {
                return SQLPatterns.selectSql(TABLE_NAME, SQLPatterns.ALL_COLUMN) +
                        SQLPatterns.whereAndSql(TRACE_ID) +
                        SQLPatterns.orderBySql(SortBy.desc, START_TIME);
            }
        }
    }
}
