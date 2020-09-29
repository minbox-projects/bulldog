package org.minbox.framework.fulldog.core.pojo.instance;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.minbox.framework.fulldog.core.annotation.SourceMapping;
import org.minbox.framework.fulldog.core.storage.Columns;

import java.time.LocalDateTime;

/**
 * The service instance
 *
 * @author 恒宇少年
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class ServiceInstance {
    /**
     * Log Service ID
     * <p>
     * Used to divide the service to which the log belongs
     */
    @SourceMapping(sourceName = Columns.ServiceInstance.SERVICE_ID)
    private String serviceId;
    /**
     * Log Service name
     */
    @SourceMapping(sourceName = Columns.ServiceInstance.SERVICE_NAME)
    private String serviceName;
    /**
     * Log Service Ipv4 address
     */
    @SourceMapping(sourceName = Columns.ServiceInstance.SERVICE_IP)
    private String serviceIp;
    /**
     * Log Service hostname
     */
    @SourceMapping(sourceName = Columns.ServiceInstance.SERVICE_HOSTNAME)
    private String serviceHostname;
    /**
     * Log Service port
     */
    @SourceMapping(sourceName = Columns.ServiceInstance.SERVICE_PORT)
    private int servicePort;
    /**
     * The last report time
     */
    @SourceMapping(sourceName = Columns.ServiceInstance.SERVICE_LAST_REPORT_TIME)
    private LocalDateTime lastReportTime;
    /**
     * service create time
     */
    @SourceMapping(sourceName = Columns.ServiceInstance.SERVICE_CREATE_TIME)
    private LocalDateTime createTime;

    public static ServiceInstance instance() {
        return new ServiceInstance();
    }
}
