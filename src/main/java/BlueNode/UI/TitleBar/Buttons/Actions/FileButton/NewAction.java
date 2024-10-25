package BlueNode.UI.TitleBar.Buttons.Actions.FileButton;

import BlueNode.Logging.BaseLogger;
import BlueNode.BlueNode;
import BlueNode.Logging.ELogLevel;
import BlueNode.UI.Components.FolderSelectionPopup;
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
                BaseLogger.log(ELogLevel.ERROR, "Error while trying to open the NewAction popup", pException);
            }
        });
    }

    private static void showFolderSelectionPopup(Stage pStage) {
        FolderSelectionPopup popup = new FolderSelectionPopup(false, pStage, pDirectory -> {
            BaseLogger.log(ELogLevel.INFO, "Selected directory: " + pDirectory.getAbsolutePath());
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
                BaseLogger.log(ELogLevel.WARN, "Folder already exists.");
            } else if (newFolder.mkdir()) {
                BaseLogger.log(ELogLevel.SUCCESS, "Folder created: " + newFolder.getAbsolutePath());
                BlueNode.contentBrowser.updateContent(newFolder);
            } else {
                BaseLogger.log(ELogLevel.ERROR, "Failed to create folder.");
            }
        });
    }
}
