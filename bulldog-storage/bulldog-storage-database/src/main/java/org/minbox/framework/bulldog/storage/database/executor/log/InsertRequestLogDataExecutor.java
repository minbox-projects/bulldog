package org.minbox.framework.bulldog.storage.database.executor.log;

import org.minbox.framework.bulldog.common.utils.Assert;
import org.minbox.framework.bulldog.storage.database.executor.InsertDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.LongParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.ParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.StringParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.variable.ParameterVariable;
import org.minbox.framework.bulldog.storage.database.table.RequestLogTable;
import org.minbox.framework.fulldog.core.pojo.RequestLogDetails;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.REQUEST_LOG_ID;
import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.REQUEST_LOG_INSTANCE;

/**
 * Insert {@link RequestLogDetails} executor
 *
 * @author 恒宇少年
 */
public class InsertRequestLogDataExecutor extends InsertDataExecutor<String> {

    @Override
    public String getSql() {
        return RequestLogTable.SQL.INSERT.single();
    }

    @Override
    protected void preExecute(ParameterVariable variable) {
        RequestLogDetails log = variable.getVariable(REQUEST_LOG_INSTANCE);
        Assert.notNull(log, "The RequestLogDetails cannot be null.");
        Assert.notEmpty(log.getTraceId(), "The TraceId cannot be empty.");
        Assert.notEmpty(log.getServiceId(), "The ServiceId value cannot be not empty.");
        Assert.notEmpty(log.getSpanId(), "The SpanId cannot be empty.");
        variable.putVariable(REQUEST_LOG_ID, UUID.randomUUID().toString());
    }

    @Override
    public List<ParameterTypeMapping> getParameterMappings(ParameterVariable variable) {
        RequestLogDetails log = variable.getVariable(REQUEST_LOG_INSTANCE);
        return Arrays.asList(
                new StringParameterTypeMapping(1, variable.getVariable(REQUEST_LOG_ID)),
                new StringParameterTypeMapping(2, log.getServiceId()),
                new StringParameterTypeMapping(3, log.getTraceId()),
                new StringParameterTypeMapping(4, log.getParentSpanId()),
                new StringParameterTypeMapping(5, log.getSpanId()),
                new LongParameterTypeMapping(6, log.getStartTime()),
                new LongParameterTypeMapping(7, log.getEndTime()),
                new LongParameterTypeMapping(8, log.getTimeConsuming()),
                new StringParameterTypeMapping(9, log.getMetadata()),
                new StringParameterTypeMapping(10, log.getRequestUri()),
                // HttpMethod
                new StringParameterTypeMapping(11, log.getMethod()),
                new StringParameterTypeMapping(12, log.getRequestIp()),
                new StringParameterTypeMapping(13, log.getRequestUrlParams()),
                new StringParameterTypeMapping(14, log.getRequestBodyParams()),
                new StringParameterTypeMapping(15, log.getRequestHeaders()),
                new StringParameterTypeMapping(16, log.getResponseBody()),
                // Response Status
                new StringParameterTypeMapping(17, log.getResponseStatus()),
                new StringParameterTypeMapping(18, log.getResponseHeaders()),
                new StringParameterTypeMapping(19, log.getExceptionStack())
        );
    }

    @Override
    protected String afterExecute(ParameterVariable variable) {
        return variable.getVariable(REQUEST_LOG_ID);
    }
}
