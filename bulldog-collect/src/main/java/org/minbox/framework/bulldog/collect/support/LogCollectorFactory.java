package org.minbox.framework.bulldog.collect.support;

import org.minbox.framework.bulldog.collect.LogCollector;
import org.minbox.framework.bulldog.collect.enums.LogCollectorType;
import org.minbox.framework.fulldog.core.BullDogLogException;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The {@link LogCollector} factory
 * <p>
 * This class is used to cache or create an instance of the collector object.
 * The unique identifier of the collector is name
 *
 * @author 恒宇少年
 */
public class LogCollectorFactory {
    /**
     * Cache collection of {@link LogCollector}
     * <p>
     * The name of the collector is used as the key of the map collection
     */
    private static final Map<String, LogCollector> COLLECTORS = new ConcurrentHashMap<>();

    /**
     * Create or get cached {@link LogCollector} instance
     *
     * @param sourceClass The name of {@link LogCollector}
     * @return The {@link LogCollector} instance
     */
    public static LogCollector getLogCollector(Class<?> sourceClass) {
        return getLogCollector(sourceClass, LogCollectorType.DEFAULT);
    }

    /**
     * Create {@link LogCollector} instance based on {@link LogCollectorType}
     *
     * @param sourceClass   The name of {@link LogCollector}
     * @param collectorType The type of {@link LogCollector}
     * @return
     */
    public static LogCollector getLogCollector(Class<?> sourceClass, LogCollectorType collectorType) {
        String name = sourceClass.getName();
        if (COLLECTORS.containsKey(name)) {
            return COLLECTORS.get(name);
        }
        LogCollector collector = createInstance(name, sourceClass, collectorType.getImplClass());
        COLLECTORS.put(name, collector);
        return collector;
    }

    /**
     * Create {@link LogCollector} instance based on class reflection
     *
     * @param name           The name of {@link LogCollector}
     * @param collectorClass The class type of {@link LogCollector}
     * @return {@link LogCollector} instance after creation
     */
    private static LogCollector createInstance(String name, Class<?> sourceClass, Class<? extends LogCollector> collectorClass) {
        try {
            Constructor<? extends LogCollector> constructor = collectorClass.getConstructor(String.class, Class.class);
            return constructor.newInstance(name, sourceClass);
        } catch (Exception e) {
            throw new BullDogLogException("Failed to create LogCollector.", e);
        }
    }
}
