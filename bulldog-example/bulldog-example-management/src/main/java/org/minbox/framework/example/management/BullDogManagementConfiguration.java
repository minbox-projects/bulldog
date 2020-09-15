package org.minbox.framework.example.management;

import org.minbox.framework.bulldog.management.LoggingAdminFactoryBean;
import org.minbox.framework.bulldog.management.storage.LoggingDataSourceStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 管理端配置示例
 *
 * @author 恒宇少年
 */
@Configuration
public class BullDogManagementConfiguration {

    /**
     * 当bean容器内存在{@link DataSource}对象实例时创建{@link LoggingAdminFactoryBean}示例
     *
     * @param dataSource {@link DataSource}
     * @return {@link LoggingAdminFactoryBean}
     */
    @Bean
    public LoggingAdminFactoryBean dataSourceLoggingAdminFactoryBean(DataSource dataSource) {
        LoggingAdminFactoryBean adminFactoryBean = new LoggingAdminFactoryBean();
        adminFactoryBean.setShowConsoleReportLog(true);
        adminFactoryBean.setFormatConsoleLogJson(true);
        adminFactoryBean.setLoggingStorage(new LoggingDataSourceStorage(dataSource));
        return adminFactoryBean;
    }
}
