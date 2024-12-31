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
                if (!isNodeAtLocation(snappedX, snappedY)) {
                    Node newNode = new MathNode(snappedX, snappedY, NodeIDGenerator.generateID("MathNode"));
                    InputComponent coreInput = new InputComponent("Name");
                    RenderableNodeComponent inputRenderer = new InputComponentRenderer(coreInput);
                    newNode.addComponent(inputRenderer);

                    nodes.add(newNode);

                    for (Node node : nodes) {
                        NodeRenderer.render(graphicsContext, node, CanvasDragHandler.getTranslateX(), CanvasDragHandler.getTranslateY());
                    }

                    BaseLogger.log(BaseLogLevel.SUCCESS, "Node [" + newNode.getId() + "] created at (" + snappedX + ", " + snappedY + ")");
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
            if (node.getX() == pX && node.getY() == pY) {
                return true;
            }
        }
        return false;
    }
}
