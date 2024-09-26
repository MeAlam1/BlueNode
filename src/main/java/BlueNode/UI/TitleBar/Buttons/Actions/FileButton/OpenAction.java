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

                BaseLogger.log("Opened the OpenAction popup", ELogLevel.INFO);

                FolderSelectionPopup popup = new FolderSelectionPopup(false, stage, pDirectory -> {
                    BaseLogger.log("Selected directory: " + pDirectory.getAbsolutePath(), ELogLevel.DEBUG);
                    BlueNode.contentBrowser.updateContent(new File(pDirectory.getAbsolutePath()));
                });

                popup.show();

            } catch (Exception pException) {
                BaseLogger.log("Error while trying to open the OpenAction popup", ELogLevel.ERROR, pException);
            }
        });
    }
}
