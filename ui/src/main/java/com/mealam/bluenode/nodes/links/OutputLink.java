package com.mealam.bluenode.nodes.links;

import com.mealam.bluenode.nodes.NodePane;
import javafx.scene.paint.Color;

public class OutputLink extends Link {
    public OutputLink(double x, double y, NodePane parentPane) {
        super(x, y, Color.RED, parentPane);
        this.setOnMousePressed(LinkHandler::startConnection);
        this.setOnMouseDragged(LinkHandler::dragConnection);
        this.setOnMouseReleased(LinkHandler::endConnection);


    }
}
