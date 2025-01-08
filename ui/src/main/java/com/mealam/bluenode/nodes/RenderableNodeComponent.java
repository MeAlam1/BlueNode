package com.mealam.bluenode.nodes;

import javafx.scene.canvas.GraphicsContext;

public interface RenderableNodeComponent extends NodeComponent {

    void render(GraphicsContext pGraphicsContext, double pX, double pY);
}
