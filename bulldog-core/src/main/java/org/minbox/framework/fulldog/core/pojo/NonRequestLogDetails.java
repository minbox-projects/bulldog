package org.minbox.framework.fulldog.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

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
}
