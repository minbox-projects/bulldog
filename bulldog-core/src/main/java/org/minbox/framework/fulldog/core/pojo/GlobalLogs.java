package org.minbox.framework.fulldog.core.pojo;

import org.minbox.framework.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link GlobalLog} collection class
 *
 * @author 恒宇少年
 */
public class GlobalLogs {
    /**
     * The {@link GlobalLog} collection
     */
    private List<GlobalLog> logs = new ArrayList<>();

    /**
     * Add the {@link GlobalLog} to {@link #logs}
     *
     * @param globalLog wait add {@link GlobalLog} instance
     */
    public void add(GlobalLog globalLog) {
        this.logs.add(globalLog);
    }

    /**
     * Rewrite {@link #toString()}, output json formatted string
     *
     * @return Format the string after {@link #logs}
     */
    @Override
    public String toString() {
        return JsonUtils.toJsonString(logs);
    }
}
