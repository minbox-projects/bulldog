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
     * Save a {@link LogDetails}
     *
     * @param logDetails The {@link LogDetails} instance to be saved
     * @return The {@link LogDetails#getLogId()} value
     */
    String save(LogDetails logDetails) throws LogStorageException;
}
