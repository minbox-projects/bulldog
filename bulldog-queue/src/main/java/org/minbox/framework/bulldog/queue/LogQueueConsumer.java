package org.minbox.framework.bulldog.queue;

import org.minbox.framework.fulldog.core.pojo.LogDetails;

/**
 * The log queue consumer
 * <p>
 * Provide method to get {@link LogDetails} from the queue
 *
 * @author 恒宇少年
 */
public interface LogQueueConsumer {
    /**
     * Take and consuming {@link LogDetails}
     *
     * @param logDetails {@link LogDetails} object extracted
     */
    void take(LogDetails logDetails);
}
