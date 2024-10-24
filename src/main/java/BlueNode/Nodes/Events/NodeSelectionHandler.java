package BlueNode.Nodes.Events;

import BlueNode.Nodes.Types.AbstractNode;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class NodeSelectionHandler {

    public static void selectNode(AbstractNode pNode) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(5.0);
        dropShadow.setOffsetY(5.0);
        dropShadow.setColor(Color.DARKBLUE);
        pNode.getNodePane().setEffect(dropShadow);
        pNode.getNodePane().setOpacity(0.9);
        pNode.getNodePane().requestFocus();
    }

    public static void deselectNode(AbstractNode pNode) {
        pNode.getNodePane().setEffect(null);
        pNode.getNodePane().setOpacity(1.0);
    }
}
