package org.minbox.framework.bulldog.storage.database.executor.log;

import org.minbox.framework.bulldog.common.utils.Assert;
import org.minbox.framework.bulldog.common.utils.StringUtils;
import org.minbox.framework.bulldog.storage.database.executor.InsertDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.LongParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.ParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.StringParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.variable.ParameterVariable;
import org.minbox.framework.fulldog.core.pojo.RequestLogDetails;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.REQUEST_LOG_ID;
import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.REQUEST_LOG_INSTANCE;

/**
 * Insert {@link RequestLogDetails} executor
 *
 * @author 恒宇少年
 */
public class InsertRequestLogDataExecutor extends InsertDataExecutor<String> {
    /**
     * Insert Request Log SQL
     */
    private static final String SQL_INSERT_LOG =
            "insert into bulldog_request_logs (log_id,\n" +
                    "                                  service_id,\n" +
                    "                                  trace_id,\n" +
                    "                                  parent_span_id,\n" +
                    "                                  span_id,\n" +
                    "                                  start_time,\n" +
                    "                                  end_time,\n" +
                    "                                  time_consuming,\n" +
                    "                                  metadata,\n" +
                    "                                  request_uri,\n" +
                    "                                  request_method,\n" +
                    "                                  request_ip,\n" +
                    "                                  request_url_params,\n" +
                    "                                  request_body_params,\n" +
                    "                                  request_headers,\n" +
                    "                                  response_body,\n" +
                    "                                  response_status,\n" +
                    "                                  response_headers,\n" +
                    "                                  exception_stack)\n" +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    @Override
    public String getSql() {
        return SQL_INSERT_LOG;
    }

    @Override
    protected void preExecute(ParameterVariable variable) {
        RequestLogDetails log = variable.getVariable(REQUEST_LOG_INSTANCE);
        Assert.notNull(log, "The RequestLogDetails cannot be null.");
        Assert.notEmpty(log.getTraceId(), "The TraceId cannot be empty.");
        Assert.notEmpty(log.getServiceInstance().getServiceId(), "The ServiceInstance pk value cannot be not empty.");
        Assert.notEmpty(log.getSpanId(), "The SpanId cannot be empty.");
        variable.putVariable(REQUEST_LOG_ID, UUID.randomUUID().toString());
    }

    @Override
    public List<ParameterTypeMapping> getParameterMappings(ParameterVariable variable) {
        RequestLogDetails log = variable.getVariable(REQUEST_LOG_INSTANCE);
        return Arrays.asList(
                new StringParameterTypeMapping(1, variable.getVariable(REQUEST_LOG_ID)),
                new StringParameterTypeMapping(2, log.getServiceInstance().getServiceId()),
                new StringParameterTypeMapping(3, log.getTraceId()),
                new StringParameterTypeMapping(4, log.getParentSpanId()),
                new StringParameterTypeMapping(5, log.getSpanId()),
                new LongParameterTypeMapping(6, log.getStartTime()),
                new LongParameterTypeMapping(7, log.getEndTime()),
                new LongParameterTypeMapping(8, log.getTimeConsuming()),
                this.notEmptyToParseJson(9, log.getMetadata()),
                new StringParameterTypeMapping(10, log.getRequestUri()),
                // HttpMethod
                new StringParameterTypeMapping(11,
                        StringUtils.notEmptyTodo(log.getMethod(),
                                (Function<HttpMethod, String>) httpMethod -> httpMethod.toString())),
                new StringParameterTypeMapping(12, log.getRequestIp()),
                this.notEmptyToParseJson(13, log.getRequestUrlParams()),
                this.notEmptyToParseJson(14, log.getRequestBodyParams()),
                this.notEmptyToParseJson(15, log.getRequestHeaders()),
                new StringParameterTypeMapping(16, log.getResponseBody()),
                // Response Status
                new StringParameterTypeMapping(17,
                        StringUtils.notEmptyTodo(log.getResponseStatus(),
                                (Function<HttpStatus, String>) httpStatus -> httpStatus.toString())),
                this.notEmptyToParseJson(18, log.getResponseHeaders()),
                new StringParameterTypeMapping(19, log.getExceptionStack())
        );
    }

    @Override
    protected String afterExecute(ParameterVariable variable) {
        return variable.getVariable(REQUEST_LOG_ID);
    }
}
