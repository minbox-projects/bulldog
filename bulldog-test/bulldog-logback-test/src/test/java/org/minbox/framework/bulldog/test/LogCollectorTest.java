package org.minbox.framework.bulldog.test;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import org.minbox.framework.bulldog.collect.LogCollector;
import org.minbox.framework.bulldog.collect.LogContainer;
import org.minbox.framework.bulldog.collect.support.LogCollectorFactory;
import org.minbox.framework.fulldog.core.pojo.GlobalLogs;
import org.minbox.framework.util.JsonUtils;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 日志采集器测试类
 *
 * @author 恒宇少年
 */
public class LogCollectorTest {

    final static LogCollector collector = LogCollectorFactory.getLogCollector(LogCollectorTest.class);
    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        /*for (int i = 0; i < 1; i++) {
            executorService.submit(() -> threadSafe());
        }
        executorService.shutdown();*/
    }

    @JunitPerfConfig(threads = 10, duration = 200)
    public void threadSafe() {
        String name = UUID.randomUUID().toString();
        collector.error("用户：{}，开始执行业务逻辑.", name);
        threadTwo(name);
        threadThree(name);
        collector.info("用户：{}，业务逻辑执行完成.", name);
        GlobalLogs globalLogs = LogContainer.getGlobalLogs(true);
        System.out.println(JsonUtils.beautifyJson(globalLogs.toString()));
    }

    public static void threadTwo(String name) {
        collector.error("用户：{}，执行ThreadTwo方法.", name);
    }

    public static void threadThree(String name) {
        new Thread(() -> collector.info("用户：{}，测试子线程.", name)).start();
    }
}
