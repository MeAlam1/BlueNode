package com.mealam.bluenode.mainPanel;

import com.mealam.bluenode.UIController;
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
    private double translateX = 0;
    private double translateY = 0;
    private double lastX;
    private double lastY;

    private final List<Node> nodes;

    public MainPanel() {
        graphicsContext = UIController.MAIN_CANVAS.getGraphicsContext2D();
        gridDrawer = new GridDrawer();
        nodes = new ArrayList<>();

        UIController.MAIN_CANVAS.widthProperty().bind(widthProperty());
        UIController.MAIN_CANVAS.heightProperty().bind(heightProperty());

        widthProperty().addListener((observable, oldValue, newValue) -> drawGrid());
        heightProperty().addListener((observable, oldValue, newValue) -> drawGrid());

        setCenter(UIController.MAIN_CANVAS);

        // MousePressed event handler to start dragging
        UIController.MAIN_CANVAS.addEventHandler(MouseEvent.MOUSE_PRESSED, pEvent -> {
            lastX = pEvent.getX();
            lastY = pEvent.getY();
        });

        // MouseDragged event handler to drag the canvas and snap to grid
        UIController.MAIN_CANVAS.addEventHandler(MouseEvent.MOUSE_DRAGGED, pEvent -> {
            double deltaX = pEvent.getX() - lastX;
            double deltaY = pEvent.getY() - lastY;

            // Update the translation directly without snapping the delta values
            translateX += deltaX;
            translateY += deltaY;

            // Redraw the grid and nodes at the new translated position
            graphicsContext.clearRect(0, 0, UIController.MAIN_CANVAS.getWidth(), UIController.MAIN_CANVAS.getHeight());
            gridDrawer.redraw(graphicsContext, UIController.MAIN_CANVAS.getWidth(), UIController.MAIN_CANVAS.getHeight(), translateX, translateY);

            for (Node node : nodes) {
                node.draw(graphicsContext, translateX, translateY);
            }

            // Update last position for next drag
            lastX = pEvent.getX();
            lastY = pEvent.getY();
        });

        // Right-click to create a new node
        UIController.MAIN_CANVAS.addEventHandler(MouseEvent.MOUSE_PRESSED, pEvent -> {
            if (pEvent.getButton() == MouseButton.SECONDARY) { // Right click
                double mouseX = pEvent.getX();
                double mouseY = pEvent.getY();

                // Snap the mouse position to the grid for node creation
                double snappedX = GridDrawer.snapToGrid(mouseX - translateX);
                double snappedY = GridDrawer.snapToGrid(mouseY - translateY);

                Node newNode = new Node(snappedX, snappedY);
                nodes.add(newNode);

                graphicsContext.clearRect(0, 0, UIController.MAIN_CANVAS.getWidth(), UIController.MAIN_CANVAS.getHeight());
                gridDrawer.redraw(graphicsContext, UIController.MAIN_CANVAS.getWidth(), UIController.MAIN_CANVAS.getHeight(), translateX, translateY);
                for (Node node : nodes) {
                    node.draw(graphicsContext, translateX, translateY);
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
        graphicsContext.translate(translateX, translateY);

        gridDrawer.drawGrid(graphicsContext, width, height, translateX, translateY);

        graphicsContext.restore();
    }
}

