package com.mealam.bluenode.nodes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NodeRenderer {

    public static void render(GraphicsContext pGraphicsContext, Node pNode, double pTranslateX, double pTranslateY) {
        double x = pNode.getX() + pTranslateX;
        double y = pNode.getY() + pTranslateY;
        double width = pNode.getWidth();
        double titleBarHeight = pNode.getHeight() - 70;
        double contentAreaHeight = pNode.getHeight() - titleBarHeight;

        Color nodeColor = Color.web(pNode.getColor());

        // Content Area
        double contentAreaY = y + titleBarHeight - 5;
        pGraphicsContext.setFill(Color.GRAY);
        pGraphicsContext.fillRoundRect(x, contentAreaY, width, contentAreaHeight, 10, 10);

        // Title Bar
        pGraphicsContext.setFill(nodeColor);
        pGraphicsContext.fillRoundRect(x, y, width, titleBarHeight, 10, 10);
        pGraphicsContext.setFill(Color.BLACK);
        pGraphicsContext.fillText(pNode.getTitle(), x + 5, y + 20);
    }
}
