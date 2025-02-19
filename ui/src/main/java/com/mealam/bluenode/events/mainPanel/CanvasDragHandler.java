package com.mealam.bluenode.events.mainPanel;

import com.mealam.bluenode.mainPanel.grid.GridDrawer;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.NodeRenderer;
import com.mealam.bluenode.nodes.links.Connection;
import com.mealam.bluenode.nodes.links.LinkHandler;
import com.mealam.bluenode.utils.io.NodeUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.List;

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
            double newX = node.getProperties().getX() + deltaX;
            double newY = node.getProperties().getY() + deltaY;

            node.getProperties().setX(newX);
            node.getProperties().setY(newY);
        }


        redraw();

        lastX = pEvent.getX();
        lastY = pEvent.getY();
    }

    public void redraw() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gridDrawer.redraw(graphicsContext, canvas.getWidth(), canvas.getHeight(), -translateX, -translateY);

        overlayPane.getChildren().clear();

        for (Node node : nodes) {
            NodeRenderer.render(node, overlayPane);
            NodeUtils.updateNode("test", node.getProperties().getId(), node.toJson());
        }

        for (Connection conn : LinkHandler.getConnectionObjects()) {
            Line line = getLine(conn);
            overlayPane.getChildren().add(line);
        }
    }

    private static Line getLine(Connection conn) {
        Line line = conn.line();
        Node source = conn.sourceNode();

        double adjustedSourceX = source.getProperties().getX() + conn.sourceOffsetX() - translateX;
        double adjustedSourceY = source.getProperties().getY() + conn.sourceOffsetY() - translateY;
        double adjustedTargetX = source.getProperties().getX() + conn.targetOffsetX() - translateX;
        double adjustedTargetY = source.getProperties().getY() + conn.targetOffsetY() - translateY;

        line.setStartX(adjustedSourceX);
        line.setStartY(adjustedSourceY);
        line.setEndX(adjustedTargetX);
        line.setEndY(adjustedTargetY);

        return line;
    }


    public static double getTranslateX() {
        return translateX;
    }

    public static double getTranslateY() {
        return translateY;
    }

    public static void setTranslateX(double pTranslateX) {
        translateX = pTranslateX;
    }

    public static void setTranslateY(double pTranslateY) {
        translateY = pTranslateY;
    }
}
