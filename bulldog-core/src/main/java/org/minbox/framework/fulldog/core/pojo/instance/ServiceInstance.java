package org.minbox.framework.fulldog.core.pojo.instance;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * The service instance
 *
 * @author 恒宇少年
 */
@Data
@Accessors(chain = true)
public class ServiceInstance {
    /**
     * Log Service ID
     * <p>
     * Used to divide the service to which the log belongs
     */
    private String serviceId;
    /**
     * Log Service Ipv4 address
     */
    private String serviceIp;
    /**
     * Log Service hostname
     */
    private String serviceHostname;
    /**
     * Log Service port
     */
    private int servicePort;
}
