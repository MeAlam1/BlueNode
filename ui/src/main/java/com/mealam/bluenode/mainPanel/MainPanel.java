package com.mealam.bluenode.mainPanel;

import com.mealam.bluenode.UIController;
import com.mealam.bluenode.handlers.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.NodeRenderer;
import com.mealam.bluenode.nodes.categories.MathNode;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import com.mealam.bluenode.utils.nodes.NodeIDGenerator;
import com.mealam.bluenode.utils.nodes.NodeLoaderUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

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

                double nodeWidth = 150;
                double nodeHeight = 100;

                double centerX = mouseX - (nodeWidth / 2);
                double centerY = mouseY - (nodeHeight / 2);

                double snappedX = GridDrawer.snapToGrid(centerX - CanvasDragHandler.getTranslateX());
                double snappedY = GridDrawer.snapToGrid(centerY - CanvasDragHandler.getTranslateY());

                //NOTE: This is where the node is Drawn!!!!
                Node newNode = new MathNode(snappedX, snappedY);
                Node getNode = getNewNode(newNode);
                placeNode(getNode);
            }
        });

        BaseLogger.log(BaseLogLevel.SUCCESS, "MainPanel initialized with infinite scrolling and zoom");
    }

    private Node getNewNode(Node newNode) {
        Node getNode = NodeLoaderUtils.getNodeByKey("testNode", newNode.getX(), newNode.getY());
        if (getNode == null) {
            throw new NullPointerException("Node not found in Library");
        }
        getNode.setId(NodeIDGenerator.generateID(getNode.getTitle()));
        getNode.setWidth(150);
        getNode.setHeight(100);
        return getNode;
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

    public boolean isNodeAtLocation(double pX, double pY, double width, double height) {
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

    public void placeNode(Node pNode) {
        if (!isNodeAtLocation(pNode.getX(), pNode.getY(), pNode.getWidth(), pNode.getHeight())) {
            nodes.add(pNode);

            for (Node node : nodes) {
                NodeRenderer.render(graphicsContext, node, CanvasDragHandler.getTranslateX(), CanvasDragHandler.getTranslateY());
            }

            BaseLogger.log(BaseLogLevel.SUCCESS, "Node [" + pNode.getId() + "] created at (" + pNode.getX() + ", " + pNode.getY() + ")");
            BaseLogger.log(BaseLogLevel.INFO, "Node Decrypted ID: " + NodeIDGenerator.decryptID(pNode.getId()));
        } else {
            BaseLogger.log(BaseLogLevel.WARNING, "No suitable position found for new node.");
        }
    }

    public static boolean isOverlapping(Node pNode1, Node pNode2) {
        boolean overlapping = pNode1.getX() < pNode2.getX() + pNode2.getWidth() &&
                pNode1.getX() + pNode1.getWidth() > pNode2.getX() &&
                pNode1.getY() < pNode2.getY() + pNode2.getHeight() &&
                pNode1.getY() + pNode1.getHeight() > pNode2.getY();

        if (overlapping) {
            BaseLogger.log(BaseLogLevel.WARNING, "Nodes are overlapping with positions: \n" +
                    "(Node1)(X: " + pNode1.getX() + ", Y: " + pNode1.getY() + ") \n" +
                    "(Node2)(X: " + pNode2.getX() + ", Y: " + pNode2.getY() + ")");
        }
        return overlapping;
    }
}
