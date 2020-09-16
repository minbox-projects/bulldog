package org.minbox.framework.fulldog.core.enums;

/**
 * Define log level
 *
 * @author 恒宇少年
 */
public enum LogLevel {
    TRACE(1),
    DEBUG(2),
    INFO(3),
    WARN(4),
    ERROR(5);
    private int order;

    LogLevel(int order) {
        this.order = order;
    }

    /**
     *
     * @param logLevel
     * @return
     */
    public boolean thanLevel(LogLevel logLevel) {
        return this.order >= logLevel.order;
    }
}
