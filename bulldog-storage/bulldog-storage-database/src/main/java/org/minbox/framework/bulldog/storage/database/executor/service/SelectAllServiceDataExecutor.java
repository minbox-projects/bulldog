package org.minbox.framework.bulldog.storage.database.executor.service;

import org.minbox.framework.bulldog.storage.database.executor.QueryDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.ParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.variable.ParameterVariable;
import org.minbox.framework.fulldog.core.pojo.instance.ServiceInstance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.SERVICE_INSTANCES;

/**
 * Query all service instances
 *
 * @author 恒宇少年
 */
public class SelectAllServiceDataExecutor extends QueryDataExecutor<List<ServiceInstance>> {
    private static final String COLUMN_SERVICE_ID = "service_id";
    private static final String COLUMN_SERVICE_NAME = "service_name";
    private static final String COLUMN_SERVICE_IP = "service_ip";
    private static final String COLUMN_SERVICE_PORT = "service_port";
    private static final String COLUMN_SERVICE_HOSTNAME = "service_hostname";
    private static final String COLUMN_SERVICE_REPORT_TIME = "last_report_time";
    private static final String COLUMN_SERVICE_CREATE_TIME = "create_time";
    private static final String SQL_SELECT_ALL_SERVICE = "select " +
            "service_id, service_name, service_ip, service_port, service_hostname, last_report_time, create_time " +
            "from bulldog_service_instance;";

    @Override
    public String getSql() {
        return SQL_SELECT_ALL_SERVICE;
    }

    @Override
    public List<ParameterTypeMapping> getParameterMappings(ParameterVariable variable) {
        return Collections.emptyList();
    }

    @Override
    public void mappingResult(ResultSet resultSet, ParameterVariable variable) throws SQLException {
        List<ServiceInstance> instances = new ArrayList<>();
        while (resultSet.next()) {
            ServiceInstance instance =
                    ServiceInstance.instance()
                            .setServiceId(resultSet.getString(COLUMN_SERVICE_ID))
                            .setServiceName(resultSet.getString(COLUMN_SERVICE_NAME))
                            .setServiceIp(resultSet.getString(COLUMN_SERVICE_IP))
                            .setServicePort(resultSet.getInt(COLUMN_SERVICE_PORT))
                            .setServiceHostname(resultSet.getString(COLUMN_SERVICE_HOSTNAME))
                            .setLastReportTime(toLocalDateTime(resultSet.getTimestamp(COLUMN_SERVICE_REPORT_TIME)))
                            .setCreateTime(toLocalDateTime(resultSet.getTimestamp(COLUMN_SERVICE_CREATE_TIME)));
            instances.add(instance);
        }
        variable.putVariable(SERVICE_INSTANCES, instances);
    }

    @Override
    protected List<ServiceInstance> afterExecute(ParameterVariable variable) {
        return variable.getVariable(SERVICE_INSTANCES);
    }
}
