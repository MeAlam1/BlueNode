package com.mealam.bluenode.titleBar.buttons.controlButtons;
import com.mealam.bluenode.components.TextButton;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.stage.Stage;

public class CloseButton extends TextButton {

    public CloseButton(Stage pStage) {
        super("x", TextButton.BUTTON_PANEL_COLOR, TextButton.HOVER_COLOR, 40, 30);

        setOnAction(event -> pStage.close());

        BaseLogger.log(BaseLogLevel.SUCCESS, "CloseButton created");
    }
}
