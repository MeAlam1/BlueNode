package com.mealam.bluenode.mainPanel;

import com.mealam.bluenode.UIConstants;
import com.mealam.bluenode.UIController;
import com.mealam.bluenode.handlers.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.NodeRenderer;
import com.mealam.bluenode.nodes.components.input.Input;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import com.mealam.bluenode.utils.nodes.NodeIDGenerator;
import com.mealam.bluenode.utils.nodes.NodeLoaderUtils;
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
        graphicsContext = UIConstants.MAIN_CANVAS.getGraphicsContext2D();
        gridDrawer = new GridDrawer();
        nodes = new ArrayList<>();

        UIConstants.MAIN_CANVAS.widthProperty().bind(widthProperty());
        UIConstants.MAIN_CANVAS.heightProperty().bind(heightProperty());

        widthProperty().addListener((observable, oldValue, newValue) -> drawGrid());
        heightProperty().addListener((observable, oldValue, newValue) -> drawGrid());

        setCenter(UIConstants.MAIN_CANVAS);

        new CanvasDragHandler(UIConstants.MAIN_CANVAS, graphicsContext, gridDrawer, nodes);

        UIConstants.MAIN_CANVAS.addEventHandler(MouseEvent.MOUSE_PRESSED, pEvent -> {
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
                Node newNode = new Node(snappedX, snappedY);
                Node getNode = getNewNode(newNode);
                placeNode(getNode);
            }
        });

        BaseLogger.log(BaseLogLevel.SUCCESS, "MainPanel initialized with infinite scrolling and zoom");
    }

    private Node getNewNode(Node newNode) {
        Node getNode = NodeLoaderUtils.getNodeByKey("testNode", newNode.getProperties().getX(), newNode.getProperties().getY());
        if (getNode == null) {
            throw new NullPointerException("Node not found in Library");
        }
        getNode.getProperties().setId(NodeIDGenerator.generateID(getNode.getProperties().getTitle()));
        getNode.getProperties().setWidth(150);
        getNode.getProperties().setHeight(100);
        return getNode;
    }

    private void drawGrid() {
        double width = UIConstants.MAIN_CANVAS.getWidth();
        double height = UIConstants.MAIN_CANVAS.getHeight();

        graphicsContext.save();
        graphicsContext.clearRect(0, 0, width, height);
        graphicsContext.translate(CanvasDragHandler.getTranslateX(), CanvasDragHandler.getTranslateY());

        gridDrawer.drawGrid(graphicsContext, width, height, CanvasDragHandler.getTranslateX(), CanvasDragHandler.getTranslateY());

        graphicsContext.restore();
    }

    public boolean isNodeAtLocation(double pX, double pY, double width, double height) {
        for (Node node : nodes) {
            double nodeX = node.getProperties().getX();
            double nodeY = node.getProperties().getY();
            double nodeWidth = node.getProperties().getWidth();
            double nodeHeight = node.getProperties().getHeight();

            if (pX < nodeX + nodeWidth && pX + width > nodeX && pY < nodeY + nodeHeight && pY + height > nodeY) {
                return true;
            }
        }
        return false;
    }

    public void placeNode(Node pNode) {
        if (!isNodeAtLocation(pNode.getProperties().getX(), pNode.getProperties().getY(), pNode.getProperties().getWidth(), pNode.getProperties().getHeight())) {
            nodes.add(pNode);
            BaseLogger.log(BaseLogLevel.INFO, pNode.getProperties().toString());

            for (Node node : nodes) {
                NodeRenderer.render(graphicsContext, node, CanvasDragHandler.getTranslateX(), CanvasDragHandler.getTranslateY());
            }

            BaseLogger.log(BaseLogLevel.INFO, "Node Decrypted ID: " + NodeIDGenerator.decryptID(pNode.getProperties().getId()));
        } else {
            BaseLogger.log(BaseLogLevel.WARNING, "No suitable position found for new node.");
        }
    }
}
