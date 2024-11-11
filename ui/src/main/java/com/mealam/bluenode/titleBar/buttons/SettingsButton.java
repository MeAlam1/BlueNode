package com.mealam.bluenode.titleBar.buttons;

import com.mealam.bluenode.components.TextButton;
import com.mealam.bluenode.titleBar.buttons.actions.settingsButton.SettingsWindow;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class SettingsButton extends TextButton {

    public SettingsButton(Stage pStage) {
        super("Settings", TextButton.NORMAL_COLOR, TextButton.HOVER_COLOR, 70, 30);
        setPadding(new Insets(5, 10, 5, 10));

        setOnAction(event -> new SettingsWindow(pStage));

        BaseLogger.log(BaseLogLevel.SUCCESS, "Settings button created");
    }
}
