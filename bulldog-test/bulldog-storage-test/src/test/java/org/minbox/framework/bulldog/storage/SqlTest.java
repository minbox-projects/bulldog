package org.minbox.framework.bulldog.storage;

import org.minbox.framework.bulldog.storage.database.table.GlobalLogTable;
import org.minbox.framework.bulldog.storage.database.table.RequestLogTable;
import org.minbox.framework.bulldog.storage.database.table.SQLPatterns;

/**
 * @author 恒宇少年
 */
public class SqlTest {
    public static void main(String[] args) {
        // Insert
        String insertSql = GlobalLogTable.SQL.INSERT.getSql();
        System.out.println(insertSql);
        // Update
        String updateSql = SQLPatterns.updateSql("bulldog_global_logs", "xx");
        System.out.println(updateSql);
        // Where
        String whereSql = SQLPatterns.whereAndSql("log_id", "xxx");
        System.out.println(updateSql + whereSql);
        // Delete
        String deleteSql = SQLPatterns.deleteSql("bulldog_global_logs");
        System.out.println(deleteSql);

        String selectSql = SQLPatterns.selectSql("bulldog_global_logs", SQLPatterns.ALL_COLUMN);
        System.out.println(selectSql);

        String sql = GlobalLogTable.SQL.SELECT.byLogId();
        System.out.println(sql);

        String orderSql = RequestLogTable.SQL.SELECT.byTraceId();
        System.out.println(orderSql);
    }
}
