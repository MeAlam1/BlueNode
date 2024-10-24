package BlueNode.Nodes.Events;

import BlueNode.Nodes.Types.AbstractNode;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class NodeEventHandler {
    public static void handleKeyPress(AbstractNode node, KeyCode code) {
        if (node.isSelected() && (code == KeyCode.DELETE || code == KeyCode.BACK_SPACE)) {
            Pane parentPane = (Pane) node.getNodePane().getParent();
            if (parentPane != null) {
                parentPane.getChildren().remove(node.getNodePane());
                // Perform any additional cleanup or logging
            }
        }
    }
}
