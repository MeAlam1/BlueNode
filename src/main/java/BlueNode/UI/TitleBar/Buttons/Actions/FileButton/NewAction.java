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
                BaseLogger.log("Error while trying to open the NewAction popup", ELogLevel.ERROR, pException);
            }
        });
    }

    private static void showFolderSelectionPopup(Stage pStage) {
        FolderSelectionPopup popup = new FolderSelectionPopup(false, pStage, pDirectory -> {
            BaseLogger.log("Selected directory: " + pDirectory.getAbsolutePath(), ELogLevel.DEBUG);
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
                BaseLogger.log("Folder already exists.", ELogLevel.WARN);
            } else if (newFolder.mkdir()) {
                BaseLogger.log("Folder created: " + newFolder.getAbsolutePath(), ELogLevel.INFO);
                BlueNode.contentBrowser.updateContent(newFolder);
            } else {
                BaseLogger.log("Failed to create folder.", ELogLevel.ERROR);
            }
        });
    }
}
