package org.minbox.framework.bulldog.queue;

import org.minbox.framework.fulldog.core.pojo.LogDetails;

/**
 * The log queue provider
 * <p>
 * Provide {@link LogDetails} write to queue method
 *
 * @author 恒宇少年
 */
public interface LogQueueProvider {
    /**
     * Put a {@link LogDetails} to log queue
     *
     * @param logDetails The {@link LogDetails} instance
     */
    void put(LogDetails logDetails);
}
