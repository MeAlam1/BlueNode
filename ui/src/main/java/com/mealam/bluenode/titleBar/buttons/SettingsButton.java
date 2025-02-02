package com.mealam.bluenode.titleBar.buttons;

import com.mealam.bluenode.components.buttons.TextButton;
import com.mealam.bluenode.titleBar.buttons.actions.settingsButton.SettingsWindow;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.stage.Stage;

public class SettingsButton extends TextButton {

    private final SettingsWindow settingsWindow;

    public SettingsButton(Stage pStage) {
        super("Settings", "settings-button", 80, 30, null);
        settingsWindow = new SettingsWindow(pStage);

        setOnAction(event -> {
            double x = localToScreen(getBoundsInLocal()).getMinX();
            double y = localToScreen(getBoundsInLocal()).getMaxY();
            settingsWindow.show(pStage, x, y);
        });

        BaseLogger.log(BaseLogLevel.SUCCESS, "SettingsButton created");
    }
}
