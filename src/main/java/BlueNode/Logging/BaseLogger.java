package BlueNode.Logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseLogger {
    private static final LogConfig config = new LogConfig(ELogLevel.INFO);
    private static boolean loggingEnabled = true;

    public static void setLoggingEnabled(boolean pEnabled) {
        loggingEnabled = pEnabled;
    }

    public static void setLogLevel(ELogLevel pLevel) {
        config.setLogLevel(pLevel);
    }

    public static void log(String pMessage, ELogLevel pLevel) {
        log(pMessage, pLevel, null);
    }

    public static void log(String pMessage, ELogLevel pLevel, Throwable pThrowable) {
        if (!loggingEnabled) {
            return;
        }

        if (pLevel.getSeverity() >= config.getLogLevel().getSeverity()) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String coloredMessage = pLevel.getColor() + timestamp + " [" + pLevel + "] " + pMessage;

            if (pThrowable != null) {
                coloredMessage += "\nException: " + pThrowable.getMessage();
                for (StackTraceElement element : pThrowable.getStackTrace()) {
                    coloredMessage += "\n\tat " + element.toString();
                }
            }

            coloredMessage += "\u001B[0m";
            System.out.println(coloredMessage);
        }
    }
}
