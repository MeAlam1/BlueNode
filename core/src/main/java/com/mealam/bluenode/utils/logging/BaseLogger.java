package com.mealam.bluenode.utils.logging;

import com.mealam.bluenode.Constants;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

/**
 * A {@code public class} responsible for logging messages
 * with various logging levels and configurations for bypassing the checks.
 * <p>
 * Logs are also written to a file named {@code bluenode_complete_log.txt}.
 * </p>
 *
 * @author MeAlam
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class BaseLogger {

    private static final String LOG_FILE = "log.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static {
        clearLogFile();
        LoggerConfig.configureLogger(Constants.LOGGER, new DefaultLogColorProvider());
    }

    /**
     * Private constructor to prevent instantiation.
     *
     * @since 1.0.0
     */
    private BaseLogger() {
    }

    /**
     * Checks if general logging is enabled.
     *
     * @return {@code true} if general logging is enabled, {@code false} otherwise.
     * @since 1.0.0
     */
    public static boolean isLoggingEnabled() {
        return Constants.isLoggingEnabled;
    }

    /**
     * Enables or disables general logging.
     *
     * @param pEnabled {@link Boolean} - Indicates whether to enable or disable general logging.
     * @since 1.0.0
     */
    public static void setLoggingEnabled(boolean pEnabled) {
        Constants.isLoggingEnabled = pEnabled;
    }

    /**
     * Logs a message with an associated {@link Throwable} and writes it to a file.
     *
     * @param pLogLevel  {@link Level} - The logging level to use.
     * @param pMessage   {@link String} - The message to log.
     * @param pThrowable {@link Throwable} - The throwable to log with the message.
     * @since 1.0.0
     */
    public static void log(Level pLogLevel, String pMessage, Throwable pThrowable) {
        writeToFile(formatLog(pLogLevel, pMessage));
        if (shouldLog(pLogLevel)) {
            Constants.LOGGER.log(pLogLevel, pMessage, pThrowable);
        }
    }

    /**
     * Logs a message and writes it to a file.
     *
     * @param pLogLevel {@link Level} - The logging level to use.
     * @param pMessage  {@link String} - The message to log.
     * @since 1.0.0
     */
    public static void log(Level pLogLevel, String pMessage) {
        writeToFile(formatLog(pLogLevel, pMessage));
        if (shouldLog(pLogLevel)) {
            Constants.LOGGER.log(pLogLevel, pMessage);
        }
    }

    /**
     * Logs a message bypassing all checks and writes it to a file.
     *
     * @param pMessage {@link String} - The message to log.
     * @since 1.0.0
     */
    public static void logDefinite(String pMessage) {
        Constants.LOGGER.log(BaseLogLevel.DEFINITE, pMessage);
        writeToFile(formatLog(BaseLogLevel.DEFINITE, pMessage));
    }

    /**
     * Determines if a message should be logged based on its logging level.
     *
     * @param pLogLevel {@link Level} - The logging level to check.
     * @return {@code true} if the message should be logged, {@code false} otherwise.
     */
    private static boolean shouldLog(Level pLogLevel) {
        return pLogLevel == BaseLogLevel.ERROR ||
                pLogLevel == BaseLogLevel.WARNING ||
                pLogLevel == BaseLogLevel.DEFINITE ||
                Constants.isLoggingEnabled;
    }

    /**
     * Formats a log message with a timestamp and log level.
     *
     * @param pLogLevel {@link Level} - The logging level of the message.
     * @param pMessage  {@link String} - The message to format.
     * @return A formatted log message.
     */
    private static String formatLog(Level pLogLevel, String pMessage) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        return String.format("[%s] [%s]: %s", timestamp, pLogLevel.getName(), pMessage);
    }

    /**
     * Writes a log message to the file {@code log.txt}.
     *
     * @param message {@link String} - The message to write to the file.
     */
    private static void writeToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to write to log file: " + e.getMessage());
        }
    }

    private static void clearLogFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, false))) {
            writer.write("");
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to clear the log file: " + e.getMessage());
        }
    }
}
