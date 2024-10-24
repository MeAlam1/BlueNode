package BlueNode.Nodes.Events;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import BlueNode.Nodes.Types.AbstractNode;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class NodeEventHandler {

    public static void handleSelect(AbstractNode pNode) {
        AbstractNode currentlySelectedNode = AbstractNode.getCurrentlySelectedNode();

        if (currentlySelectedNode == pNode) {
            currentlySelectedNode.deselect();
            AbstractNode.setCurrentlySelectedNode(null);
        } else {
            if (currentlySelectedNode != null) {
                currentlySelectedNode.deselect();
            }
            pNode.select();
            AbstractNode.setCurrentlySelectedNode(pNode);
        }
    }


    public static void handleKeyPress(AbstractNode pNode, KeyCode pCode) {
        if (pNode.isSelected() && (pCode == KeyCode.DELETE || pCode == KeyCode.BACK_SPACE)) {
            Pane parentPane = (Pane) pNode.getNodePane().getParent();
            if (parentPane != null) {
                parentPane.getChildren().remove(pNode.getNodePane());
                BaseLogger.log(ELogLevel.INFO,"Node removed from parent pane.");
            }
        }
    }

    public static void addGlobalClickListener(Pane pParentPane) {
        pParentPane.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            AbstractNode selectedNode = AbstractNode.getCurrentlySelectedNode();

            if (selectedNode != null && !selectedNode.getNodePane().getBoundsInParent().contains(event.getX(), event.getY())) {
                selectedNode.deselect();
                AbstractNode.setCurrentlySelectedNode(null);
            }
        });
    }
}
