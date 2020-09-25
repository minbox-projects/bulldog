package org.minbox.framework.bulldog.storage.database.executor.mapping.parameter;

/**
 * {@link ParameterTypeMapping} of string type
 *
 * @author 恒宇少年
 */
public class StringParameterTypeMapping extends AbstractParameterTypeMapping<String> {
    public StringParameterTypeMapping(int parameterIndex, String parameterValue) {
        super(parameterIndex, parameterValue);
    }
}
