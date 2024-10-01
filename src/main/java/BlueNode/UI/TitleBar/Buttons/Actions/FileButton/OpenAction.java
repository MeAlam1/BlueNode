package BlueNode.UI.TitleBar.Buttons.Actions.FileButton;

import BlueNode.Logging.BaseLogger;
import BlueNode.BlueNode;
import BlueNode.Logging.ELogLevel;
import BlueNode.UI.Components.FolderSelectionPopup;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.File;

public class OpenAction {

    public static void handleOpenAction() {
        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();

                BaseLogger.log(ELogLevel.INFO, "Opened the OpenAction popup");

                FolderSelectionPopup popup = new FolderSelectionPopup(false, stage, pDirectory -> {
                    BaseLogger.log(ELogLevel.DEBUG, "Selected directory: " + pDirectory.getAbsolutePath());
                    BlueNode.contentBrowser.updateContent(new File(pDirectory.getAbsolutePath()));
                });

                popup.show();

            } catch (Exception pException) {
                BaseLogger.log(ELogLevel.ERROR, "Error while trying to open the OpenAction popup", pException);
            }
        });
    }
}
