package org.minbox.framework.bulldog.collect;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.minbox.framework.fulldog.core.pojo.GlobalLog;
import org.minbox.framework.fulldog.core.pojo.GlobalLogs;

import java.util.Optional;

/**
 * Log container
 * <p>
 * Store all {@link GlobalLog} logs generated by the current execution thread
 *
 * @author 恒宇少年
 */
public class LogContainer {
    /**
     * Store the current thread collection {@link GlobalLogs}
     *
     * @see TransmittableThreadLocal
     */
    private static final TransmittableThreadLocal<GlobalLogs> GLOBAL_LOGS_THREAD_LOCAL = new TransmittableThreadLocal();

    /**
     * Collect a {@link GlobalLog} instance to {@link GlobalLogs}
     * <p>
     * If the GlobalLogs object does not exist in the current thread ThreadLocal,
     * it will be written to the ThreadLocal after the new creation
     *
     * @param globalLog Log object collected
     */
    public static void collect(GlobalLog globalLog) {
        GlobalLogs globalLogs = Optional.ofNullable(getGlobalLogs()).orElse(new GlobalLogs());
        globalLogs.add(globalLog);
        GLOBAL_LOGS_THREAD_LOCAL.set(globalLogs);
    }

    /**
     * Get {@link GlobalLogs} instance
     * <p>
     * default do not clear the {@link GlobalLogs} in ThreadLocal
     *
     * @return {@link GlobalLogs} of the current thread
     */
    public static GlobalLogs getGlobalLogs() {
        return getGlobalLogs(false);
    }

    /**
     * Get {@link GlobalLogs} instance
     *
     * @param isClear if value is "true" will clear {@link GlobalLogs} in threadLocal
     * @return {@link GlobalLogs} of the current thread
     */
    public static GlobalLogs getGlobalLogs(boolean isClear) {
        GlobalLogs globalLogs = GLOBAL_LOGS_THREAD_LOCAL.get();
        if (isClear) {
            clear();
        }
        return globalLogs;
    }

    /**
     * Clear {@link GlobalLogs} of the current thread
     */
    public static void clear() {
        Optional.ofNullable(getGlobalLogs())
                .ifPresent(globalLogs -> GLOBAL_LOGS_THREAD_LOCAL.remove());
    }
}
