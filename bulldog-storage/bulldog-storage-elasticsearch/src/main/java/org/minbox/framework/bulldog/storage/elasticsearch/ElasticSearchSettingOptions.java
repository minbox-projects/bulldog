package org.minbox.framework.bulldog.storage.elasticsearch;

import lombok.Data;
import org.apache.http.Header;
import org.apache.http.HttpHost;

/**
 * Setting elasticsearch options
 *
 * @author 恒宇少年
 */
@Data
public class ElasticSearchSettingOptions {
    /**
     * HTTP request scheme
     */
    public static final String HTTP_SCHEME = "http";
    /**
     * HTTPS request scheme
     */
    public static final String HTTPS_SCHEME = "https";
    /**
     * The elasticsearch default hostname
     */
    private static final String DEFAULT_HOSTNAME = "localhost";
    /**
     * The elasticsearch default port
     */
    private static final int DEFAULT_PORT = 9200;
    /**
     * rest prefix path
     */
    private String pathPrefix;
    /**
     * List of hosts connected to elasticsearch
     */
    private HttpHost[] connectionHosts;
    /**
     * The default headers
     */
    private Header[] defaultHeaders;

    /**
     * Get default {@link ElasticSearchSettingOptions} instance
     * <p>
     * connect to "localhost:9200" by default
     *
     * @return The {@link ElasticSearchSettingOptions} default instance
     */
    public static ElasticSearchSettingOptions defaultSetting() {
        ElasticSearchSettingOptions options = new ElasticSearchSettingOptions();
        HttpHost singleHost = new HttpHost(DEFAULT_HOSTNAME, DEFAULT_PORT, HTTP_SCHEME);
        options.setConnectionHosts(new HttpHost[]{singleHost});
        return options;
    }
}
