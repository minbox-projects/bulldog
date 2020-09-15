package org.minbox.framework.example.management;

import org.minbox.framework.bulldog.spring.context.annotation.admin.EnableLoggingAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 管理端启动类入口
 *
 * @author 恒宇少年
 */
@SpringBootApplication
@EnableLoggingAdmin
public class BullDogManagementSampleApplication {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(BullDogManagementSampleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BullDogManagementSampleApplication.class, args);
        logger.info("{}服务启动成功.", "BullDog Admin Sample");
    }


}
