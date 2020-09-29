package org.minbox.framework.fulldog.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Data source content mapping
 *
 * @author 恒宇少年
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SourceMapping {
    /**
     * Source content name
     *
     * @return content name
     */
    String sourceName();

    /**
     * Source content type
     * <p>
     * default is {@link String}
     *
     * @return content type
     */
    Class<?> sourceType() default String.class;
}
