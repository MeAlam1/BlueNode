package com.mealam.bluenode.nodes;

import javafx.scene.layout.Pane;

public class NodeRenderer {

    public static void render(Pane pPane, Node nodeData) {
        NodePane nodePane = new NodePane(nodeData);
        pPane.getChildren().add(nodePane);
    }
}
