package org.minbox.framework.bulldog.common.utils;

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
}
