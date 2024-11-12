package com.mealam.bluenode.titleBar.buttons.actions.fileButton;

import com.mealam.bluenode.UIController;
import com.mealam.bluenode.components.FolderSelectionPopup;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.application.Platform;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class NewAction {

    public static void handleNewAction() {
        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                showFolderSelectionPopup(stage);
            } catch (Exception pException) {
                BaseLogger.log(BaseLogLevel.ERROR, "Error while trying to open the NewAction popup", pException);
            }
        });
    }

    private static void showFolderSelectionPopup(Stage pStage) {
        FolderSelectionPopup popup = new FolderSelectionPopup(false, pStage, pDirectory -> {
            BaseLogger.log(BaseLogLevel.INFO, "Selected directory: " + pDirectory.getAbsolutePath());
            createNewFolder(pDirectory);
        });

        popup.show();
    }

    private static void createNewFolder(File pDirectory) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create New Folder");
        dialog.setHeaderText("Create a New Folder");
        dialog.setContentText("Enter folder name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(pFolderName -> {
            File newFolder = new File(pDirectory, pFolderName);
            if (newFolder.exists()) {
                BaseLogger.log(BaseLogLevel.WARNING, "Folder already exists.");
            } else if (newFolder.mkdir()) {
                BaseLogger.log(BaseLogLevel.SUCCESS, "Folder created: " + newFolder.getAbsolutePath());
                UIController.CONTENT_BROWSER.updateContent(newFolder);
            } else {
                BaseLogger.log(BaseLogLevel.ERROR, "Failed to create folder.");
            }
        });
    }
}
