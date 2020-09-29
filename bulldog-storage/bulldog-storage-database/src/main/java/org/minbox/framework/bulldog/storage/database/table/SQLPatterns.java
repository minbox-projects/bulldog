package org.minbox.framework.bulldog.storage.database.table;

import org.minbox.framework.bulldog.common.utils.Assert;

import java.util.function.Function;

/**
 * SQL pattern string
 *
 * @author 恒宇少年
 */
public class SQLPatterns {
    /**
     * The insert sql pattern
     */
    private static final String INSERT_PATTERN = "insert into %s (%s) values(%s);";
    private static final String UPDATE_PATTERN = "update %s set %s";
    private static final String DELETE_PATTERN = "delete %s";
    private static final String SELECT_PATTERN = "select %s from %s";

    private static final String PS_PLACEHOLDER = "?";
    private static final String COLUMN_SPLIT = ", ";
    private static final String EMPTY_STRING = "";
    private static final String EQUAL_STRING = " = ";
    private static final String WHERE = " where ";
    private static final String AND = " and ";
    private static final String SPACE = " ";
    public static final String ALL_COLUMN = "*";
    public static final String ORDER = " order by ";

    /**
     * Format the insert sql of the given table name
     *
     * @param tableName table name
     * @param columns   column name array
     * @return formatted sql
     */
    public static String insertSql(String tableName, String... columns) {
        String columnSql = eachAppendColumns(columns, COLUMN_SPLIT, columnName -> columnName);
        String values = getPlaceholderValues(columns.length);
        return String.format(INSERT_PATTERN, tableName, columnSql, values);
    }

    /**
     * Format the update sql of the given table name
     *
     * @param tableName table name
     * @param columns   column name array
     * @return formatted sql
     */
    public static String updateSql(String tableName, String... columns) {
        String columnSql = eachAppendColumns(columns, COLUMN_SPLIT, columnName -> columnName + EQUAL_STRING + PS_PLACEHOLDER);
        return String.format(UPDATE_PATTERN, tableName, columnSql);
    }

    /**
     * Format the delete sql of the given table name
     *
     * @param tableName table name
     * @param columns   column name array
     * @return formatted sql
     */
    public static String deleteSql(String tableName, String... columns) {
        return String.format(DELETE_PATTERN, tableName);
    }

    /**
     * Format the select sql of the given table name
     *
     * @param tableName table name
     * @param columns   column name array
     * @return formatted sql
     */
    public static String selectSql(String tableName, String... columns) {
        String columnSql = eachAppendColumns(columns, COLUMN_SPLIT, columnName -> columnName);
        return String.format(SELECT_PATTERN, columnSql, tableName);
    }

    /**
     * Format where and sql
     *
     * @param columns column name array
     * @return where sql
     */
    public static String whereAndSql(String... columns) {
        StringBuffer where = new StringBuffer(WHERE);
        String columnSql = eachAppendColumns(columns, AND, columnName -> columnName + EQUAL_STRING + PS_PLACEHOLDER);
        where.append(columnSql);
        return where.toString();
    }

    /**
     * Format order sql
     *
     * @param sortBy  sort by away
     * @param columns column name array
     * @return order sql
     */
    public static String orderBySql(SortBy sortBy, String... columns) {
        StringBuffer orderSql = new StringBuffer(ORDER);
        orderSql.append(eachAppendColumns(columns, COLUMN_SPLIT, columnName -> columnName));
        orderSql.append(SPACE);
        orderSql.append(sortBy.toString());
        return orderSql.toString();
    }

    /**
     * Get placeholder values
     *
     * @param columnLength The column length
     * @return formatted values sql string
     */
    private static String getPlaceholderValues(int columnLength) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < columnLength; i++) {
            buffer.append(PS_PLACEHOLDER);
            buffer.append(i == columnLength - 1 ? EMPTY_STRING : COLUMN_SPLIT);
        }
        return buffer.toString();
    }

    /**
     * Each columns,execute logic function
     *
     * @param columns  The column array
     * @param split    Separator between columns
     * @param function logic function
     * @return formatted column sql
     */
    private static String eachAppendColumns(String[] columns, String split, Function<String, String> function) {
        Assert.notEmpty(columns, "Columns cannot be empty.");
        StringBuffer sql = new StringBuffer();
        for (int i = 0; i < columns.length; i++) {
            sql.append(function.apply(columns[i]));
            sql.append(i == columns.length - 1 ? EMPTY_STRING : split);
        }
        return sql.toString();
    }
}
