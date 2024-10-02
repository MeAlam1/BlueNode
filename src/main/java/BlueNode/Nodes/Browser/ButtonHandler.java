package BlueNode.Nodes.Browser;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import BlueNode.Nodes.Node;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class ButtonHandler {

    private final Stage parentStage;

    public ButtonHandler(Stage pParentStage) {
        this.parentStage = pParentStage;
    }

    public void handleButtonClick(Class<? extends PopupButtonProvider> pNodeClass, MouseEvent pEvent, Popup pPopup, PopupButtonProvider pInstance) {
        try {
            Node newNodeInstance = (Node) pNodeClass.getDeclaredConstructor().newInstance();
            Pane nodePane = newNodeInstance.getNodePane();

            nodePane.setLayoutX(pEvent.getSceneX());
            nodePane.setLayoutY(pEvent.getSceneY());

            Pane mainPane = (Pane) parentStage.getScene().getRoot();
            mainPane.getChildren().add(nodePane);
            System.out.println(pInstance.getButtonText() + " clicked");
            pPopup.hide();
        } catch (Exception pException) {
            BaseLogger.log(ELogLevel.ERROR, "Failed to create new node instance: " + pException.getMessage(), pException);
        }
    }
}
