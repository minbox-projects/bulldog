package org.minbox.framework.fulldog.core.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.minbox.framework.fulldog.core.annotation.SourceMapping;
import org.minbox.framework.fulldog.core.storage.Columns;

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
    @SourceMapping(sourceName = Columns.NonRequestLog.GROUP)
    private String group;
    /**
     * The log tag
     */
    @SourceMapping(sourceName = Columns.NonRequestLog.TAG)
    private String tag;
}
