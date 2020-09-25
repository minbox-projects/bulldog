package org.minbox.framework.bulldog.storage.database.executor.mapping.parameter;

import java.sql.PreparedStatement;

/**
 * Parameter type mapping interface
 *
 * @param <T> The type of parameter
 * @author 恒宇少年
 */
public interface ParameterTypeMapping<T> {
    /**
     * Get index of {@link PreparedStatement} parameter
     *
     * @return The index of parameter
     */
    int getParameterIndex();

    /**
     * Get value of {@link PreparedStatement} parameter
     *
     * @return The value of parameter
     */
    T getParameterValue();
}
