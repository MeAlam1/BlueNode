package com.mealam.bluenode.nodes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NodeRenderer {

    public static void render(GraphicsContext pGraphicsContext, Node pNode, double pTranslateX, double pTranslateY) {
        double x = pNode.getPX() + pTranslateX;
        double y = pNode.getPY() + pTranslateY;

        double nodeWidth = 150;
        double titleBarHeight = 30;
        double contentAreaHeight = 70;

        Color nodeColor = Color.web(pNode.getColor());

        // Content Area
        double contentAreaY = y + titleBarHeight - 5;
        pGraphicsContext.setFill(Color.GRAY);
        pGraphicsContext.fillRoundRect(x, contentAreaY, nodeWidth, contentAreaHeight, 10, 10);

        // Title Bar
        pGraphicsContext.setFill(nodeColor);
        pGraphicsContext.fillRoundRect(x, y, nodeWidth, titleBarHeight, 10, 10);
        pGraphicsContext.setFill(Color.BLACK);
        pGraphicsContext.fillText(pNode.getType(), x + 5, y + 20);

        renderFormElements(pGraphicsContext, x, contentAreaY);
    }

    private static void renderFormElements(GraphicsContext pGraphicsContext, double pX, double pY) {
        pGraphicsContext.setFill(Color.BLACK);
        pGraphicsContext.fillText("Checkbox", pX + 5, pY + 20);
        pGraphicsContext.fillText("Slider", pX + 5, pY + 40);
        pGraphicsContext.fillText("Input Field", pX + 5, pY + 60);
    }
}
