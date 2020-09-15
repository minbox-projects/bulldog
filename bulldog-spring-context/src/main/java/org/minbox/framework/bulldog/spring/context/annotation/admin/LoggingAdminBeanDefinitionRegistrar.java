package org.minbox.framework.bulldog.spring.context.annotation.admin;

import org.minbox.framework.bulldog.spring.util.LoggingBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Register logging admin beans{@link LoggingBeanUtils#registerLoggingAdminBeans(BeanDefinitionRegistry)}
 * register {@link org.minbox.framework.bulldog.management.storage.LoggingDataSourceStorage}
 * register {@link org.minbox.framework.bulldog.management.listener.ReportLogStorageListener}
 * register {@link org.minbox.framework.bulldog.management.listener.ReportLogJsonFormatListener}
 * register {@link org.minbox.framework.bulldog.management.endpoint.LoggingEndpoint}
 *
 * @author 恒宇少年
 */
public class LoggingAdminBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(LoggingAdminBeanDefinitionRegistrar.class);

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        LoggingBeanUtils.registerLoggingAdminBeans(registry);
        logger.info("BullDog admin beans register successfully.");
        LoggingBeanUtils.registerLoggingAdminUiBeans(registry);
        logger.info("BullDog admin ui beans register successfully.");
    }
}
