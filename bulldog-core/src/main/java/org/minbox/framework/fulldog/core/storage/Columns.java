package org.minbox.framework.fulldog.core.storage;

/**
 * All table columns
 *
 * @author 恒宇少年
 */
public class Columns {
    /**
     * The service instance columns
     */
    public static class ServiceInstance {
        public static final String SERVICE_ID = "service_id";
        public static final String SERVICE_NAME = "service_name";
        public static final String SERVICE_IP = "service_ip";
        public static final String SERVICE_PORT = "service_port";
        public static final String SERVICE_HOSTNAME = "service_hostname";
        public static final String SERVICE_LAST_REPORT_TIME = "last_report_time";
        public static final String SERVICE_CREATE_TIME = "create_time";
    }

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

    /**
     * The non-request log columns
     */
    public static class NonRequestLog {
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
     * The global log columns
     */
    public static class GlobalLog {

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
}
