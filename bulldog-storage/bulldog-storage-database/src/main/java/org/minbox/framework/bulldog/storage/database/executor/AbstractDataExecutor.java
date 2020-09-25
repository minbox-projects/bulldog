package org.minbox.framework.bulldog.storage.database.executor;

import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.ParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.variable.ParameterVariable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Abstract implementation of {@link DataExecutor}
 *
 * @author 恒宇少年
 */
public abstract class AbstractDataExecutor<R> implements DataExecutor<R> {
    /**
     * Return execute sql
     *
     * @return The sql string
     */
    public abstract String getSql();

    /**
     * Get {@link ParameterTypeMapping} list
     *
     * @param variable Parametric variable collection object
     * @return {@link ParameterTypeMapping} list for executing SQL
     */
    public abstract List<ParameterTypeMapping> getParameterMappings(ParameterVariable variable);

    /**
     * Execute SQL pre-method
     *
     * @param variable Parametric variable collection object
     */
    protected void preExecute(ParameterVariable variable) {
        // Do nothing by default
    }

    /**
     * Execute SQL after-method
     *
     * @param variable Parametric variable collection object
     * @return The {@link #execute} method result value
     */
    protected abstract R afterExecute(ParameterVariable variable);

    /**
     * Set {@link PreparedStatement} index parameter
     *
     * @param statement The {@link PreparedStatement} instance
     * @param mappings  {@link ParameterTypeMapping} list for executing SQL
     */
    public void setParameters(PreparedStatement statement, List<ParameterTypeMapping> mappings) throws SQLException {
        if (mappings != null && mappings.size() > 0) {
            for (ParameterTypeMapping mapping : mappings) {
                this.chooseAndSetParameterValue(statement, mapping);
            }
        }
    }

    /**
     * Choose and set parameter type value
     *
     * @param statement The {@link PreparedStatement} instance
     * @param mapping   The {@link ParameterTypeMapping} instance
     * @throws SQLException sql exception
     */
    private void chooseAndSetParameterValue(PreparedStatement statement, ParameterTypeMapping mapping) throws SQLException {
        int index = mapping.getParameterIndex();
        Object value = mapping.getParameterValue();
        if (value instanceof Integer) {
            statement.setInt(index, ((Integer) value).intValue());
        } else if (value instanceof Double) {
            statement.setDouble(index, ((Double) value).doubleValue());
        } else if (value instanceof Boolean) {
            statement.setBoolean(index, ((Boolean) value).booleanValue());
        } else if (value instanceof String) {
            statement.setString(index, String.valueOf(value));
        } else if (value instanceof Long) {
            statement.setLong(index, ((Long) value).longValue());
        } else {
            statement.setObject(index, value);
        }
    }
}
