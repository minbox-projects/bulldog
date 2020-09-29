package org.minbox.framework.fulldog.core.storage;

/**
 * All table columns
 *
 * @author 恒宇少年
 */
public class Columns {
    /**
     * The request log columns
     */
    public static class RequestLog {
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
}
