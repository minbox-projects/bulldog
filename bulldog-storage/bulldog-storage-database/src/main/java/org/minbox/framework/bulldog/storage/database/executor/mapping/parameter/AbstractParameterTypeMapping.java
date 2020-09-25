package org.minbox.framework.bulldog.storage.database.executor.mapping.parameter;

import lombok.Getter;

import java.sql.PreparedStatement;

/**
 * Abstract implementation of {@link ParameterTypeMapping}
 *
 * @author 恒宇少年
 */
@Getter
public abstract class AbstractParameterTypeMapping<T> implements ParameterTypeMapping<T> {
    /**
     * The index of {@link PreparedStatement}
     */
    private int parameterIndex;
    /**
     * The value of {@link PreparedStatement}
     */
    private T parameterValue;

    public AbstractParameterTypeMapping(int parameterIndex, T parameterValue) {
        this.parameterIndex = parameterIndex;
        this.parameterValue = parameterValue;
    }
}
