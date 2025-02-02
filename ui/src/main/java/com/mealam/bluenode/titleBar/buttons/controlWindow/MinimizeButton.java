package com.mealam.bluenode.titleBar.buttons.controlWindow;

import com.mealam.bluenode.components.buttons.TextButton;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.stage.Stage;

public class MinimizeButton extends TextButton {

    public MinimizeButton(Stage pStage) {
        super("-",
                "minimize-button",
                40,
                30,
                event -> pStage.setIconified(true));
        BaseLogger.log(BaseLogLevel.SUCCESS, "Minimize button created");
    }
}
