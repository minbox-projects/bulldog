package org.minbox.framework.bulldog.storage.database.executor.log;

import org.minbox.framework.bulldog.common.utils.Assert;
import org.minbox.framework.bulldog.common.utils.ClassUtils;
import org.minbox.framework.bulldog.storage.database.executor.QueryDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.ParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.StringParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.variable.ParameterVariable;
import org.minbox.framework.bulldog.storage.database.table.NonRequestLogTable;
import org.minbox.framework.fulldog.core.annotation.SourceMapping;
import org.minbox.framework.fulldog.core.pojo.NonRequestLogDetails;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.NON_REQUEST_LOG_INSTANCES;
import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.NON_REQUEST_TRACE_ID;

/**
 * Query unsolicited logs based on traceId
 *
 * @author 恒宇少年
 */
public class SelectNonRequestLogByTraceDataExecutor extends QueryDataExecutor<List<NonRequestLogDetails>> {

    @Override
    protected String getSql() {
        return NonRequestLogTable.SQL.SELECT.byTraceId();
    }

    @Override
    protected void mappingResult(ResultSet resultSet, ParameterVariable variable) throws SQLException {
        List<NonRequestLogDetails> details = new ArrayList<>();
        while (resultSet.next()) {
            NonRequestLogDetails detail = new NonRequestLogDetails();
            List<Field> fields = ClassUtils.getFields(NonRequestLogDetails.class, true);
            for (Field field : fields) {
                SourceMapping sourceMapping = ClassUtils.getFieldAnnotation(field, SourceMapping.class);
                Object value = resultSet.getObject(sourceMapping.sourceName());
                ClassUtils.setFieldValue(detail, field, value);
            }
            details.add(detail);
        }
        variable.putVariable(NON_REQUEST_LOG_INSTANCES, details);
    }

    @Override
    protected void preExecute(ParameterVariable variable) {
        Assert.notEmpty(variable.getVariable(NON_REQUEST_TRACE_ID), "The traceId cannot be empty.");
    }

    @Override
    protected List<ParameterTypeMapping> getParameterMappings(ParameterVariable variable) {
        return Arrays.asList(
                new StringParameterTypeMapping(1, variable.getVariable(NON_REQUEST_TRACE_ID))
        );
    }

    @Override
    protected List<NonRequestLogDetails> afterExecute(ParameterVariable variable) {
        return variable.getVariable(NON_REQUEST_LOG_INSTANCES);
    }
}
