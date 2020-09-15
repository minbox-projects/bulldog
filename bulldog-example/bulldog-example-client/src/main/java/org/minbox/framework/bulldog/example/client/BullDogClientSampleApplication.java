package org.minbox.framework.bulldog.example.client;

import org.minbox.framework.bulldog.spring.context.annotation.client.EnableLoggingClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 客户端实例项目启动类
 *
 * @author 恒宇少年
 */
@SpringBootApplication
@EnableLoggingClient
@EnableAsync
public class BullDogClientSampleApplication {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(BullDogClientSampleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BullDogClientSampleApplication.class, args);
        logger.info("{}服务启动成功.", "BullDog Client Sample");
    }
}
