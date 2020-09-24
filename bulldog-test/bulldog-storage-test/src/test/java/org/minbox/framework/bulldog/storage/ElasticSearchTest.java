package org.minbox.framework.bulldog.storage;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.GetSourceRequest;
import org.elasticsearch.client.core.GetSourceResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.minbox.framework.fulldog.core.pojo.RequestLogDetails;
import org.minbox.framework.util.JsonUtils;

import java.util.Map;
import java.util.UUID;

/**
 * @author 恒宇少年
 */
public class ElasticSearchTest {

    private static RestHighLevelClient client;

    @BeforeAll
    public static void before() {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200)));
    }

    /**
     * 添加数据到elasticsearch
     *
     * @throws Exception
     */
    @Test
    public void addTest() throws Exception {
        IndexRequest request = new IndexRequest("bulldog");
        RequestLogDetails details = new RequestLogDetails();
        details.setRequestIp("127.0.0.1")
                .setRequestUri("/user/1")
                .setLogId(UUID.randomUUID().toString());
        request.source(JsonUtils.toJsonString(details), XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
        System.out.println(response);
    }

    /**
     * 获取数据测试
     *
     * @throws Exception
     */
    @Test
    public void getTest() throws Exception {
        GetRequest request = new GetRequest("bulldog", "DtwPv3QB4XMRtTl7wAuG");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        Map<String, Object> source = response.getSourceAsMap();
        System.out.println(JsonUtils.beautifyJson(source));
    }

    @Test
    public void getSourceTest() throws Exception {
        GetSourceRequest request = new GetSourceRequest("bulldog", "n7mnvXQBioAWvNt0H44Y");
        GetSourceResponse response = client.getSource(request, RequestOptions.DEFAULT);
        Map source = response.getSource();
        System.out.println(JsonUtils.beautifyJson(source));
    }

    @Test
    public void searchTest() throws Exception {
        SearchRequest searchRequest = new SearchRequest("bulldog_request_logs");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.
                matchQuery("requestIp", "127.0.0.1"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        RestStatus status = response.status();
        System.out.println(status);
        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
            System.out.println(searchHit.getId());
            Map source = searchHit.getSourceAsMap();
            System.out.println(JsonUtils.beautifyJson(source));
        }
    }
}
