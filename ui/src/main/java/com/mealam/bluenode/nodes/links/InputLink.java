package com.mealam.bluenode.nodes.links;

import com.mealam.bluenode.nodes.NodePane;
import javafx.scene.paint.Color;

public class InputLink extends Link {
    public InputLink(double x, double y, NodePane parentPane) {
        super(x, y, Color.BLUE, parentPane);
        this.setOnDragDropped(LinkHandler::endConnection);
    }
}
