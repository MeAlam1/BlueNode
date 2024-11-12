package com.mealam.bluenode.titleBar.buttons.controlButtons;

import com.mealam.bluenode.ColorConstants;
import com.mealam.bluenode.components.TextButton;
import com.mealam.bluenode.utils.converter.UIColorConverter;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.stage.Stage;

public class MaximizeButton extends TextButton {

    public MaximizeButton(Stage pStage) {
        super("+", UIColorConverter.toJavaFXColor(ColorConstants.BUTTON_PANEL_COLOR), UIColorConverter.toJavaFXColor(ColorConstants.HOVER_COLOR), 40, 30);

        setOnAction(event -> pStage.setMaximized(!pStage.isMaximized()));

        BaseLogger.log(BaseLogLevel.SUCCESS, "Maximize button created");
    }
}
