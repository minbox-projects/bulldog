package org.minbox.framework.bulldog.storage;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.minbox.framework.bulldog.storage.mongo.MongoLogStorage;
import org.minbox.framework.fulldog.core.pojo.NonRequestLogDetails;
import org.minbox.framework.fulldog.core.pojo.RequestLogDetails;
import org.minbox.framework.fulldog.core.pojo.instance.ServiceInstance;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.UUID;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * Mongo连接测试
 *
 * @author 恒宇少年
 */
@Slf4j
public class MongoConnectionTest {
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString("mongodb://admin:123456@localhost:27017/local?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&ssl=false"))
            .applyToConnectionPoolSettings(builder -> {
                builder.minSize(1);
                builder.maxSize(50);
            })
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    LogStorage logStorage = new MongoLogStorage(settings);

    /**
     * 性能测试
     */
    @JunitPerfConfig(threads = 100, duration = 2000)
    public void perfTest() {
        this.requestLogSaveTest();
        this.nonRequestLogSaveTest();
    }

    /**
     * 请求日志保存测试
     */
    @Test
    public void requestLogSaveTest() {
        RequestLogDetails details = new RequestLogDetails();

        details
                .setRequestUri("/user/1")
                .setMethod(HttpMethod.GET)
                .setRequestIp("127.0.0.1")
                .setResponseStatus(HttpStatus.OK)
                .setLogId(UUID.randomUUID().toString())
                .setTraceId(UUID.randomUUID().toString())
                .setEndTime(System.currentTimeMillis())
                .setSpanId(UUID.randomUUID().toString())
                .setTimeConsuming(10)
                .setStartTime(System.currentTimeMillis())
                .setServiceInstance(new ServiceInstance()
                        .setServiceIp("localhost")
                        .setServiceId("user-service")
                        .setServicePort(8080))
                .setMetadata(new HashMap() {
                    {
                        put("group", "request");
                        put("tag", "today");
                    }
                });
        String logId = logStorage.save(details);
        log.info("日志编号：{}", logId);
    }

    /**
     * 非请求日志保存测试
     */
    @Test
    public void nonRequestLogSaveTest() {

        NonRequestLogDetails details = new NonRequestLogDetails();
        details.setLogId(UUID.randomUUID().toString())
                .setTraceId(UUID.randomUUID().toString())
                .setStartTime(System.currentTimeMillis())
                .setSpanId(UUID.randomUUID().toString())
                .setTimeConsuming(10)
                .setServiceInstance(new ServiceInstance()
                        .setServiceIp("localhost")
                        .setServiceId("user-service")
                        .setServicePort(8080));
        String logId = logStorage.save(details);
        log.info("日志编号：{}", logId);
    }

    @Test
    public void findTest() {
        MongoDatabase mongoDatabase = mongoClient.getDatabase("local");
        MongoCollection collection = mongoDatabase.getCollection("bulldog");
        collection.find(
                and(
                        eq("logId", "91a63749-5a43-441b-b096-50662d17bbbd")
                )
        ).sort(Sorts.descending("logId")).forEach(o -> {
            Document document = (Document) o;
            System.out.println(document.toJson());
            System.out.println(document.getString("traceId"));
        });
    }
}
