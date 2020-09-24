package org.minbox.framework.bulldog.storage.mongodb;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.internal.MongoClientImpl;
import com.mongodb.client.result.InsertOneResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.minbox.framework.bulldog.storage.LogStorage;
import org.minbox.framework.bulldog.storage.LogStorageException;
import org.minbox.framework.fulldog.core.pojo.LogDetails;
import org.minbox.framework.fulldog.core.pojo.NonRequestLogDetails;
import org.minbox.framework.fulldog.core.pojo.RequestLogDetails;
import org.minbox.framework.util.JsonUtils;

/**
 * mongo way to implement {@link LogStorage}
 * <p>
 * Create a {@link MongoClient} instance according to the passed connection string or {@link MongoClientSettings},
 * which is used to manipulate the {@link MongoCollection} log in the {@link MongoDatabase}
 *
 * @author 恒宇少年
 */
@Slf4j
public class MongoLogStorage implements LogStorage {
    private static final String MONGO_ID = "_id";
    private static final String DATABASE_NAME = "bulldog";
    private static final String REQUEST_COLLECTION_NAME = "request_logs";
    private static final String NON_REQUEST_COLLECTION_NAME = "non_request_logs";
    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection requestCollection;
    private MongoCollection nonRequestCollection;

    public MongoLogStorage(String connectionString) {
        this(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build());
    }

    public MongoLogStorage(MongoClientSettings settings) {
        log.info("Use mongo to store logs.");
        this.client = new MongoClientImpl(settings, null);
        this.database = this.client.getDatabase(DATABASE_NAME);
        log.info("The log will be saved to the database：{}.", DATABASE_NAME);
        this.requestCollection = this.database.getCollection(REQUEST_COLLECTION_NAME);
        log.info("Request log save document：{}.", REQUEST_COLLECTION_NAME);
        this.nonRequestCollection = this.database.getCollection(NON_REQUEST_COLLECTION_NAME);
        log.info("Non-request log save document：{}.", NON_REQUEST_COLLECTION_NAME);
    }

    /**
     * Save the {@link LogDetails} to mongodb
     *
     * @param logDetails The {@link LogDetails} instance to be saved
     * @return log mongoId
     * @throws LogStorageException exception
     */
    @Override
    public String save(LogDetails logDetails) throws LogStorageException {
        String jsonValue = JsonUtils.toJsonString(logDetails);
        Document document = Document.parse(jsonValue);
        MongoCollection collection = this.chooseCollection(logDetails);
        InsertOneResult result = collection.insertOne(document);
        if (!result.wasAcknowledged()) {
            throw new LogStorageException("Acknowledged failed and the log has not been saved.");
        }
        return document.getString(MONGO_ID);
    }

    /**
     * Choose the collection to use
     * <p>
     * select the collection to use according to the type of {@link LogDetails}
     *
     * @param details The {@link LogDetails} instance
     * @return The {@link MongoCollection} implement instance
     * @throws LogStorageException exception
     */
    protected MongoCollection chooseCollection(LogDetails details) throws LogStorageException {
        if (details instanceof RequestLogDetails) {
            return requestCollection;
        } else if (details instanceof NonRequestLogDetails) {
            return nonRequestCollection;
        }
        throw new LogStorageException("Unknown type of log detail object.");
    }

    /**
     * Choose the collection to use
     * <p>
     * select the collection to use according to the type of given collection name
     *
     * @param collectName The collection name
     * @return The {@link MongoCollection} implement instance
     * @throws LogStorageException exception
     */
    protected MongoCollection chooseCollection(String collectName) throws LogStorageException {
        if (REQUEST_COLLECTION_NAME.equals(collectName)) {
            return requestCollection;
        } else if (NON_REQUEST_COLLECTION_NAME.equals(collectName)) {
            return nonRequestCollection;
        }
        throw new LogStorageException("Unsupported document.");
    }
}
