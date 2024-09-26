package BlueNode.Logging;

public class LogConfig {
    private ELogLevel logLevel;

    public LogConfig(ELogLevel pLogLevel) {
        this.logLevel = pLogLevel;
    }

    public ELogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(ELogLevel pLogLevel) {
        this.logLevel = pLogLevel;
    }
}
