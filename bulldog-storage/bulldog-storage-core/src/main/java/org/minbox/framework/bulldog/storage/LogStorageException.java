package org.minbox.framework.bulldog.storage;

import lombok.NoArgsConstructor;
import org.minbox.framework.fulldog.core.BullDogLogException;

/**
 * The log storage runtime exception
 *
 * @author 恒宇少年
 */
@NoArgsConstructor
public class LogStorageException extends BullDogLogException {
    public LogStorageException(String message) {
        super(message);
    }

    public LogStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
