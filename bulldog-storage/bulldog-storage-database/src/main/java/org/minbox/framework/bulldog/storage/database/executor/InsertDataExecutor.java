package org.minbox.framework.bulldog.storage.database.executor;

import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.ParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.variable.ParameterVariable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Data insertion executor
 *
 * @author 恒宇少年
 */
public abstract class InsertDataExecutor<R> extends AbstractDataExecutor<R> {
    /**
     * Execute insert SQL
     *
     * @param connection The datasource connection
     * @param variable   Parametric variable collection
     * @return execute result value
     * @throws SQLException sql exception
     */
    @Override
    public R execute(Connection connection, ParameterVariable variable) throws SQLException {
        String sql = this.getSql();
        this.preExecute(variable);
        PreparedStatement statement = connection.prepareStatement(sql);
        List<ParameterTypeMapping> mappings = this.getParameterMappings(variable);
        this.setParameters(statement, mappings);
        statement.executeUpdate();
        statement.close();
        return this.afterExecute(variable);
    }
}
