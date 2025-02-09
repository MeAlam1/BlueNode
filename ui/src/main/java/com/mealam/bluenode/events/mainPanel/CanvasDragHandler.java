package com.mealam.bluenode.events.mainPanel;

import com.mealam.bluenode.mainPanel.GridDrawer;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.NodeRenderer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.List;

public class CanvasDragHandler {

    private static Canvas canvas;
    private static GraphicsContext graphicsContext;
    private static GridDrawer gridDrawer;
    private static Pane overlayPane;
    private static double translateX;
    private static double translateY;
    private static List<Node> nodes;
    private double lastX;
    private double lastY;

    public CanvasDragHandler(Canvas pCanvas, GraphicsContext pGraphicsContext, GridDrawer pGridDrawer, List<Node> pNodes, Pane pOverlayPane) {
        canvas = pCanvas;
        graphicsContext = pGraphicsContext;
        gridDrawer = pGridDrawer;
        nodes = pNodes;
        overlayPane = pOverlayPane;
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

        for (Node node : nodes) {
            node.getProperties().setX(node.getProperties().getX() + deltaX);
            node.getProperties().setY(node.getProperties().getY() + deltaY);
        }

        redraw();

        lastX = pEvent.getX();
        lastY = pEvent.getY();
    }

    public static void redraw() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gridDrawer.redraw(graphicsContext, canvas.getWidth(), canvas.getHeight(), -translateX, -translateY);

        overlayPane.getChildren().clear();

        for (Node node : nodes) {
            NodeRenderer.render(overlayPane, node);
            //BaseLogger.log(BaseLogLevel.INFO, "Node: " + node.toString());
        }
    }

    public static double getTranslateX() {
        return translateX;
    }

    public static double getTranslateY() {
        return translateY;
    }
}