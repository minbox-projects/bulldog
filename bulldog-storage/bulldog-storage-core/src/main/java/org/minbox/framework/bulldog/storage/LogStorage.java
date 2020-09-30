package org.minbox.framework.bulldog.storage;

import org.minbox.framework.fulldog.core.pojo.LogDetails;

/**
 * The log storage function
 * <p>
 * Provide log storage and query related methods, and implement the interface in different ways
 *
 * @author 恒宇少年
 */
public interface LogStorage {
    /**
     * Register service
     *
     * @param serviceName The name of service
     * @param ipAddress   The ipv4 address of service
     * @param port        The port of service
     * @return ID generated after registering the service
     */
    String registerService(String serviceName, String ipAddress, int port);

    /**
     * Get id of service
     *
     * @param serviceName The name of service
     * @param ipAddress   The ipv4 address of service
     * @param port        The port of service
     * @return The service id
     */
    String getServiceId(String serviceName, String ipAddress, int port);

    /**
     * Save a {@link LogDetails}
     *
     * @param logDetails The {@link LogDetails} instance to be saved
     * @return ID of the log in different storage media
     */
    String save(LogDetails logDetails) throws LogStorageException;

}
