package org.minbox.framework.bulldog.storage.database.executor.log;

import org.minbox.framework.bulldog.common.utils.Assert;
import org.minbox.framework.bulldog.common.utils.ClassUtils;
import org.minbox.framework.bulldog.storage.database.executor.QueryDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.ParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.StringParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.variable.ParameterVariable;
import org.minbox.framework.bulldog.storage.database.table.RequestLogTable;
import org.minbox.framework.fulldog.core.annotation.SourceMapping;
import org.minbox.framework.fulldog.core.pojo.RequestLogDetails;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.REQUEST_LOG_INSTANCES;
import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.REQUEST_TRACE_ID;

/**
 * Query request log based on traceId
 *
 * @author 恒宇少年
 */
public class SelectRequestLogByTraceDataExecutor extends QueryDataExecutor<List<RequestLogDetails>> {
    @Override
    protected String getSql() {
        return RequestLogTable.SQL.SELECT.byTraceId();
    }

    @Override
    public void mappingResult(ResultSet resultSet, ParameterVariable variable) throws SQLException {
        List<RequestLogDetails> details = new ArrayList<>();
        while (resultSet.next()) {
            RequestLogDetails detail = new RequestLogDetails();
            List<Field> fields = ClassUtils.getFields(RequestLogDetails.class, true);
            for (Field field : fields) {
                SourceMapping mapping = ClassUtils.getFieldAnnotation(field, SourceMapping.class);
                Object value = resultSet.getObject(mapping.sourceName());
                ClassUtils.setFieldValue(detail, field, value);
            }
            details.add(detail);
        }
        variable.putVariable(REQUEST_LOG_INSTANCES, details);
    }

    @Override
    protected List<ParameterTypeMapping> getParameterMappings(ParameterVariable variable) {
        return Arrays.asList(
                new StringParameterTypeMapping(1, variable.getVariable(REQUEST_TRACE_ID))
        );
    }

    @Override
    protected void preExecute(ParameterVariable variable) {
        Assert.notEmpty(variable.getVariable(REQUEST_TRACE_ID), "The LogTraceId cannot be empty.");
    }

    @Override
    protected List<RequestLogDetails> afterExecute(ParameterVariable variable) {
        return variable.getVariable(REQUEST_LOG_INSTANCES);
    }
}
