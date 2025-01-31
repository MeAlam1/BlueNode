package com.mealam.bluenode.titleBar.buttons;

import com.mealam.bluenode.components.buttons.TextButton;
import com.mealam.bluenode.titleBar.buttons.actions.settingsButton.SettingsWindow;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.stage.Stage;

public class SettingsButton extends TextButton {

    public SettingsButton(Stage pStage) {
        super("Settings", "settings-button", 70, 30);

        setOnAction(event -> new SettingsWindow(pStage));

        BaseLogger.log(BaseLogLevel.SUCCESS, "Settings button created");
    }
}
