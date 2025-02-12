package com.mealam.bluenode.nodes;

import javafx.scene.layout.Pane;

public class NodeRenderer {

    public static void render(Node pNode, Pane pPane) {
        NodePane nodePane = new NodePane(pNode);
        pPane.getChildren().add(nodePane);
    }
}
