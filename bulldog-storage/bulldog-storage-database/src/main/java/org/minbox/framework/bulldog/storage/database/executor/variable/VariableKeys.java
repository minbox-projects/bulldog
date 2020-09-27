package org.minbox.framework.bulldog.storage.database.executor.variable;

/**
 * The variable keys define
 *
 * @author 恒宇少年
 */
public interface VariableKeys {
    /**
     * The service instance keys
     */
    String SERVICE_INSTANCE = "ServiceInstance";
    String SERVICE_ID = "ServiceId";
    String SERVICE_NAME = "ServiceName";
    String SERVICE_IP = "ServiceIp";
    String SERVICE_PORT = "ServicePort";
    String SERVICE_HOSTNAME = "ServiceHostName";
    String SERVICE_LAST_REPORT_TIME = "ServiceLastReportTime";
    String SERVICE_CREATE_TIME = "ServiceCreateTime";

    /**
     * The request log keys
     */
    String REQUEST_LOG_INSTANCE = "RequestLogInstance";
    String REQUEST_LOG_PK_ID = "RequestLogPkId";
}
