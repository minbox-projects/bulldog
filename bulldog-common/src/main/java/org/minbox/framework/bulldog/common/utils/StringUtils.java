package org.minbox.framework.bulldog.common.utils;

import java.util.function.Function;

/**
 * The string utils
 *
 * @author 恒宇少年
 */
public class StringUtils {
    /**
     * Determine whether the string is empty
     *
     * @param str Inspection string
     * @return Return "true" when the string is empty
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * The method to be executed when the given value is not empty
     *
     * @param value    The given value
     * @param function Method to execute when not empty
     * @param <R>      The type of result
     * @return The result value
     */
    public static <R> R notEmptyTodo(Object value, Function function) {
        return value != null ? (R) function.apply(value) : null;
    }
}
