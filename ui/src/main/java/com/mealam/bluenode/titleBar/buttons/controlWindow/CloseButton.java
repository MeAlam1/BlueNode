package com.mealam.bluenode.titleBar.buttons.controlWindow;

import com.mealam.bluenode.components.buttons.TextButton;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.stage.Stage;

public class CloseButton extends TextButton {

    public CloseButton(Stage pStage) {
        super("x",
                "close-button",
                40,
                30,
                event -> pStage.close());
        BaseLogger.log(BaseLogLevel.SUCCESS, "CloseButton created");
    }
}
