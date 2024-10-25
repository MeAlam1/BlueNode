package BlueNode.UI.TitleBar.Buttons;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import BlueNode.UI.Components.TextButton;
import BlueNode.UI.TitleBar.Buttons.Actions.FileButton.NewAction;
import BlueNode.UI.TitleBar.Buttons.Actions.FileButton.OpenAction;
import BlueNode.UI.TitleBar.Buttons.Actions.FileButton.SaveAction;
import BlueNode.UI.TitleBar.Buttons.Actions.FileButton.SaveAsAction;
import javafx.geometry.Insets;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class FileButton extends TextButton {

    public FileButton(Stage pStage) {
        super("File", TextButton.NORMAL_COLOR, TextButton.HOVER_COLOR, 45, 30);
        setPadding(new Insets(5, 10, 5, 10));

        ContextMenu contextMenu = createContextMenu();

        setOnAction(event -> {
            double x = localToScreen(getBoundsInLocal()).getMinX();
            double y = localToScreen(getBoundsInLocal()).getMaxY();
            contextMenu.show(FileButton.this, x, y);
        });

        BaseLogger.log(ELogLevel.SUCCESS, "FileButton created");
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

        BaseLogger.log(ELogLevel.INFO, "FileButton context menu created");

        return contextMenu;
    }
}
