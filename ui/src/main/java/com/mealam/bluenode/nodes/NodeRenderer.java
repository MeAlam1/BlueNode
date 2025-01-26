package com.mealam.bluenode.nodes;

import com.mealam.bluenode.nodes.components.input.Input;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class NodeRenderer {

    public static void render(GraphicsContext pGraphicsContext, Pane pPane, Node pNode, double pTranslateX, double pTranslateY) {
        double x = pNode.getProperties().getX() + pTranslateX;
        double y = pNode.getProperties().getY() + pTranslateY;
        double width = pNode.getProperties().getWidth();
        double height = pNode.getProperties().getHeight();
        double titleBarHeight = height - 70;
        double contentAreaHeight = height - titleBarHeight;

        Color nodeColor = Color.web(pNode.getProperties().getColor());

        // Content Area
        double contentAreaY = y + titleBarHeight - 5;
        pGraphicsContext.setFill(Color.GRAY);
        pGraphicsContext.fillRoundRect(x, contentAreaY, width, contentAreaHeight, 10, 10);

        // Title Bar
        pGraphicsContext.setFill(nodeColor);
        pGraphicsContext.fillRoundRect(x, y, width, titleBarHeight, 10, 10);
        pGraphicsContext.setFill(Color.BLACK);
        pGraphicsContext.fillText(pNode.getProperties().getTitle(), x + 5, y + 20);

        for (Input input : pNode.getProperties().getInputs()) {
            InputRenderer.render(pGraphicsContext, pPane, input, pNode, x + 5, y + (height / 2), width, height);
        }
    }
}
