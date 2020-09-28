package org.minbox.framework.bulldog.storage.database.executor.service;

import org.minbox.framework.bulldog.common.utils.Assert;
import org.minbox.framework.bulldog.storage.database.executor.UpdateDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.ObjectParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.ParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.StringParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.variable.ParameterVariable;
import org.minbox.framework.bulldog.storage.database.table.ServiceInstanceTable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.SERVICE_ID;
import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.SERVICE_LAST_REPORT_TIME;

/**
 * Update service last reported time
 *
 * @author 恒宇少年
 */
public class UpdateServiceReportTimeDataExecutor extends UpdateDataExecutor<Void> {

    @Override
    public String getSql() {
        return ServiceInstanceTable.SQL.UPDATE.lastReportTime();
    }

    @Override
    protected void preExecute(ParameterVariable variable) {
        Assert.notEmpty(variable.getVariable(SERVICE_ID), "The ServiceId cannot be null.");
        if (variable.getVariable(SERVICE_LAST_REPORT_TIME) == null) {
            variable.putVariable(SERVICE_LAST_REPORT_TIME, LocalDateTime.now());
        }
    }

    @Override
    public List<ParameterTypeMapping> getParameterMappings(ParameterVariable variable) {
        return Arrays.asList(
                new ObjectParameterTypeMapping(1, variable.getVariable(SERVICE_LAST_REPORT_TIME)),
                new StringParameterTypeMapping(2, variable.getVariable(SERVICE_ID))
        );
    }

    @Override
    protected Void afterExecute(ParameterVariable variable) {
        // no return result
        return null;
    }
}
