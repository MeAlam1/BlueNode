package com.mealam.bluenode.utils.logging;

import com.mealam.bluenode.Constants;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

@SuppressWarnings("unused")
public class BaseLogger {

    private static final String LOG_FILE = "log.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static {
        clearLogFile();
        LoggerConfig.configureLogger(Constants.LOGGER, new DefaultLogColorProvider());
    }

    private BaseLogger() {}

    public static boolean isLoggingEnabled() {
        return Constants.isLoggingEnabled;
    }

    public static void setLoggingEnabled(boolean pEnabled) {
        Constants.isLoggingEnabled = pEnabled;
    }

    public static void log(Level pLogLevel, String pMessage, Throwable pThrowable) {
        writeToFile(formatLog(pLogLevel, pMessage));
        if (shouldLog(pLogLevel)) {
            Constants.LOGGER.log(pLogLevel, pMessage, pThrowable);
        }
    }

    public static void log(Level pLogLevel, String pMessage) {
        writeToFile(formatLog(pLogLevel, pMessage));
        if (shouldLog(pLogLevel)) {
            Constants.LOGGER.log(pLogLevel, pMessage);
        }
    }

    public static void logDefinite(String pMessage) {
        Constants.LOGGER.log(BaseLogLevel.DEFINITE, pMessage);
        writeToFile(formatLog(BaseLogLevel.DEFINITE, pMessage));
    }

    private static boolean shouldLog(Level pLogLevel) {
        return pLogLevel == BaseLogLevel.ERROR ||
                pLogLevel == BaseLogLevel.WARNING ||
                pLogLevel == BaseLogLevel.DEFINITE ||
                Constants.isLoggingEnabled;
    }

    private static String formatLog(Level pLogLevel, String pMessage) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        return String.format("[%s] [%s]: %s", timestamp, pLogLevel.getName(), pMessage);
    }

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
