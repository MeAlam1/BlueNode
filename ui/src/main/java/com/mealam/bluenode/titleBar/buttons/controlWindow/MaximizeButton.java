package com.mealam.bluenode.titleBar.buttons.controlWindow;

import com.mealam.bluenode.components.buttons.TextButton;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.stage.Stage;

public class MaximizeButton extends TextButton {

    public MaximizeButton(Stage pStage) {
        super("+",
                "maximize-button",
                40,
                30,
                event -> pStage.setMaximized(!pStage.isMaximized()));
        BaseLogger.log(BaseLogLevel.SUCCESS, "Maximize button created");
    }
}
