package org.minbox.framework.fulldog.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.minbox.framework.fulldog.core.annotation.SourceMapping;
import org.minbox.framework.fulldog.core.pojo.instance.ServiceInstance;

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
@Accessors(chain = true)
public class LogDetails {
    /**
     * Log Unique number
     */
    @SourceMapping(sourceName = "log_id")
    private String logId;
    /**
     * Log link tracking number
     */
    @SourceMapping(sourceName = "trace_id")
    private String traceId;
    /**
     * Log link execution unit number
     */
    @SourceMapping(sourceName = "span_id")
    private String spanId;
    /**
     * Log link execution parent unit number
     */
    @SourceMapping(sourceName = "parent_span_id")
    private String parentSpanId;
    /**
     * The {@link ServiceInstance#getServiceId()}
     */
    @SourceMapping(sourceName = "service_id")
    private String serviceId;
    /**
     * Start execution logic time, unit: millisecond
     */
    @SourceMapping(sourceName = "start_time")
    private long startTime;
    /**
     * End execution logic time, unit: millisecond
     */
    @SourceMapping(sourceName = "end_time")
    private long endTime;
    /**
     * Total time spent executing logic
     */
    @SourceMapping(sourceName = "time_consuming")
    private long timeConsuming;
    /**
     * Additional metadata collection json string
     */
    @SourceMapping(sourceName = "metadata")
    private String metadata;
}
