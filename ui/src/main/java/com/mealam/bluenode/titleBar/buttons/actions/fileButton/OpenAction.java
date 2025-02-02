package com.mealam.bluenode.titleBar.buttons.actions.fileButton;

import com.mealam.bluenode.UIConstants;
import com.mealam.bluenode.components.FolderSelectionPopup;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import java.io.File;
import javafx.application.Platform;
import javafx.stage.Stage;

public class OpenAction {

    public static void handleOpenAction() {
        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                showFolderSelectionPopup(stage);
                BaseLogger.log(BaseLogLevel.SUCCESS, "Opened the OpenAction popup");
            } catch (Exception pException) {
                BaseLogger.log(BaseLogLevel.ERROR, "Error while trying to open the OpenAction popup", pException);
            }
        });
    }

    private static void showFolderSelectionPopup(Stage stage) {
        FolderSelectionPopup popup = new FolderSelectionPopup(false, stage, pDirectory -> {
            BaseLogger.log(BaseLogLevel.INFO, "Selected directory: " + pDirectory.getAbsolutePath());
            UIConstants.CONTENT_BROWSER.updateContent(new File(pDirectory.getAbsolutePath()));
        });

        popup.show();
    }
}
