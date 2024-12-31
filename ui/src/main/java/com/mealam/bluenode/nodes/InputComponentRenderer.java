package com.mealam.bluenode.nodes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class InputComponentRenderer implements RenderableNodeComponent {

    private final InputComponent coreComponent;

    public InputComponentRenderer(InputComponent pCoreComponent) {
        this.coreComponent = pCoreComponent;
    }

    @Override
    public String getLabel() {
        return coreComponent.getLabel();
    }

    @Override
    public double getHeight() {
        return coreComponent.getHeight();
    }

    @Override
    public void render(GraphicsContext pGraphicsContext, double pX, double pY) {
        pGraphicsContext.setFill(Color.WHITE);
        pGraphicsContext.fillRect(pX, pY, 120, 20);
        pGraphicsContext.setFill(Color.BLACK);
        pGraphicsContext.strokeRect(pX, pY, 120, 20);
        pGraphicsContext.fillText(coreComponent.getLabel(), pX + 5, pY + 15);
    }
}
