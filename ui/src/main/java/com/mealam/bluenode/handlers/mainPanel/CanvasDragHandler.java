package com.mealam.bluenode.handlers.mainPanel;

import com.mealam.bluenode.mainPanel.MainPanel;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.mainPanel.GridDrawer;
import com.mealam.bluenode.nodes.NodeRenderer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class CanvasDragHandler {

    private final Canvas canvas;
    private final GraphicsContext graphicsContext;
    private final GridDrawer gridDrawer;
    private static double translateX;
    private static double translateY;
    private double lastX;
    private double lastY;

    public CanvasDragHandler(Canvas pCanvas, GraphicsContext pGraphicsContext, GridDrawer pGridDrawer) {
        this.canvas = pCanvas;
        this.graphicsContext = pGraphicsContext;
        this.gridDrawer = pGridDrawer;
        initDragHandler();
    }

    private void initDragHandler() {
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this::onMousePressed);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::onMouseDragged);
    }

    private void onMousePressed(MouseEvent pEvent) {
        lastX = pEvent.getX();
        lastY = pEvent.getY();
    }

    private void onMouseDragged(MouseEvent pEvent) {
        double deltaX = pEvent.getX() - lastX;
        double deltaY = pEvent.getY() - lastY;

        translateX += deltaX;
        translateY += deltaY;

        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gridDrawer.redraw(graphicsContext, canvas.getWidth(), canvas.getHeight(), -translateX, -translateY);

        for (Node node : MainPanel.nodes) {
            NodeRenderer.render(graphicsContext, node, translateX, translateY);
        }

        lastX = pEvent.getX();
        lastY = pEvent.getY();
    }

    public static double getTranslateX() {
        return translateX;
    }

    public static double getTranslateY() {
        return translateY;
    }
}
