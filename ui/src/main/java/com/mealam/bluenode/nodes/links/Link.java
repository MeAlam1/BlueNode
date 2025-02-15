package com.mealam.bluenode.nodes.links;

import com.mealam.bluenode.nodes.NodePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

abstract class Link extends Circle {
    protected NodePane parentPane;

    public Link(double x, double y, Color color, NodePane parentPane) {
        super(x, y, 10, color);
        this.setStroke(Color.BLACK);
        this.parentPane = parentPane;
        parentPane.getChildren().add(this);
    }
}
