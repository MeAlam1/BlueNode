package BlueNode.UI.TitleBar.Buttons;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import BlueNode.UI.Components.TextButton;
import BlueNode.UI.TitleBar.Buttons.Actions.SettingsButton.SettingsWindow;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class SettingsButton extends TextButton {

    public SettingsButton(Stage pStage) {
        super("Settings", TextButton.NORMAL_COLOR, TextButton.HOVER_COLOR, 70, 30);
        setPadding(new Insets(5, 10, 5, 10));

        setOnAction(event -> new SettingsWindow(pStage));

        BaseLogger.log(ELogLevel.INFO, "Settings button created");
    }
}
