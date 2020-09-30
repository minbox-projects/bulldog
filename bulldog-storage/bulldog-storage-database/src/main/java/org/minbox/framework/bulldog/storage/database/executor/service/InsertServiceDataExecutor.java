package org.minbox.framework.bulldog.storage.database.executor.service;

import org.minbox.framework.bulldog.common.utils.Assert;
import org.minbox.framework.bulldog.storage.database.executor.InsertDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.IntegerParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.ObjectParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.ParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.StringParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.variable.ParameterVariable;
import org.minbox.framework.bulldog.storage.database.table.ServiceInstanceTable;
import org.minbox.framework.fulldog.core.pojo.instance.ServiceInstance;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.*;

/**
 * Insert {@link ServiceInstance} to the executor of the database
 *
 * @author 恒宇少年
 */
public class InsertServiceDataExecutor extends InsertDataExecutor<String> {
    @Override
    protected String getSql() {
        return ServiceInstanceTable.SQL.INSERT.single();
    }

    @Override
    protected void preExecute(ParameterVariable variable) {
        variable.putVariable(SERVICE_ID, UUID.randomUUID().toString());
        Assert.notEmpty(variable.getVariable(SERVICE_NAME), "The ServiceName cannot be empty.");
        Assert.notEmpty(variable.getVariable(SERVICE_IP), "The ServiceIp cannot be empty.");
        Assert.notEmpty(variable.getVariable(SERVICE_PORT), "The ServicePort cannot be empty.");
    }

    /**
     * Return the value corresponding to "?" in the executed sql
     *
     * @param variable Parametric variable collection object
     * @return The {@link ParameterTypeMapping} collection
     */
    @Override
    protected List<ParameterTypeMapping> getParameterMappings(ParameterVariable variable) {
        return Arrays.asList(
                new StringParameterTypeMapping(1, variable.getVariable(SERVICE_ID)),
                new StringParameterTypeMapping(2, variable.getVariable(SERVICE_NAME)),
                new StringParameterTypeMapping(3, variable.getVariable(SERVICE_IP)),
                new IntegerParameterTypeMapping(4, variable.getVariable(SERVICE_PORT)),
                new StringParameterTypeMapping(5, variable.getVariable(SERVICE_HOSTNAME)),
                new ObjectParameterTypeMapping(6, LocalDateTime.now())
        );
    }

    /**
     * After execute sql
     *
     * @param variable Parametric variable collection object
     * @return Return {@link #execute} method result
     */
    @Override
    protected String afterExecute(ParameterVariable variable) {
        return variable.getVariable(SERVICE_ID);
    }
}
