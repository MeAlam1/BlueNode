package BlueNode.Nodes.Events;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import BlueNode.Nodes.Types.AbstractNode;
import BlueNode.UI.MainPanel.GridDrawer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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


    public static void handleKeyPress(KeyCode pCode, AbstractNode pNode) {
        if (pNode.isSelected() && (pCode == KeyCode.DELETE || pCode == KeyCode.BACK_SPACE)) {
            Pane parentPane = (Pane) pNode.getNodePane().getParent();
            if (parentPane != null) {
                parentPane.getChildren().remove(pNode.getNodePane());
                BaseLogger.log(ELogLevel.SUCCESS, "Node removed from parent pane.");
            }
        }
    }

    public static void handleMouseRelease(MouseEvent pEvent, VBox pNodePane) {
        double mouseX = pEvent.getSceneX();
        double mouseY = pEvent.getSceneY();

        double nodeX = pNodePane.getLayoutX();
        double nodeY = pNodePane.getLayoutY();
        double nodeWidth = pNodePane.getWidth();
        double nodeHeight = pNodePane.getHeight();

        double snappedX;
        double snappedY;

        boolean isCloserToLeft = mouseX < (nodeX + nodeWidth / 2);
        boolean isCloserToTop = mouseY < (nodeY + nodeHeight / 2);

        if (isCloserToLeft) {
            snappedX = GridDrawer.snapToGrid(nodeX);
        } else {
            snappedX = GridDrawer.snapToGrid(nodeX + nodeWidth) - nodeWidth;
        }

        if (isCloserToTop) {
            snappedY = GridDrawer.snapToGrid(nodeY);
        } else {
            snappedY = GridDrawer.snapToGrid(nodeY + nodeHeight) - nodeHeight;
        }

        pNodePane.setLayoutX(snappedX);
        pNodePane.setLayoutY(snappedY);
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
