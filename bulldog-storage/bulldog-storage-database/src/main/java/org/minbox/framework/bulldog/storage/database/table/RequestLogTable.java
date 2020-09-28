package org.minbox.framework.bulldog.storage.database.table;

import static org.minbox.framework.bulldog.storage.database.table.RequestLogTable.COLUMNS.*;

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
     * The "bulldog_request_logs" table columns
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
        public static final String REQUEST_URI = "request_uri";
        public static final String REQUEST_METHOD = "request_method";
        public static final String REQUEST_IP = "request_ip";
        public static final String REQUEST_URL_PARAMS = "request_url_params";
        public static final String REQUEST_BODY_PARAMS = "request_body_params";
        public static final String REQUEST_HEADERS = "request_headers";
        public static final String RESPONSE_BODY = "response_body";
        public static final String RESPONSE_STATUS = "response_status";
        public static final String RESPONSE_HEADERS = "response_headers";
        public static final String EXCEPTION_STACK = "exception_stack";
    }


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
    }
}
