package org.minbox.framework.fulldog.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.minbox.framework.fulldog.core.annotation.SourceMapping;
import org.minbox.framework.fulldog.core.storage.Columns;
import org.springframework.http.HttpStatus;

/**
 * The http request log entity
 *
 * @author 恒宇少年
 */
@Data
@Accessors(chain = true)
public class RequestLogDetails extends LogDetails {
    /**
     * The http request uri address
     */
    @SourceMapping(sourceName = Columns.RequestLog.REQUEST_URI)
    private String requestUri;
    /**
     * The IP address that initiated the request
     */
    @SourceMapping(sourceName = Columns.RequestLog.REQUEST_IP)
    private String requestIp;
    /**
     * Request method
     */
    @SourceMapping(sourceName = Columns.RequestLog.REQUEST_METHOD)
    private String method;
    /**
     * http request url parameter collection json string
     */
    @SourceMapping(sourceName = Columns.RequestLog.REQUEST_URL_PARAMS)
    private String requestUrlParams;
    /**
     * http request body parameter collection json string
     */
    @SourceMapping(sourceName = Columns.RequestLog.REQUEST_BODY_PARAMS)
    private String requestBodyParams;
    /**
     * http request header collection json string
     */
    @SourceMapping(sourceName = Columns.RequestLog.REQUEST_HEADERS)
    private String requestHeaders;

    /**
     * http response header collection json string
     */
    @SourceMapping(sourceName = Columns.RequestLog.RESPONSE_HEADERS)
    private String responseHeaders;
    /**
     * http request response body content
     */
    @SourceMapping(sourceName = Columns.RequestLog.RESPONSE_BODY)
    private String responseBody;
    /**
     * {@link HttpStatus} of this http response
     */
    @SourceMapping(sourceName = Columns.RequestLog.RESPONSE_STATUS)
    private String responseStatus;
    /**
     * Exception stack content encountered in the request
     */
    @SourceMapping(sourceName = Columns.RequestLog.EXCEPTION_STACK)
    private String exceptionStack;
}
