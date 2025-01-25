package com.mealam.bluenode.handlers.mainPanel;

import com.mealam.bluenode.mainPanel.GridDrawer;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.NodeRenderer;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class CanvasDragHandler {

    private final Canvas canvas;
    private final GraphicsContext graphicsContext;
    private final GridDrawer gridDrawer;
    private final Pane overlayPane;
    private static double translateX;
    private static double translateY;
    private final List<Node> nodes;
    private double lastX;
    private double lastY;

    public CanvasDragHandler(Canvas pCanvas, GraphicsContext pGraphicsContext, GridDrawer pGridDrawer, List<Node> pNodes, Pane pOverlayPane) {
        this.canvas = pCanvas;
        this.graphicsContext = pGraphicsContext;
        this.gridDrawer = pGridDrawer;
        this.nodes = pNodes;
        this.overlayPane = pOverlayPane;
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

        overlayPane.getChildren().clear();

        overlayPane.setTranslateX(-translateX);
        overlayPane.setTranslateY(-translateY);

        for (Node node : nodes) {
            NodeRenderer.render(graphicsContext, overlayPane, node, translateX, translateY);
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
