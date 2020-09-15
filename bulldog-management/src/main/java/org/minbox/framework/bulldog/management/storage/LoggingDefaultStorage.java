package org.minbox.framework.bulldog.management.storage;

import org.minbox.framework.bulldog.common.GlobalLog;
import org.minbox.framework.bulldog.common.MinBoxLog;
import org.minbox.framework.bulldog.common.response.LoggingResponse;
import org.minbox.framework.bulldog.common.response.ServiceResponse;

import java.sql.SQLException;
import java.util.List;

/**
 * The {@link LoggingStorage} default support
 *
 * @author 恒宇少年
 */
public class LoggingDefaultStorage implements LoggingStorage {
    @Override
    public String insertGlobalLog(String requestLogId, GlobalLog log) throws SQLException {
        return null;
    }

    @Override
    public String insertLog(String serviceDetailId, MinBoxLog log) throws SQLException {
        return null;
    }

    @Override
    public String insertServiceDetail(String serviceId, String serviceIp, int servicePort) throws SQLException {
        return null;
    }

    @Override
    public String selectServiceDetailId(String serviceId, String serviceIp, int servicePort) throws SQLException {
        return null;
    }

    @Override
    public List<ServiceResponse> findAllService() throws SQLException {
        return null;
    }

    @Override
    public List<LoggingResponse> findTopList(int topCount) throws SQLException {
        return null;
    }

    @Override
    public void updateLastReportTime(String serviceDetailId) throws SQLException {

    }
}
