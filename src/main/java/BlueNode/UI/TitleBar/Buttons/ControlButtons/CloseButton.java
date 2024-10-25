package BlueNode.UI.TitleBar.Buttons.ControlButtons;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import BlueNode.UI.Components.TextButton;
import javafx.stage.Stage;

public class CloseButton extends TextButton {

    public CloseButton(Stage pStage) {
        super("x", TextButton.BUTTON_PANEL_COLOR, TextButton.HOVER_COLOR, 40, 30);

        setOnAction(event -> pStage.close());

        BaseLogger.log(ELogLevel.SUCCESS, "CloseButton created");
    }
}
