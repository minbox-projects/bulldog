package org.minbox.framework.fulldog.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * The log caller instance
 *
 * @author 恒宇少年
 */
@Data
@Accessors(chain = true)
public class CallerData {
    /**
     * Call log class name
     */
    private String className;
    /**
     * Call log method name
     */
    private String methodName;
    /**
     * Call log java file name
     */
    private String fileName;
    /**
     * Call log code line number
     */
    private int lineNumber;
}
