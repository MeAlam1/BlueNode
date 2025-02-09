package com.mealam.bluenode.nodes;

import javafx.scene.layout.Pane;

/*OPTIMIZE:
 * 1. Why does this class exist? it is only a wrapper for NodePane.
 */
public class NodeRenderer {

    public static void render(Pane pPane, Node nodeData) {
        NodePane nodePane = new NodePane(nodeData);
        pPane.getChildren().add(nodePane);
    }
}
