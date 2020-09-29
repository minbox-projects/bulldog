package org.minbox.framework.bulldog.storage.database.executor.log;

import org.minbox.framework.bulldog.common.utils.Assert;
import org.minbox.framework.bulldog.common.utils.ClassUtils;
import org.minbox.framework.bulldog.storage.database.executor.QueryDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.ParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.StringParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.variable.ParameterVariable;
import org.minbox.framework.bulldog.storage.database.table.GlobalLogTable;
import org.minbox.framework.fulldog.core.annotation.SourceMapping;
import org.minbox.framework.fulldog.core.pojo.GlobalLog;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.GLOBAL_LOG_ID;
import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.GLOBAL_LOG_INSTANCES;

/**
 * Query the global log list according to {@link GlobalLog#getLogId()}
 *
 * @author 恒宇少年
 */
public class SelectGlobalLogByLogIdDataExecutor extends QueryDataExecutor<List<GlobalLog>> {

    @Override
    protected String getSql() {
        return GlobalLogTable.SQL.SELECT.byLogId();
    }

    @Override
    public void mappingResult(ResultSet resultSet, ParameterVariable variable) throws SQLException {
        List<GlobalLog> globalLogs = new ArrayList();
        while (resultSet.next()) {
            GlobalLog globalLog = new GlobalLog();
            List<Field> fields = ClassUtils.getFields(GlobalLog.class, true);
            for (Field field : fields) {
                SourceMapping sourceMapping = ClassUtils.getFieldAnnotation(field, SourceMapping.class);
                Object value = resultSet.getObject(sourceMapping.sourceName());
                ClassUtils.setFieldValue(globalLog, field, value);
            }
            globalLogs.add(globalLog);
        }
        variable.putVariable(GLOBAL_LOG_INSTANCES, globalLogs);
    }

    @Override
    protected void preExecute(ParameterVariable variable) {
        Assert.notEmpty(variable.getVariable(GLOBAL_LOG_ID), "The LogId cannot be empty.");
    }

    @Override
    protected List<ParameterTypeMapping> getParameterMappings(ParameterVariable variable) {
        return Arrays.asList(
                new StringParameterTypeMapping(1, variable.getVariable(GLOBAL_LOG_ID))
        );
    }

    @Override
    protected List<GlobalLog> afterExecute(ParameterVariable variable) {
        return variable.getVariable(GLOBAL_LOG_INSTANCES);
    }
}
