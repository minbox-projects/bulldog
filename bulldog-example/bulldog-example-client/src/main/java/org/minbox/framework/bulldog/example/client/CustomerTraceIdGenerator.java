package org.minbox.framework.bulldog.example.client;

import org.minbox.framework.bulldog.client.MinBoxLoggingException;
import org.minbox.framework.bulldog.client.tracer.LogTraceIdGenerator;

import java.util.UUID;

/**
 * 自定义链路编号（TraceID）{@link LogTraceIdGenerator}
 *
 * @author 恒宇少年
 */
public class CustomerTraceIdGenerator implements LogTraceIdGenerator {
    @Override
    public String createTraceId() throws MinBoxLoggingException {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
