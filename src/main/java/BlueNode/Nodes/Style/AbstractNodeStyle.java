package BlueNode.Nodes.Style;

public class AbstractNodeStyle {
    public static final String NODE_BACKGROUND_COLOR = "#222222";
    public static final String TITLE_BAR_COLOR = "linear-gradient(#228B22, #006400)";
    public static final String TITLE_STYLE = "-fx-fill: white;";
    public static final String SELECTED_BORDER_STYLE = "-fx-border-color: blue; -fx-border-width: 2; -fx-background-radius: 10;";
    public static final String DEFAULT_BORDER_STYLE = "-fx-border-color: #FFD700; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-radius: 10;";
    public static final String CONTENT_AREA_STYLE = "-fx-background-color: " + NODE_BACKGROUND_COLOR + "; -fx-padding: 10; -fx-background-radius: 0 0 10 10;";

    public static String getDefaultBorderStyle() {
        return DEFAULT_BORDER_STYLE;
    }

    public static String getSelectedBorderStyle() {
        return SELECTED_BORDER_STYLE;
    }

    public static String getContentAreaStyle() {
        return CONTENT_AREA_STYLE;
    }
}
