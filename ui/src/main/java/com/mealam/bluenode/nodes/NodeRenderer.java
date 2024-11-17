package com.mealam.bluenode.nodes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NodeRenderer {

    public static void render(GraphicsContext pGraphicsContext, Node pNode, double pTranslateX, double pTranslateY) {
        double x = pNode.getPX() + pTranslateX;
        double y = pNode.getPY() + pTranslateY;

        Color color = Color.web(pNode.getColor());
        pGraphicsContext.setFill(color);

        pGraphicsContext.fillRect(x, y, 50, 50);

        pGraphicsContext.setFill(Color.BLACK);
        pGraphicsContext.fillText(pNode.getType(), x + 5, y + 15);
    }
}
