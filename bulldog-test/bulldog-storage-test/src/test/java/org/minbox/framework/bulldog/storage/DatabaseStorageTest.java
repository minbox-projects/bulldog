package org.minbox.framework.bulldog.storage;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.minbox.framework.bulldog.storage.database.executor.log.*;
import org.minbox.framework.bulldog.storage.database.executor.service.InsertServiceDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.service.SelectAllServiceDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.service.SelectServiceIdDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.service.UpdateServiceReportTimeDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.variable.ParameterVariable;
import org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys;
import org.minbox.framework.fulldog.core.enums.LogLevel;
import org.minbox.framework.fulldog.core.pojo.GlobalLog;
import org.minbox.framework.fulldog.core.pojo.NonRequestLogDetails;
import org.minbox.framework.fulldog.core.pojo.RequestLogDetails;
import org.minbox.framework.fulldog.core.pojo.instance.ServiceInstance;
import org.minbox.framework.util.JsonUtils;
import org.springframework.http.HttpMethod;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.*;

/**
 * @author 恒宇少年
 */
@Slf4j
public class DatabaseStorageTest {

    private static DataSource dataSource;

    @BeforeAll
    public static void before() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/bulldog?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername("root");
        config.setPassword("123456");
        dataSource = new HikariDataSource(config);
    }

    /**
     * 保存服务信息
     *
     * @throws SQLException
     */
    @Test
    public void insertService() throws SQLException {
        InsertServiceDataExecutor insertServiceDataExecutor = new InsertServiceDataExecutor();
        ParameterVariable variable = new ParameterVariable();
        variable.putVariable(VariableKeys.SERVICE_NAME, "test-service");
        variable.putVariable(VariableKeys.SERVICE_IP, "localhost");
        variable.putVariable(VariableKeys.SERVICE_PORT, 8080);
        String id = insertServiceDataExecutor.execute(dataSource.getConnection(), variable);
        System.out.println(id);
    }

    /**
     * 查询服务ID
     *
     * @throws SQLException
     */
    @Test
    public void selectService() throws SQLException {
        SelectServiceIdDataExecutor executor = new SelectServiceIdDataExecutor();
        ParameterVariable variable = new ParameterVariable();
        variable.putVariable(VariableKeys.SERVICE_NAME, "test-service");
        variable.putVariable(VariableKeys.SERVICE_PORT, 8080);
        variable.putVariable(VariableKeys.SERVICE_IP, "localhost");
        String id = executor.execute(dataSource.getConnection(), variable);
        log.info("Service主键：{}", id);
    }

    /**
     * 更新最后上报时间
     *
     * @throws SQLException
     */
    @Test
    public void updateLastReportTime() throws SQLException {
        UpdateServiceReportTimeDataExecutor executor = new UpdateServiceReportTimeDataExecutor();
        ParameterVariable variable = new ParameterVariable();
        variable.putVariable(SERVICE_ID, "8e2143be-b665-40e8-be96-01e96f9d3ae7");
        executor.execute(dataSource.getConnection(), variable);
    }

    /**
     * 查询全部的服务
     *
     * @throws SQLException
     */
    @Test
    public void selectAllService() throws SQLException {
        SelectAllServiceDataExecutor executor = new SelectAllServiceDataExecutor();
        List<ServiceInstance> instances = executor.execute(dataSource.getConnection(), ParameterVariable.empty());
        System.out.println(JsonUtils.beautifyJson(instances));
    }

    /**
     * 保存请求日志
     *
     * @throws SQLException
     */
    @Test
    public void insertRequestLog() throws SQLException {
        InsertRequestLogDataExecutor executor = new InsertRequestLogDataExecutor();

        RequestLogDetails details = new RequestLogDetails();
        details.setTraceId(UUID.randomUUID().toString());
        details.setSpanId(UUID.randomUUID().toString());
        details.setRequestUri("/user/lock");
        details.setRequestBodyParams("");
        details.setMethod(HttpMethod.POST.toString());
        details.setExceptionStack("xxx");
        details.setMetadata("xxxxx");
        details.setServiceId("8e2143be-b665-40e8-be96-01e96f9d3ae7");

        ParameterVariable variable = new ParameterVariable();
        variable.putVariable(VariableKeys.REQUEST_LOG_INSTANCE, details);
        String logId = executor.execute(dataSource.getConnection(), variable);
        System.out.println(logId);
    }

    /**
     * 保存非请求日志
     *
     * @throws SQLException
     */
    @Test
    public void insertNonRequestLog() throws SQLException {
        InsertNonRequestLogDataExecutor executor = new InsertNonRequestLogDataExecutor();
        ParameterVariable variable = ParameterVariable.empty();
        NonRequestLogDetails details = new NonRequestLogDetails();
        details.setTraceId(UUID.randomUUID().toString());
        details.setSpanId(UUID.randomUUID().toString());
        details.setGroup("user");
        details.setTag("update");
        details.setStartTime(System.currentTimeMillis());
        details.setEndTime(System.currentTimeMillis());
        details.setServiceId("8e2143be-b665-40e8-be96-01e96f9d3ae7");
        variable.putVariable(VariableKeys.NON_REQUEST_LOG_INSTANCE, details);
        String logId = executor.execute(dataSource.getConnection(), variable);
        System.out.println(logId);
    }

    @Test
    public void insertGlobalLog() throws SQLException {
        InsertGlobalLogDataExecutor executor = new InsertGlobalLogDataExecutor();
        ParameterVariable variable = ParameterVariable.empty();
        variable.putVariable(GLOBAL_LOG_INSTANCE,
                new GlobalLog().setLogId(UUID.randomUUID().toString())
                        .setMessage("这是一个测试内容.")
                        .setLevel(LogLevel.DEBUG)
        );
        String globalLogId = executor.execute(dataSource.getConnection(), variable);
        System.out.println(globalLogId);
    }

    @Test
    public void selectLogByTrace() throws SQLException {
        SelectRequestLogByTraceDataExecutor executor = new SelectRequestLogByTraceDataExecutor();
        ParameterVariable variable = ParameterVariable.empty();
        variable.putVariable(REQUEST_TRACE_ID, "952c2f6f-baaa-4713-a46a-ffac992279c7");
        List<RequestLogDetails> details = executor.execute(dataSource.getConnection(), variable);
        System.out.println(JsonUtils.beautifyJson(details));
    }

    @Test
    public void selectGlobalLogByLogId() throws SQLException {
        SelectGlobalLogByLogIdDataExecutor executor = new SelectGlobalLogByLogIdDataExecutor();
        ParameterVariable variable = ParameterVariable.empty();
        variable.putVariable(GLOBAL_LOG_ID, "7bd31326-14ad-4138-bca3-d1f1b2a37a68");
        List<GlobalLog> globalLogs = executor.execute(dataSource.getConnection(), variable);
        System.out.println(JsonUtils.beautifyJson(globalLogs));
    }
}
