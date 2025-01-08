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
            if (pEvent.getButton() == MouseButton.SECONDARY) { // Right-click
                double mouseX = pEvent.getX();
                double mouseY = pEvent.getY();

                double snappedX = GridDrawer.snapToGrid(mouseX - CanvasDragHandler.getTranslateX());
                double snappedY = GridDrawer.snapToGrid(mouseY - CanvasDragHandler.getTranslateY());

                double newX = snappedX;
                double newY = snappedY;
                double nodeWidth = 150;
                double nodeHeight = 100;

                while (isNodeAtLocation(newX, newY, nodeWidth, nodeHeight)) {
                    if (!isNodeAtLocation(newX - 10, newY, nodeWidth, nodeHeight)) {
                        newX -= 10;
                    } else if (!isNodeAtLocation(newX, newY - 10, nodeWidth, nodeHeight)) {
                        newY -= 10;
                    } else {
                        break;
                    }
                }

                if (!isNodeAtLocation(newX, newY, nodeWidth, nodeHeight)) {
                    Node newNode = new MathNode(nodeWidth, nodeHeight, newX, newY, NodeIDGenerator.generateID("MathNode"));
                    nodes.add(newNode);

                    for (Node node : nodes) {
                        NodeRenderer.render(graphicsContext, node, CanvasDragHandler.getTranslateX(), CanvasDragHandler.getTranslateY());
                    }

                    BaseLogger.log(BaseLogLevel.SUCCESS, "Node [" + newNode.getId() + "] created at (" + newX + ", " + newY + ")");
                } else {
                    BaseLogger.log(BaseLogLevel.ERROR, "No suitable position found for new node.");
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

    private boolean isNodeAtLocation(double pX, double pY, double width, double height) {
        for (Node node : nodes) {
            double nodeX = node.getX();
            double nodeY = node.getY();
            double nodeWidth = node.getWidth();
            double nodeHeight = node.getHeight();

            if (pX < nodeX + nodeWidth && pX + width > nodeX && pY < nodeY + nodeHeight && pY + height > nodeY) {
                return true;
            }
        }
        return false;
    }
}
