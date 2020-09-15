package org.minbox.framework.bulldog.spring.context.annotation.client;

import org.minbox.framework.bulldog.client.interceptor.web.LoggingWebInterceptor;
import org.minbox.framework.bulldog.spring.util.LoggingBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Register logging client beans {@link LoggingBeanUtils#registerLoggingClientBeans(BeanDefinitionRegistry)}
 * register {@link LoggingWebInterceptor}
 *
 * @author 恒宇少年
 */
public class LoggingClientBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(LoggingClientBeanDefinitionRegistrar.class);

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        LoggingBeanUtils.registerLoggingClientBeans(registry);
        logger.info("BullDog client beans register successfully.");
    }
}
