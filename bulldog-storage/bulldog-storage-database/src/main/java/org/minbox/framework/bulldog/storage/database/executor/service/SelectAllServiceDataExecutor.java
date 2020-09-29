package org.minbox.framework.bulldog.storage.database.executor.service;

import org.minbox.framework.bulldog.common.utils.ClassUtils;
import org.minbox.framework.bulldog.storage.database.executor.QueryDataExecutor;
import org.minbox.framework.bulldog.storage.database.executor.mapping.parameter.ParameterTypeMapping;
import org.minbox.framework.bulldog.storage.database.executor.variable.ParameterVariable;
import org.minbox.framework.bulldog.storage.database.table.ServiceInstanceTable;
import org.minbox.framework.fulldog.core.annotation.SourceMapping;
import org.minbox.framework.fulldog.core.pojo.instance.ServiceInstance;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.minbox.framework.bulldog.storage.database.executor.variable.VariableKeys.SERVICE_INSTANCES;

/**
 * Query all service instances
 *
 * @author 恒宇少年
 */
public class SelectAllServiceDataExecutor extends QueryDataExecutor<List<ServiceInstance>> {
    @Override
    public String getSql() {
        return ServiceInstanceTable.SQL.SELECT.all();
    }

    @Override
    public List<ParameterTypeMapping> getParameterMappings(ParameterVariable variable) {
        return Collections.emptyList();
    }

    @Override
    public void mappingResult(ResultSet resultSet, ParameterVariable variable) throws SQLException {
        List<ServiceInstance> instances = new ArrayList<>();
        while (resultSet.next()) {
            ServiceInstance instance = ServiceInstance.instance();
            List<Field> fields = ClassUtils.getFields(ServiceInstance.class, true);
            for (Field field : fields) {
                SourceMapping sourceMapping = ClassUtils.getFieldAnnotation(field, SourceMapping.class);
                Object value = resultSet.getObject(sourceMapping.sourceName());
                ClassUtils.setFieldValue(instance, field, value);
            }
            instances.add(instance);
        }
        variable.putVariable(SERVICE_INSTANCES, instances);
    }

    @Override
    protected List<ServiceInstance> afterExecute(ParameterVariable variable) {
        return variable.getVariable(SERVICE_INSTANCES);
    }
}
