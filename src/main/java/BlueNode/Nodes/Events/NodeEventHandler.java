package BlueNode.Nodes.Events;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import BlueNode.Nodes.Types.AbstractNode;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class NodeEventHandler {
    public static void handleSelect(AbstractNode pNode) {
        if (AbstractNode.getCurrentlySelectedNode() != null && AbstractNode.getCurrentlySelectedNode() != pNode) {
            AbstractNode.getCurrentlySelectedNode().deselect();
        }

        if (AbstractNode.getCurrentlySelectedNode() != pNode) {
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
}
