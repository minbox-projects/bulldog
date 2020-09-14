package org.minbox.framework.bulldog.common.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Judge the expected result tool class
 *
 * @author 恒宇少年
 */
public class Assert {
    /**
     * Judge the parameter as null
     *
     * @param source  Object to be inspected
     * @param message Throw an exception message
     */
    public static void notNull(Object source, String message) {
        assertEqualsTrue(source == null, message);
    }

    /**
     * Judge the parameter as empty
     *
     * @param source  Object to be inspected
     * @param message Throw an exception message
     */
    public static void notEmpty(Object source, String message) {
        notNull(source, message);
        // String
        if (source instanceof String) {
            assertEqualsTrue(((String) source).length() == 0, message);
        }
        // List
        else if (source instanceof List) {
            assertEqualsTrue(((List) source).size() == 0, message);
        }
        // Map
        else if (source instanceof Map) {
            assertEqualsTrue(((Map) source).size() == 0, message);
        }
        // Set
        else if (source instanceof Set) {
            assertEqualsTrue(((Set) source).size() == 0, message);
        }
    }

    /**
     * Throw an exception when judged to be true
     *
     * @param condition Analyzing conditions
     * @param message   Throw an exception message
     */
    private static void assertEqualsTrue(boolean condition, String message) {
        if (condition) {
            throwArgumentException(message);
        }
    }

    /**
     * Throw a parameter validation exception
     *
     * @param message Throw an exception message
     */
    private static void throwArgumentException(String message) {
        throw new IllegalArgumentException(message);
    }
}
