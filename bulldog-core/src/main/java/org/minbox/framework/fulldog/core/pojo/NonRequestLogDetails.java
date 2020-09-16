package org.minbox.framework.fulldog.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.minbox.framework.fulldog.core.enums.LogType;

/**
 * The non-request log entity
 *
 * @author 恒宇少年
 */
@Data
@Accessors(chain = true)
public class NonRequestLogDetails extends LogDetails {
    /**
     * Group to which the log belongs
     */
    private String group;
    /**
     * The log tag
     */
    private String tag;
    /**
     * log type
     */
    private LogType type = LogType.NON_REQUEST;
}
