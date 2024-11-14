package com.mealam.bluenode.mainPanel;

import com.mealam.bluenode.UIController;
import com.mealam.bluenode.handlers.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

public class MainPanel extends BorderPane {

    private final GraphicsContext graphicsContext;
    private final GridDrawer gridDrawer;
    private final List<Node> nodes;
    private final CanvasDragHandler dragHandler;

    public MainPanel() {
        graphicsContext = UIController.MAIN_CANVAS.getGraphicsContext2D();
        gridDrawer = new GridDrawer();
        nodes = new ArrayList<>();

        UIController.MAIN_CANVAS.widthProperty().bind(widthProperty());
        UIController.MAIN_CANVAS.heightProperty().bind(heightProperty());

        widthProperty().addListener((observable, oldValue, newValue) -> drawGrid());
        heightProperty().addListener((observable, oldValue, newValue) -> drawGrid());

        setCenter(UIController.MAIN_CANVAS);

        dragHandler = new CanvasDragHandler(UIController.MAIN_CANVAS, graphicsContext, gridDrawer, nodes);

        UIController.MAIN_CANVAS.addEventHandler(MouseEvent.MOUSE_PRESSED, pEvent -> {
            if (pEvent.getButton() == MouseButton.SECONDARY) { // Right click
                double mouseX = pEvent.getX();
                double mouseY = pEvent.getY();

                double snappedX = GridDrawer.snapToGrid(mouseX - dragHandler.getTranslateX());
                double snappedY = GridDrawer.snapToGrid(mouseY - dragHandler.getTranslateY());

                Node newNode = new Node(snappedX, snappedY);
                nodes.add(newNode);

                for (Node node : nodes) {
                    node.draw(graphicsContext, dragHandler.getTranslateX(), dragHandler.getTranslateY());
                }

                BaseLogger.log(BaseLogLevel.SUCCESS, "Node created at (" + snappedX + ", " + snappedY + ")");
            }
        });

        BaseLogger.log(BaseLogLevel.SUCCESS, "MainPanel initialized with infinite scrolling and zoom");
    }

    private void drawGrid() {
        double width = UIController.MAIN_CANVAS.getWidth();
        double height = UIController.MAIN_CANVAS.getHeight();

        graphicsContext.save();
        graphicsContext.clearRect(0, 0, width, height);
        graphicsContext.translate(dragHandler.getTranslateX(), dragHandler.getTranslateY());

        gridDrawer.drawGrid(graphicsContext, width, height, dragHandler.getTranslateX(), dragHandler.getTranslateY());

        graphicsContext.restore();
    }
}
