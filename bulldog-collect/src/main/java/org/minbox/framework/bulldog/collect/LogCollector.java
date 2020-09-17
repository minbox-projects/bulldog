package org.minbox.framework.bulldog.collect;


import org.minbox.framework.fulldog.core.enums.LogLevel;

/**
 * Define the log collection function
 * <p>
 * Provide a method of log collection, which is implemented by different frameworks
 *
 * @author 恒宇少年
 */
public interface LogCollector {
    /**
     * Log a message at the {@link LogLevel#TRACE} level.
     *
     * @param message the message string to be logged
     */
    void trace(String message);

    /**
     * Log a message at the {@link LogLevel#TRACE} level according to the specified format
     * and argument.
     *
     * @param format    the format string
     * @param arguments the argument list
     */
    void trace(String format, Object... arguments);

    /**
     * Log a message at the {@link LogLevel#DEBUG} level.
     *
     * @param message the message string to be logged
     */
    void debug(String message);

    /**
     * Log a message at the {@link LogLevel#DEBUG} level according to the specified format
     * and argument.
     *
     * @param format    the format string
     * @param arguments the argument list
     */
    void debug(String format, Object... arguments);

    /**
     * Log a message at the {@link LogLevel#INFO} level.
     *
     * @param message the message string to be logged
     */
    void info(String message);

    /**
     * Log a message at the {@link LogLevel#INFO} level according to the specified format
     * and argument.
     *
     * @param format    the format string
     * @param arguments the argument list
     */
    void info(String format, Object... arguments);

    /**
     * Log a message at the {@link LogLevel#WARN} level.
     *
     * @param message the message string to be logged
     */
    void warn(String message);

    /**
     * Log a message at the {@link LogLevel#WARN} level according to the specified format
     * and argument.
     *
     * @param format    the format string
     * @param arguments the argument list
     */
    void warn(String format, Object... arguments);

    /**
     * Log a message at the {@link LogLevel#ERROR} level.
     *
     * @param message the message string to be logged
     */
    void error(String message);

    /**
     * Log a message at the {@link LogLevel#ERROR} level according to the specified format
     * and argument.
     *
     * @param format    the format string
     * @param arguments the argument list
     */
    void error(String format, Object... arguments);

    /**
     * Log a message at the {@link LogLevel#ERROR} level.
     *
     * @param message   the message accompanying the exception
     * @param throwable the exception (throwable) to log
     */
    void error(String message, Throwable throwable);

    /**
     * Log a message at the {@link LogLevel#ERROR} level according to the specified format
     * and argument.
     *
     * @param format    the format string
     * @param throwable the exception (throwable) to log
     * @param arguments the argument list
     */
    void error(String format, Throwable throwable, Object... arguments);
}
