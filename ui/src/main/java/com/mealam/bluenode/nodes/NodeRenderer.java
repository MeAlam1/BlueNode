package com.mealam.bluenode.nodes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NodeRenderer {

    public static void render(GraphicsContext pGraphicsContext, Node pNode, double pTranslateX, double pTranslateY) {
        pGraphicsContext.setFill(Color.BLUE);
        pGraphicsContext.fillRect(pNode.pX() + pTranslateX, pNode.pY() + pTranslateY, 50, 50);
    }
}
