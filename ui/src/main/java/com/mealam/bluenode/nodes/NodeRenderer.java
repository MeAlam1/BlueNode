package com.mealam.bluenode.nodes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NodeRenderer {

    public static void render(GraphicsContext pGraphicsContext, Node pNode, double pTranslateX, double pTranslateY) {
        double x = pNode.getX() + pTranslateX;
        double y = pNode.getY() + pTranslateY;

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

        // Render Components
        double contentStartY = y + titleBarHeight + 5;
        for (NodeComponent component : pNode.getComponents()) {
            if (component instanceof RenderableNodeComponent renderableComponent) {
                renderableComponent.render(pGraphicsContext, x + 10, contentStartY);
                contentStartY += renderableComponent.getHeight();
            }
        }
    }

}
