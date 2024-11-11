package com.mealam.bluenode.titleBar.buttons.controlButtons;

import com.mealam.bluenode.components.TextButton;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.stage.Stage;

public class MinimizeButton extends TextButton {

    public MinimizeButton(Stage pStage) {
        super("-", TextButton.BUTTON_PANEL_COLOR, TextButton.HOVER_COLOR, 40, 30);

        setOnAction(event -> pStage.setIconified(true));

        BaseLogger.log(BaseLogLevel.SUCCESS, "Minimize button created");
    }
}
