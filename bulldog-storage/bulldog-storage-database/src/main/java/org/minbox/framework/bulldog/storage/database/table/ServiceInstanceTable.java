package org.minbox.framework.bulldog.storage.database.table;

import org.minbox.framework.fulldog.core.pojo.instance.ServiceInstance;

import static org.minbox.framework.fulldog.core.storage.Columns.ServiceInstance.*;

/**
 * The "bulldog_service_instance" table define
 *
 * @author 恒宇少年
 */
public class ServiceInstanceTable {
    /**
     * The table name of {@link ServiceInstance}
     */
    public static final String TABLE_NAME = "bulldog_service_instance";

    /**
     * All SQL statement definitions
     */
    public static class SQL {
        public static class INSERT {
            /**
             * Insert single {@link ServiceInstance}
             *
             * @return formatted sql string
             */
            public static String single() {
                return SQLPatterns.insertSql(TABLE_NAME,
                        SERVICE_ID,
                        SERVICE_NAME,
                        SERVICE_IP,
                        SERVICE_PORT,
                        SERVICE_HOSTNAME
                );
            }
        }

        public static class SELECT {
            /**
             * Query all service instances
             *
             * @return formatted sql string
             */
            public static String all() {
                return SQLPatterns.selectSql(TABLE_NAME, SQLPatterns.ALL_COLUMN);
            }

            /**
             * Query service ID based on service name, service IP, service port number
             *
             * @return formatted sql string
             */
            public static String byNameAndIpAndPort() {
                return SQLPatterns.selectSql(TABLE_NAME, SERVICE_ID) +
                        SQLPatterns.whereAndSql(SERVICE_NAME, SERVICE_IP, SERVICE_PORT);
            }
        }

        public static class UPDATE {
            /**
             * Update {@link ServiceInstance} last report time
             *
             * @return formatted sql string
             */
            public static String lastReportTime() {
                return SQLPatterns.updateSql(TABLE_NAME, SERVICE_LAST_REPORT_TIME)
                        + SQLPatterns.whereAndSql(SERVICE_ID);
            }
        }
    }
}
