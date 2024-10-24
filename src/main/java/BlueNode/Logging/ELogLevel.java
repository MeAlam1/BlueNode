package BlueNode.Logging;

public enum ELogLevel {
    DEBUG(2, "\u001B[34m"),   // Blue
    INFO(2, "\u001B[32m"),    // Green
    WARN(3, "\u001B[33m"),    // Yellow
    ERROR(4, "\u001B[31m");    // Red

    private final int severity;
    private final String color;

    ELogLevel(int pSeverity, String pColor) {
        this.severity = pSeverity;
        this.color = pColor;
    }

    public int getSeverity() {
        return severity;
    }

    public String getColor() {
        return color;
    }
}
