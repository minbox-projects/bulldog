package org.minbox.framework.bulldog.storage.database.executor;

import org.minbox.framework.bulldog.storage.database.executor.variable.ParameterVariable;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Data manipulation executor
 *
 * @param <R> the type of the result of the function
 * @author 恒宇少年
 */
public interface DataExecutor<R> {
    /**
     * Execute data method
     *
     * @param variable Parametric variable collection
     * @return The method result value
     */
    R execute(Connection connection, ParameterVariable variable) throws SQLException;
}
