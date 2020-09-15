package org.minbox.framework.bulldog.example.client;

import org.minbox.framework.bulldog.client.span.LogSpanIdGenerator;
import org.minbox.framework.bulldog.client.MinBoxLoggingException;


/**
 * 自定义单元编号（SpanID）{@link LogSpanIdGenerator}
 *
 * @author 恒宇少年
 */
public class CustomerSpanIdGenerator implements LogSpanIdGenerator {
    @Override
    public String createSpanId() throws MinBoxLoggingException {
        String currentTime = String.valueOf(System.currentTimeMillis());
        return String.format("%s-%s", "span", currentTime);
    }
}
