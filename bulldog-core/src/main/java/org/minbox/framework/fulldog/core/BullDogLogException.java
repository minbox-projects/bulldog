package org.minbox.framework.fulldog.core;

import lombok.NoArgsConstructor;

/**
 * The bulldog log runtime exception
 *
 * @author 恒宇少年
 */
@NoArgsConstructor
public class BullDogLogException extends RuntimeException {
    public BullDogLogException(String message) {
        super(message);
    }

    public BullDogLogException(String message, Throwable cause) {
        super(message, cause);
    }
}
