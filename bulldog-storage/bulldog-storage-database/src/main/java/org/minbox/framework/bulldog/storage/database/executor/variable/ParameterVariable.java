package org.minbox.framework.bulldog.storage.database.executor.variable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Parametric variable collection
 *
 * @author 恒宇少年
 */
public class ParameterVariable {
    public static final String DEFAULT_GROUP = "DEFAULT";
    private ConcurrentMap<String, Map<String, Object>> VARIABLES = new ConcurrentHashMap();

    /**
     * Get variable value
     *
     * @param group The group to which the variable belongs
     * @param name  The name of variable
     * @param <T>   The type of return value
     * @return The variable value
     */
    public <T> T getVariable(String group, String name) {
        Map<String, Object> groupVariables = Optional.ofNullable(VARIABLES.get(group)).orElse(new HashMap());
        return (T) groupVariables.get(name);
    }

    /**
     * Get variable value
     *
     * @param name The name of variable
     * @param <T>  The type of return value
     * @return The variable value
     */
    public <T> T getVariable(String name) {
        return this.getVariable(DEFAULT_GROUP, name);
    }

    /**
     * Put variable to collection
     *
     * @param name  The name of variable
     * @param value The value of variable
     */
    public void putVariable(String name, Object value) {
        this.putVariable(DEFAULT_GROUP, name, value);
    }

    /**
     * Put variable to collection
     *
     * @param group The group to which the variable belongs
     * @param name  The name of variable
     * @param value The value of variable
     */
    public void putVariable(String group, String name, Object value) {
        Map<String, Object> groupVariables = Optional.ofNullable(VARIABLES.get(group)).orElse(new HashMap());
        groupVariables.put(name, value);
    }
}
