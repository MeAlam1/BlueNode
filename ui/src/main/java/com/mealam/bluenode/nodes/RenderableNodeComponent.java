package com.mealam.bluenode.nodes;

import com.mealam.bluenode.interfaces.nodes.NodeComponent;
import javafx.scene.canvas.GraphicsContext;

public interface RenderableNodeComponent extends NodeComponent {

    void render(GraphicsContext pGraphicsContext, double pX, double pY);
}
