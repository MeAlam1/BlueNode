package BlueNode.Nodes.Browser;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NodeBrowser {

    public static void showCustomPopup(Stage pParentStage, MouseEvent pEvent) {
        PopupManager popupManager = new PopupManager(pParentStage);
        popupManager.showPopup(pEvent);
    }
}
