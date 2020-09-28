package org.minbox.framework.bulldog.storage.database.table;

import org.minbox.framework.fulldog.core.pojo.instance.ServiceInstance;

import static org.minbox.framework.bulldog.storage.database.table.ServiceInstanceTable.COLUMNS.*;

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
     * The table "bulldog_service_instance" columns
     */
    public static class COLUMNS {
        public static final String SERVICE_ID = "service_id";
        public static final String SERVICE_NAME = "service_name";
        public static final String SERVICE_IP = "service_ip";
        public static final String SERVICE_PORT = "service_port";
        public static final String SERVICE_HOSTNAME = "service_hostname";
        public static final String SERVICE_LAST_REPORT_TIME = "last_report_time";
        public static final String SERVICE_CREATE_TIME = "create_time";
    }

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
