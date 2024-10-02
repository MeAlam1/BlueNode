package BlueNode.Nodes.Browser;

import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class PopupManager {

    private final Stage parentStage;
    private final ContentCreator popupContentCreator;

    public PopupManager(Stage pParentStage) {
        this.parentStage = pParentStage;
        this.popupContentCreator = new ContentCreator(parentStage);
    }

    public void showPopup(MouseEvent pEvent) {
        Popup popup = new Popup();
        popup.getContent().add(popupContentCreator.createContent(pEvent, popup));
        popup.setAutoHide(true);
        popup.show(parentStage, pEvent.getScreenX(), pEvent.getScreenY());
    }
}
