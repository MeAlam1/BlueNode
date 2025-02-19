package com.mealam.bluenode.nodes.links;

import com.mealam.bluenode.nodes.NodePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

abstract class Link extends Circle {

    protected NodePane parentPane;

    public Link(double pX, double pY, Color pColor, NodePane pParentPane) {
        super(pX, pY, 10, pColor);
        this.setStroke(Color.BLACK);
        this.parentPane = pParentPane;
        parentPane.getChildren().add(this);
    }
}
