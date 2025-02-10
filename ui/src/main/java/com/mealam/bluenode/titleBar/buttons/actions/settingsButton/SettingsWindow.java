package com.mealam.bluenode.titleBar.buttons.actions.settingsButton;

import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.stage.Stage;

public class SettingsWindow {

    private final ContextMenu settingsMenu;

    public SettingsWindow(Stage pParentStage) {
        settingsMenu = new ContextMenu();
        settingsMenu.getStyleClass().add("settings-button-action");

        CheckBox loggingCheckBox = new CheckBox("Logging");
        loggingCheckBox.setSelected(true);
        loggingCheckBox.selectedProperty().addListener((pObservable, pOldValue, pNewValue) -> {
            BaseLogger.setLoggingEnabled(pNewValue);
        });

        CustomMenuItem loggingItem = new CustomMenuItem(loggingCheckBox, false);
        settingsMenu.getItems().add(loggingItem);

        BaseLogger.log(BaseLogLevel.SUCCESS, "Settings menu created");
    }

    public void show(Stage owner, double x, double y) {
        settingsMenu.show(owner, x, y);
    }
}
