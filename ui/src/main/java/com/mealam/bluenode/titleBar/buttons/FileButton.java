package com.mealam.bluenode.titleBar.buttons;

import com.mealam.bluenode.components.buttons.TextButton;
import com.mealam.bluenode.titleBar.buttons.actions.fileButton.NewAction;
import com.mealam.bluenode.titleBar.buttons.actions.fileButton.OpenAction;
import com.mealam.bluenode.titleBar.buttons.actions.fileButton.SaveAction;
import com.mealam.bluenode.titleBar.buttons.actions.fileButton.SaveAsAction;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class FileButton extends TextButton {

    public FileButton(Stage pStage) {
        super("File", "file-button", 45, 30);

        ContextMenu contextMenu = createContextMenu();

        setOnAction(event -> {
            double x = localToScreen(getBoundsInLocal()).getMinX();
            double y = localToScreen(getBoundsInLocal()).getMaxY();
            contextMenu.show(FileButton.this, x, y);
        });

        BaseLogger.log(BaseLogLevel.SUCCESS, "FileButton created");
    }

    private ContextMenu createContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");

        newItem.setOnAction(event -> NewAction.handleNewAction());
        openItem.setOnAction(event -> OpenAction.handleOpenAction());
        saveItem.setOnAction(event -> SaveAction.handleSaveAction());
        saveAsItem.setOnAction(event -> SaveAsAction.handleSaveAsAction());

        contextMenu.getItems().addAll(newItem, openItem, saveItem, saveAsItem);

        BaseLogger.log(BaseLogLevel.SUCCESS, "FileButton context menu created");

        return contextMenu;
    }
}
