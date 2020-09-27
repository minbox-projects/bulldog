package org.minbox.framework.bulldog.storage.database.executor.mapping.parameter;

/**
 * {@link ParameterTypeMapping} of object type
 *
 * @author 恒宇少年
 */
public class ObjectParameterTypeMapping extends AbstractParameterTypeMapping<Object> {
    public ObjectParameterTypeMapping(int parameterIndex, Object parameterValue) {
        super(parameterIndex, parameterValue);
    }
}
