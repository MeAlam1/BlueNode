package com.mealam.bluenode.mainPanel;

import com.mealam.bluenode.UIController;
import com.mealam.bluenode.handlers.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.nodes.*;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import com.mealam.bluenode.utils.nodes.NodeIDGenerator;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class MainPanel extends BorderPane {

    private final GraphicsContext graphicsContext;
    private final GridDrawer gridDrawer;
    public static List<Node> nodes;

    public MainPanel() {
        graphicsContext = UIController.MAIN_CANVAS.getGraphicsContext2D();
        gridDrawer = new GridDrawer();
        nodes = new ArrayList<>();

        UIController.MAIN_CANVAS.widthProperty().bind(widthProperty());
        UIController.MAIN_CANVAS.heightProperty().bind(heightProperty());

        widthProperty().addListener((observable, oldValue, newValue) -> drawGrid());
        heightProperty().addListener((observable, oldValue, newValue) -> drawGrid());

        setCenter(UIController.MAIN_CANVAS);

        new CanvasDragHandler(UIController.MAIN_CANVAS, graphicsContext, gridDrawer, nodes);

        UIController.MAIN_CANVAS.addEventHandler(MouseEvent.MOUSE_PRESSED, pEvent -> {
            if (pEvent.getButton() == MouseButton.SECONDARY) { // Right click
                double mouseX = pEvent.getX();
                double mouseY = pEvent.getY();

                double snappedX = GridDrawer.snapToGrid(mouseX - CanvasDragHandler.getTranslateX());
                double snappedY = GridDrawer.snapToGrid(mouseY - CanvasDragHandler.getTranslateY());

                double newX = snappedX;
                double newY = snappedY;

                if (isNodeNearby(snappedX, snappedY, 200)) {
                    if (!isNodeNearby(snappedX, snappedY - 100, -200)) {
                        newY = snappedY - 100; // Move up
                    }

                    if (!isNodeNearby(snappedX - 150, snappedY, 200)) {
                        newX = snappedX - 150; // Move left
                    }
                }

                if (!isNodeAtLocation(newX, newY)) {
                    Node newNode = new MathNode(150, 100, newX, newY, NodeIDGenerator.generateID("MathNode"));

                    nodes.add(newNode);

                    for (Node node : nodes) {
                        NodeRenderer.render(graphicsContext, node, CanvasDragHandler.getTranslateX(), CanvasDragHandler.getTranslateY());
                    }

                    BaseLogger.log(BaseLogLevel.SUCCESS, "Node [" + newNode.getId() + "] created at (" + newX + ", " + newY + ")");
                }
            }
        });


        BaseLogger.log(BaseLogLevel.SUCCESS, "MainPanel initialized with infinite scrolling and zoom");
    }

    private void drawGrid() {
        double width = UIController.MAIN_CANVAS.getWidth();
        double height = UIController.MAIN_CANVAS.getHeight();

        graphicsContext.save();
        graphicsContext.clearRect(0, 0, width, height);
        graphicsContext.translate(CanvasDragHandler.getTranslateX(), CanvasDragHandler.getTranslateY());

        gridDrawer.drawGrid(graphicsContext, width, height, CanvasDragHandler.getTranslateX(), CanvasDragHandler.getTranslateY());

        graphicsContext.restore();
    }

    private boolean isNodeAtLocation(double pX, double pY) {
        for (Node node : nodes) {
            double nodeX = node.getX();
            double nodeY = node.getY();
            double nodeWidth = node.getWidth();
            double nodeHeight = node.getHeight();

            if (pX >= nodeX && pX <= nodeX + nodeWidth && pY >= nodeY && pY <= nodeY + nodeHeight) {
                return true;
            }
        }
        return false;
    }

    private boolean isNodeNearby(double pX, double pY, double threshold) {
        for (Node node : nodes) {
            double nodeX = node.getX();
            double nodeY = node.getY();
            double nodeWidth = node.getWidth();
            double nodeHeight = node.getHeight();

            double deltaX = Math.abs(pX - (nodeX + nodeWidth / 2));
            double deltaY = Math.abs(pY - (nodeY + nodeHeight / 2));

            if (deltaX <= threshold && deltaY <= threshold) {
                return true;
            }
        }
        return false;
    }
}
