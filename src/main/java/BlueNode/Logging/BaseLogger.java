package BlueNode.Logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseLogger {
    private static final LogConfig config = new LogConfig(ELogLevel.SUCCESS);
    private static boolean loggingEnabled = true;

    public static void setLoggingEnabled(boolean pEnabled) {
        loggingEnabled = pEnabled;
    }

    public static void setLogLevel(ELogLevel pLevel) {
        config.setLogLevel(pLevel);
    }

    public static void log(ELogLevel pLevel, String pMessage) {
        log(pLevel, pMessage, null);
    }

    public static void log(ELogLevel pLevel, String pMessage, Throwable pThrowable) {
        if (!loggingEnabled) {
            return;
        }

        if (pLevel.getSeverity() >= config.getLogLevel().getSeverity()) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            StringBuilder coloredMessage = new StringBuilder(pLevel.getColor() + "[" + timestamp + "]" + " [" + pLevel + "] " + pMessage);

            if (pThrowable != null) {
                coloredMessage.append("\nException: ").append(pThrowable.getMessage());
                for (StackTraceElement element : pThrowable.getStackTrace()) {
                    coloredMessage.append("\n\tat ").append(element.toString());
                }
            }

            coloredMessage.append("\u001B[0m");
            System.out.println(coloredMessage);
        }
    }
}
