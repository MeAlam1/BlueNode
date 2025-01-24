package com.mealam.bluenode.nodes;

import com.mealam.bluenode.interfaces.nodes.NodeComponent;
import com.mealam.bluenode.interfaces.nodes.RenderableNodeComponent;
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
        pGraphicsContext.fillText(pNode.getType(), x + 5, y + 20);

        // Render Components
        double contentStartY = y + titleBarHeight + 5;
        for (NodeComponent component : pNode.getComponents()) {
            if (component instanceof RenderableNodeComponent renderableNodeComponent) {
                renderableNodeComponent.render(pGraphicsContext, x + 10, contentStartY);
                contentStartY += renderableNodeComponent.getHeight();
            }
        }
    }
}
