package org.minbox.framework.bulldog.storage.database;

import org.minbox.framework.bulldog.common.utils.StringUtils;
import org.minbox.framework.bulldog.storage.LogStorage;
import org.minbox.framework.bulldog.storage.LogStorageException;
import org.minbox.framework.bulldog.storage.database.executor.log.InsertNonRequestLogDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.log.InsertRequestLogDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.service.InsertServiceDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.service.SelectServiceIdDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.variable.ParameterVariable;
import org.minbox.framework.fulldog.core.pojo.LogDetails;
import org.minbox.framework.fulldog.core.pojo.NonRequestLogDetails;
import org.minbox.framework.fulldog.core.pojo.RequestLogDetails;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.*;

/**
 * database way to implement {@link LogStorage}
 *
 * @author 恒宇少年
 */
public class DatabaseLogStorage implements LogStorage {
    private DataSource dataSource;

    public DatabaseLogStorage(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Register Service
     *
     * @param serviceName The name of service
     * @param ipAddress   The ipv4 address of service
     * @param port        The port of service
     * @return Return the registered ServiceID
     */
    @Override
    public String registerService(String serviceName, String ipAddress, int port) {
        try {
            InsertServiceDataExecutor executor = new InsertServiceDataExecutor();
            ParameterVariable variable = ParameterVariable.empty();
            variable.putVariable(SERVICE_NAME, serviceName);
            variable.putVariable(SERVICE_IP, ipAddress);
            variable.putVariable(SERVICE_PORT, port);
            return executor.execute(dataSource.getConnection(), variable);
        } catch (Exception e) {
            throw new LogStorageException(e.getMessage(), e);
        }
    }

    /**
     * Get Service ID
     *
     * @param serviceName The name of service
     * @param ipAddress   The ipv4 address of service
     * @param port        The port of service
     * @return The id of service
     */
    @Override
    public String getServiceId(String serviceName, String ipAddress, int port) {
        try {
            Connection connection = dataSource.getConnection();
            SelectServiceIdDataExecutor executor = new SelectServiceIdDataExecutor();
            ParameterVariable variable = ParameterVariable.empty();
            variable.putVariable(SERVICE_NAME, serviceName);
            variable.putVariable(SERVICE_IP, ipAddress);
            variable.putVariable(SERVICE_PORT, port);
            String serviceId = executor.execute(connection, variable);
            if (StringUtils.isEmpty(serviceId)) {
                return this.registerService(serviceName, ipAddress, port);
            }
            return serviceId;
        } catch (Exception e) {
            throw new LogStorageException(e.getMessage(), e);
        }
    }

    /**
     * Save request/non-request log to database
     *
     * @param logDetails The {@link LogDetails} instance to be saved
     * @return
     * @throws LogStorageException
     */
    @Override
    public String save(LogDetails logDetails) throws LogStorageException {
        try {
            Connection connection = dataSource.getConnection();
            // Request Log
            if (logDetails instanceof RequestLogDetails) {
                InsertRequestLogDataExecutor executor = new InsertRequestLogDataExecutor();
                ParameterVariable variable = ParameterVariable.empty();
                variable.putVariable(REQUEST_LOG_INSTANCE, logDetails);
                return executor.execute(connection, variable);
            }
            // Non Request Log
            else if (logDetails instanceof NonRequestLogDetails) {
                InsertNonRequestLogDataExecutor executor = new InsertNonRequestLogDataExecutor();
                ParameterVariable variable = ParameterVariable.empty();
                variable.putVariable(NON_REQUEST_LOG_INSTANCE, logDetails);
                return executor.execute(connection, variable);
            }
        } catch (SQLException e) {
            throw new LogStorageException(e.getMessage(), e);
        }
        throw new LogStorageException("Failed to save log details.");
    }
}
