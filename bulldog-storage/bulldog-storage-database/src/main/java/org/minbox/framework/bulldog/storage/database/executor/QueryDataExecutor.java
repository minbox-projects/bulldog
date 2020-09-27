package org.minbox.framework.bulldog.storage.database.executor;

import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.ParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.variable.ParameterVariable;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Data query executor
 *
 * @author 恒宇少年
 */
public abstract class QueryDataExecutor<R> extends AbstractDataExecutor<R> {
    /**
     * Mapping {@link ResultSet}
     */
    public abstract void mappingResult(ResultSet resultSet, ParameterVariable variable) throws SQLException;

    /**
     * Execute delete SQL
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
        ResultSet resultSet = statement.executeQuery();
        this.mappingResult(resultSet, variable);
        resultSet.close();
        statement.close();
        return this.afterExecute(variable);
    }

    /**
     * Convert "java.sql.Date" to {@link LocalDateTime}
     *
     * @param timestamp The {@link Timestamp} instance
     * @return The {@link LocalDateTime} instance
     */
    protected LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}
