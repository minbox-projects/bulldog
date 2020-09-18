package org.minbox.framework.bulldog.test;

import org.junit.jupiter.api.Test;
import org.minbox.framework.bulldog.collect.LogContainer;
import org.minbox.framework.fulldog.core.pojo.GlobalLogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logback方式采集日志测试
 *
 * @author 恒宇少年
 */
public class LogbackCollectorTest {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(LogCollectorTest.class);

    @Test
    public void collectLog() {
        logger.info("info级别日志");
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        GlobalLogs globalLogs = LogContainer.getGlobalLogs();
        if (globalLogs != null) {
            System.out.println("\n");
            System.out.println(globalLogs.toString());
        }
    }
}
