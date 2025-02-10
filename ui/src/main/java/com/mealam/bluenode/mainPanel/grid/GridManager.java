package com.mealam.bluenode.mainPanel.grid;

import com.mealam.bluenode.events.mainPanel.CanvasDragHandler;
import javafx.scene.canvas.GraphicsContext;

public class GridManager {

    private final GraphicsContext graphicsContext;
    private final GridDrawer gridDrawer;

    public GridManager(GraphicsContext pGraphicsContext) {
        this.graphicsContext = pGraphicsContext;
        this.gridDrawer = new GridDrawer();
    }

    public void drawGrid() {
        double width = graphicsContext.getCanvas().getWidth();
        double height = graphicsContext.getCanvas().getHeight();

        graphicsContext.save();
        graphicsContext.clearRect(0, 0, width, height);
        gridDrawer.drawGrid(graphicsContext, width, height, CanvasDragHandler.getTranslateX(), CanvasDragHandler.getTranslateY());
        graphicsContext.restore();
    }

    public GridDrawer getGridDrawer() {
        return gridDrawer;
    }
}
