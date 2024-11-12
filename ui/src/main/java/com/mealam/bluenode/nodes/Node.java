package com.mealam.bluenode.nodes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Node {
    private final double x;
    private final double y;

    public Node(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void draw(GraphicsContext gc, double translateX, double translateY) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x + translateX, y + translateY, 50, 50);
    }
}
