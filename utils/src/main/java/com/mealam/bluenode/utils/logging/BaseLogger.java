package com.mealam.bluenode.utils.logging;

import com.mealam.bluenode.Constants;

import java.util.logging.Level;

/**
 * A {@code public class} responsible for logging messages
 * with various logging levels and configurations for bypassing the checks.
 * <p>
 * Key Methods:
 * <ul>
 *   <li>{@link #setLoggingEnabled(boolean)} - Enables or disables {logging.</li>
 *   <li>{@link #isLoggingEnabled()} - Checks if logging is enabled.</li>
 *   <li>{@link #log(Level, String, Throwable)} - Logs a message with an associated {@link Throwable}, if logging is enabled.</li>
 *   <li>{@link #log(Level, String)} - Logs a message with a specified logging level, if logging is enabled.</li>
 *   <li>{@link #logDefinite(String)} - Logs a message bypassing all checks.</li>
 * </ul>
 *
 * @author MeAlam
 * @since 1.0.0
 */
public class BaseLogger {

    /**
     * Private constructor to prevent instantiation.
     * <p>
     * This constructor is intentionally empty to prevent creating instances of this class.
     * </p>
     *
     * @author MeAlam
     * @since 1.0.0
     */
    private BaseLogger() {
    }

    /**
     * A {@link Boolean} method that checks if logging is enabled.
     *
     * @return {@code true} if general logging is enabled, {@code false} otherwise.
     * @since 1.0.0
     */
    public static boolean isLoggingEnabled() {
        return Constants.isLoggingEnabled;
    }

    /**
     * A {@code void} to enable or disable general logging.
     *
     * @param pEnabled {@link Boolean} - Indicates whether to enable or disable general logging.
     * @since 1.0.0
     */
    public static void setLoggingEnabled(boolean pEnabled) {
        Constants.isLoggingEnabled = pEnabled;
    }

    static {
        LoggerConfig.configureLogger(Constants.LOGGER, new DefaultLogColorProvider());
    }

    /**
     * A {@code public static void} that logs a message with an associated {@link Throwable}
     * if general logging is enabled.
     *
     * @param pLogLevel  {@link Level} - The logging level to use.
     * @param pMessage   {@link String} - The message to log.
     * @param pThrowable {@link Throwable} - The throwable to log with the message.
     * @since 1.0.0
     */
    public static void log(Level pLogLevel, String pMessage, Throwable pThrowable) {
        if (pLogLevel == BaseLogLevel.ERROR ||
                pLogLevel == BaseLogLevel.WARNING ||
                pLogLevel == BaseLogLevel.DEFINITE ||
                Constants.isLoggingEnabled) {
            Constants.LOGGER.log(pLogLevel, pMessage, pThrowable);
        }
    }

    /**
     * A {@code public static void} that logs a message if general logging is enabled.
     *
     * @param pLogLevel {@link Level} - The logging level to use.
     * @param pMessage  {@link String} - The message to log.
     * @since 1.0.0
     */
    public static void log(Level pLogLevel, String pMessage) {
        if (pLogLevel == BaseLogLevel.ERROR ||
                pLogLevel == BaseLogLevel.WARNING ||
                pLogLevel == BaseLogLevel.DEFINITE ||
                Constants.isLoggingEnabled) {
            Constants.LOGGER.log(pLogLevel, pMessage);
        }
    }

    /**
     * A {@code public static void} that logs a specific message bypassing all checks.
     *
     * @param pMessage {@link String} - The message to log.
     * @since 1.0.0
     */
    public static void logDefinite(String pMessage) {
        Constants.LOGGER.log(BaseLogLevel.DEFINITE, pMessage);
    }
}
