package org.minbox.framework.fulldog.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Map;

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
    private String requestUri;
    /**
     * The IP address that initiated the request
     */
    private String requestIp;
    /**
     * Request method
     */
    private HttpMethod method;
    /**
     * http request url parameter collection
     */
    private Map<String, Object> requestUrlParams;
    /**
     * http request body parameter collection
     */
    private Map<String, Object> requestBodyParams;
    /**
     * http request header collection
     */
    private Map<String, String> requestHeaders;

    /**
     * http response header collection
     */
    private Map<String, String> responseHeaders;
    /**
     * http request response body content
     */
    private String responseBody;
    /**
     * {@link HttpStatus} of this http response
     */
    private HttpStatus responseStatus;
    /**
     * Exception stack content encountered in the request
     */
    private String exceptionStack;
}
