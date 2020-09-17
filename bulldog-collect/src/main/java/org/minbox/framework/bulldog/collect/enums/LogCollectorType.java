package org.minbox.framework.bulldog.collect.enums;

import lombok.Getter;
import org.minbox.framework.bulldog.collect.LogCollector;
import org.minbox.framework.bulldog.collect.support.DefaultLogCollector;

/**
 * The {@link org.minbox.framework.bulldog.collect.LogCollector} type
 *
 * @author 恒宇少年
 */
@Getter
public enum LogCollectorType {
    /**
     * The default collector
     */
    DEFAULT(DefaultLogCollector.class);
    private Class<? extends LogCollector> implClass;

    LogCollectorType(Class<? extends LogCollector> implClass) {
        this.implClass = implClass;
    }
}
