package BlueNode.Nodes;

import javafx.scene.layout.Pane;

public abstract class Node {
    protected Pane nodePane;

    public Node() {
        nodePane = new Pane();
    }

    public abstract Pane getNodePane();
}
