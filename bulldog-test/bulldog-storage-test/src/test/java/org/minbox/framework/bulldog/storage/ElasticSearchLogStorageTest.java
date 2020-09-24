package org.minbox.framework.bulldog.storage;

import org.junit.jupiter.api.Test;
import org.minbox.framework.bulldog.storage.elasticsearch.ElasticSearchLogStorage;
import org.minbox.framework.fulldog.core.pojo.NonRequestLogDetails;
import org.minbox.framework.fulldog.core.pojo.RequestLogDetails;

import java.util.UUID;

/**
 * @author 恒宇少年
 */
public class ElasticSearchLogStorageTest {

    @Test
    public void testRequestLogAdd() {
        ElasticSearchLogStorage storage = new ElasticSearchLogStorage();
        RequestLogDetails details = new RequestLogDetails();
        details.setRequestIp("127.0.0.1")
                .setRequestUri("/user/10")
                .setLogId(UUID.randomUUID().toString())
                .setStartTime(System.currentTimeMillis())
                .setTimeConsuming(25);
        storage.save(details);
    }

    @Test
    public void testNonRequestLogAdd() {
        ElasticSearchLogStorage storage = new ElasticSearchLogStorage();
        NonRequestLogDetails details = new NonRequestLogDetails();
        details.setGroup("user")
                .setTag("admin")
                .setLogId(UUID.randomUUID().toString())
                .setStartTime(System.currentTimeMillis());
        storage.save(details);
    }
}
