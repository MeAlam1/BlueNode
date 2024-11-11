package com.mealam.bluenode.titleBar.buttons.actions.fileButton;
import com.mealam.bluenode.UIController;
import com.mealam.bluenode.components.FolderSelectionPopup;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.File;

public class OpenAction {

    public static void handleOpenAction() {
        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();

                BaseLogger.log(BaseLogLevel.SUCCESS, "Opened the OpenAction popup");

                FolderSelectionPopup popup = new FolderSelectionPopup(false, stage, pDirectory -> {
                    BaseLogger.log(BaseLogLevel.INFO, "Selected directory: " + pDirectory.getAbsolutePath());
                    UIController.CONTENT_BROWSER.updateContent(new File(pDirectory.getAbsolutePath()));
                });

                popup.show();

            } catch (Exception pException) {
                BaseLogger.log(BaseLogLevel.ERROR, "Error while trying to open the OpenAction popup", pException);
            }
        });
    }
}
