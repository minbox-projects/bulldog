package org.minbox.framework.bulldog.storage.elasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.minbox.framework.bulldog.common.utils.StringUtils;
import org.minbox.framework.bulldog.storage.LogStorage;
import org.minbox.framework.bulldog.storage.LogStorageException;
import org.minbox.framework.fulldog.core.pojo.LogDetails;
import org.minbox.framework.fulldog.core.pojo.NonRequestLogDetails;
import org.minbox.framework.fulldog.core.pojo.RequestLogDetails;
import org.minbox.framework.util.JsonUtils;

import java.io.IOException;

/**
 * elasticsearch way to implement {@link LogStorage}
 *
 * @author 恒宇少年
 */
@Slf4j
public class ElasticSearchLogStorage implements LogStorage {
    private static final String INDEX_NAME_REQUEST_LOG = "bulldog_request_logs";
    private static final String INDEX_NAME_NON_REQUEST_LOG = "bulldog_non_request_logs";
    private RestHighLevelClient client;

    /**
     * Use by default {@link ElasticSearchSettingOptions} to create {@link ElasticSearchLogStorage}
     */
    public ElasticSearchLogStorage() {
        this(ElasticSearchSettingOptions.defaultSetting());
    }

    /**
     * Use by given {@link ElasticSearchSettingOptions} to create {@link ElasticSearchLogStorage}
     *
     * @param settingOptions The {@link ElasticSearchSettingOptions} instance
     */
    public ElasticSearchLogStorage(ElasticSearchSettingOptions settingOptions) {
        RestClientBuilder restClientBuilder = RestClient.builder(settingOptions.getConnectionHosts());
        if (!StringUtils.isEmpty(settingOptions.getPathPrefix())) {
            restClientBuilder.setPathPrefix(settingOptions.getPathPrefix());
        }
        if (settingOptions.getDefaultHeaders() != null && settingOptions.getDefaultHeaders().length > 0) {
            restClientBuilder.setDefaultHeaders(settingOptions.getDefaultHeaders());
        }
        this.client = new RestHighLevelClient(restClientBuilder);
    }

    /**
     * Save {@link LogDetails} json string to elasticsearch
     *
     * @param logDetails The {@link LogDetails} instance to be saved
     * @return The unique id of log in elasticsearch
     * @throws LogStorageException exception
     */
    @Override
    public String save(LogDetails logDetails) throws LogStorageException {
        try {
            String indexName = this.chooseIndexName(logDetails);
            IndexRequest request = new IndexRequest(indexName);
            request.source(JsonUtils.toJsonString(logDetails), XContentType.JSON);
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            return response.getId();
        } catch (IOException e) {
            throw new LogStorageException(e.getMessage(), e);
        }
    }

    /**
     * Choose index name
     *
     * @param details Select the index name used according to the {@link LogDetails}
     * @return The elasticsearch index name
     */
    protected String chooseIndexName(LogDetails details) {
        if (details instanceof RequestLogDetails) {
            return INDEX_NAME_REQUEST_LOG;
        } else if (details instanceof NonRequestLogDetails) {
            return INDEX_NAME_NON_REQUEST_LOG;
        }
        throw new LogStorageException("Unknown type of log detail object.");
    }
}
