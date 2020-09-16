package org.minbox.framework.fulldog.core.pojo;

import lombok.Data;
import org.minbox.framework.fulldog.core.pojo.instance.ServiceInstance;

import java.util.Map;

/**
 * The log base entity
 *
 * @author 恒宇少年
 * @see GlobalLogs
 * @see GlobalLog
 * @see RequestLogDetails
 * @see NonRequestLogDetails
 */
@Data
public class LogDetails {
    /**
     * Log Unique number
     */
    private String logId;
    /**
     * Log link tracking number
     */
    private String traceId;
    /**
     * Log link execution unit number
     */
    private String spanId;
    /**
     * Log link execution parent unit number
     */
    private String parentSpanId;
    /**
     * The {@link ServiceInstance} instance
     */
    private ServiceInstance serviceInstance;
    /**
     * {@link GlobalLog} log collection
     */
    private GlobalLogs globalLogs;
    /**
     * Start execution logic time, unit: millisecond
     */
    private long startTime;
    /**
     * End execution logic time, unit: millisecond
     */
    private long endTime;
    /**
     * Total time spent executing logic
     */
    private long timeConsuming;
    /**
     * Additional metadata collection
     */
    private Map<String, Object> metadata;
}
