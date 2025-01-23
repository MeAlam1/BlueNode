package com.mealam.bluenode.titleBar.buttons.controlButtons;

import com.mealam.bluenode.ColorConstants;
import com.mealam.bluenode.components.TextButton;
import com.mealam.bluenode.utils.converter.UIColorConverter;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.stage.Stage;

public class MinimizeButton extends TextButton {

    public MinimizeButton(Stage pStage) {
        super("-", UIColorConverter.toJavaFXColor(ColorConstants.BUTTON_PANEL_COLOR), UIColorConverter.toJavaFXColor(ColorConstants.BUTTON_PANEL_HOVER_COLOR), 40, 30);

        setOnAction(event -> pStage.setIconified(true));

        BaseLogger.log(BaseLogLevel.SUCCESS, "Minimize button created");
    }
}
