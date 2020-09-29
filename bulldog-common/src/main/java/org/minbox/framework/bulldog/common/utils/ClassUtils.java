package org.minbox.framework.bulldog.common.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Operation class tools
 *
 * @author 恒宇少年
 */
public class ClassUtils {
    /**
     * Get class declared fields
     *
     * @param clazz      The class type
     * @param mergeSuper When it is "true", query the parent field
     * @return
     */
    public static List<Field> getFields(Class clazz, boolean mergeSuper) {
        List<Field> fields = new ArrayList<>();
        if (mergeSuper) {
            Class superClass = clazz.getSuperclass();
            Field[] superClassFields = superClass.getDeclaredFields();
            fields.addAll(Arrays.asList(superClassFields));
        }
        Field[] classFields = clazz.getDeclaredFields();
        fields.addAll(Arrays.asList(classFields));
        return fields;
    }

    /**
     * Get {@link Field} declared annotation
     *
     * @param field          The class {@link Field}
     * @param annotationType The annotation type
     * @param <T>            The annotation class
     * @return annotation instance
     */
    public static <T extends Annotation> T getFieldAnnotation(Field field, Class<T> annotationType) {
        Assert.notNull(field, "The Field cannot be null.");
        return field.getDeclaredAnnotation(annotationType);
    }

    /**
     * Set {@link Field} value
     *
     * @param object The object the field belongs to
     * @param field  field instance
     * @param value  field value
     */
    public static void setFieldValue(Object object, Field field, Object value) {
        try {
            if (value != null) {
                field.setAccessible(true);
                Class type = field.getType();
                if (String.class == type) {
                    field.set(object, value);
                } else if (Long.class == type) {
                    field.setLong(object, Long.valueOf(value.toString()));
                } else if (Integer.class == type) {
                    field.setInt(object, Integer.valueOf(value.toString()));
                } else if (Double.class == type) {
                    field.setDouble(object, Double.valueOf(value.toString()));
                } else if (Timestamp.class == type) {
                    field.set(object, value);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
