package com.mealam.bluenode.titleBar.buttons;

import com.mealam.bluenode.ColorConstants;
import com.mealam.bluenode.components.TextButton;
import com.mealam.bluenode.titleBar.buttons.actions.settingsButton.SettingsWindow;
import com.mealam.bluenode.utils.converter.UIColorConverter;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class SettingsButton extends TextButton {

    public SettingsButton(Stage pStage) {
        super("Settings", UIColorConverter.toJavaFXColor(ColorConstants.NORMAL_COLOR), UIColorConverter.toJavaFXColor(ColorConstants.BUTTON_PANEL_HOVER_COLOR), 70, 30);
        setPadding(new Insets(5, 10, 5, 10));

        setOnAction(event -> new SettingsWindow(pStage));

        BaseLogger.log(BaseLogLevel.SUCCESS, "Settings button created");
    }
}
