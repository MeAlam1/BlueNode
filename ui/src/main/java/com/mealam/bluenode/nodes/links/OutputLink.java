package com.mealam.bluenode.nodes.links;

import com.mealam.bluenode.nodes.NodePane;
import javafx.scene.paint.Color;

public class OutputLink extends Link {

    public OutputLink(double pX, double pY, NodePane pParentPane) {
        super(pX, pY, Color.RED, pParentPane);
        this.setOnMousePressed(LinkHandler::startConnection);
        this.setOnMouseDragged(LinkHandler::dragConnection);
        this.setOnMouseReleased(LinkHandler::endConnection);
    }
}
