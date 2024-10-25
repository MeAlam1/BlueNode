package BlueNode.UI.TitleBar.Buttons.ControlButtons;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import BlueNode.UI.Components.TextButton;
import javafx.stage.Stage;

public class MinimizeButton extends TextButton {

    public MinimizeButton(Stage pStage) {
        super("-", TextButton.BUTTON_PANEL_COLOR, TextButton.HOVER_COLOR, 40, 30);

        setOnAction(event -> pStage.setIconified(true));

        BaseLogger.log(ELogLevel.SUCCESS, "Minimize button created");
    }
}
