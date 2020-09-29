package org.minbox.framework.bulldog.storage.database.executor.log;

import org.minbox.framework.bulldog.common.utils.Assert;
import org.minbox.framework.bulldog.storage.database.executor.InsertDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.IntegerParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.ParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.StringParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.variable.ParameterVariable;
import org.minbox.framework.bulldog.storage.database.table.GlobalLogTable;
import org.minbox.framework.fulldog.core.pojo.GlobalLog;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.GLOBAL_LOG_ID;
import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.GLOBAL_LOG_INSTANCE;

/**
 * Insert {@link org.minbox.framework.fulldog.core.pojo.GlobalLog} executor
 *
 * @author 恒宇少年
 */
public class InsertGlobalLogDataExecutor extends InsertDataExecutor<String> {
    @Override
    protected String getSql() {
        return GlobalLogTable.SQL.INSERT.getSql();
    }

    @Override
    protected List<ParameterTypeMapping> getParameterMappings(ParameterVariable variable) {
        GlobalLog globalLog = variable.getVariable(GLOBAL_LOG_INSTANCE);
        return Arrays.asList(
                new StringParameterTypeMapping(1, variable.getVariable(GLOBAL_LOG_ID)),
                new StringParameterTypeMapping(2, globalLog.getLogId()),
                new StringParameterTypeMapping(3, globalLog.getLevel().toString()),
                new StringParameterTypeMapping(4, globalLog.getMessage()),
                new StringParameterTypeMapping(5, globalLog.getCallClassName()),
                new StringParameterTypeMapping(6, globalLog.getCallMethodName()),
                new StringParameterTypeMapping(7, globalLog.getCallFileName()),
                new IntegerParameterTypeMapping(8, globalLog.getCallLineNumber()),
                new StringParameterTypeMapping(9, globalLog.getExceptionStack())
        );
    }

    @Override
    protected void preExecute(ParameterVariable variable) {
        GlobalLog globalLog = variable.getVariable(GLOBAL_LOG_INSTANCE);
        Assert.notEmpty(globalLog.getLogId(), "The LogId cannot be empty.");
        Assert.notNull(globalLog.getLevel(), "The LogLevel cannot be empty.");
        Assert.notEmpty(globalLog.getMessage(), "The LogContent cannot be empty.");
        String globalLogId = UUID.randomUUID().toString();
        variable.putVariable(GLOBAL_LOG_ID, globalLogId);
    }

    @Override
    protected String afterExecute(ParameterVariable variable) {
        return variable.getVariable(GLOBAL_LOG_ID);
    }
}
