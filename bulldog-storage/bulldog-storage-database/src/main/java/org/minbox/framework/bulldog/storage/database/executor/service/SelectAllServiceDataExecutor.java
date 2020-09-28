package org.minbox.framework.bulldog.storage.database.executor.service;

import org.minbox.framework.bulldog.storage.database.executor.QueryDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.ParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.variable.ParameterVariable;
import org.minbox.framework.bulldog.storage.database.table.ServiceInstanceTable;
import org.minbox.framework.fulldog.core.pojo.instance.ServiceInstance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.SERVICE_INSTANCES;
import static org.minbox.framework.bulldog.storage.database.table.ServiceInstanceTable.COLUMNS.*;

/**
 * Query all service instances
 *
 * @author 恒宇少年
 */
public class SelectAllServiceDataExecutor extends QueryDataExecutor<List<ServiceInstance>> {
    @Override
    public String getSql() {
        return ServiceInstanceTable.SQL.SELECT.all();
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
                            .setServiceId(resultSet.getString(SERVICE_ID))
                            .setServiceName(resultSet.getString(SERVICE_NAME))
                            .setServiceIp(resultSet.getString(SERVICE_IP))
                            .setServicePort(resultSet.getInt(SERVICE_PORT))
                            .setServiceHostname(resultSet.getString(SERVICE_HOSTNAME))
                            .setLastReportTime(toLocalDateTime(resultSet.getTimestamp(SERVICE_LAST_REPORT_TIME)))
                            .setCreateTime(toLocalDateTime(resultSet.getTimestamp(SERVICE_CREATE_TIME)));
            instances.add(instance);
        }
        variable.putVariable(SERVICE_INSTANCES, instances);
    }

    @Override
    protected List<ServiceInstance> afterExecute(ParameterVariable variable) {
        return variable.getVariable(SERVICE_INSTANCES);
    }
}
