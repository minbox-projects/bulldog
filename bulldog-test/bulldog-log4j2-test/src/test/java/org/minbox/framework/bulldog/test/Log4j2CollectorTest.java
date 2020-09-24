package org.minbox.framework.bulldog.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.minbox.framework.bulldog.collect.LogContainer;
import org.minbox.framework.fulldog.core.pojo.GlobalLogs;

/**
 * Log4j2采集日志测试
 *
 * @author 恒宇少年
 */
@Slf4j
public class Log4j2CollectorTest {
    @Test
    public void log4jTest() {
        log.info("info级别日志");
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        GlobalLogs globalLogs = LogContainer.getGlobalLogs();
        if (globalLogs != null) {
            System.out.println("\n");
            System.out.println(globalLogs.toString());
        }
    }
}
