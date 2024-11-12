package com.mealam.bluenode.mainPanel;

import com.mealam.bluenode.UIController;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class MainPanel extends BorderPane {

    private final GraphicsContext graphicsContext;
    private final GridDrawer gridDrawer;
    private double translateX = 0;
    private double translateY = 0;
    private double lastX;
    private double lastY;

    public MainPanel() {
        graphicsContext = UIController.MAIN_CANVAS.getGraphicsContext2D();
        gridDrawer = new GridDrawer();

        UIController.MAIN_CANVAS.widthProperty().bind(widthProperty());
        UIController.MAIN_CANVAS.heightProperty().bind(heightProperty());

        widthProperty().addListener((observable, oldValue, newValue) -> drawGrid());
        heightProperty().addListener((observable, oldValue, newValue) -> drawGrid());

        setCenter(UIController.MAIN_CANVAS);

        UIController.MAIN_CANVAS.addEventHandler(MouseEvent.MOUSE_PRESSED, pEvent -> {
            lastX = pEvent.getX();
            lastY = pEvent.getY();
        });

        UIController.MAIN_CANVAS.addEventHandler(MouseEvent.MOUSE_DRAGGED, pEvent -> {
            translateX += pEvent.getX() - lastX;
            translateY += pEvent.getY() - lastY;

            graphicsContext.clearRect(0, 0, UIController.MAIN_CANVAS.getWidth(), UIController.MAIN_CANVAS.getHeight());
            gridDrawer.redraw(graphicsContext, UIController.MAIN_CANVAS.getWidth(), UIController.MAIN_CANVAS.getHeight(), translateX, translateY);

            lastX = pEvent.getX();
            lastY = pEvent.getY();
        });

        BaseLogger.log(BaseLogLevel.SUCCESS, "MainPanel initialized with infinite scrolling and zoom");

        UIController.MAIN_CANVAS.addEventHandler(MouseEvent.MOUSE_PRESSED, pEvent -> {
            if (pEvent.getButton() == MouseButton.PRIMARY) {
                // Left click
                BaseLogger.log(BaseLogLevel.SUCCESS, "Left mouse button clicked");
            } else if (pEvent.getButton() == MouseButton.MIDDLE) {
                // Middle click
                BaseLogger.log(BaseLogLevel.SUCCESS, "Mouse mouse button clicked");
            } else if (pEvent.getButton() == MouseButton.SECONDARY) {
                // Right click
                BaseLogger.log(BaseLogLevel.SUCCESS, "Right mouse button clicked");
            }
        });
    }

    private void drawGrid() {
        double width = UIController.MAIN_CANVAS.getWidth();
        double height = UIController.MAIN_CANVAS.getHeight();

        graphicsContext.save();
        graphicsContext.clearRect(0, 0, width, height);
        graphicsContext.translate(translateX, translateY);

        gridDrawer.drawGrid(graphicsContext, width, height, translateX, translateY);

        graphicsContext.restore();

        BaseLogger.log(BaseLogLevel.SUCCESS, "Infinite grid drawn with zoom and pan support");
    }
}
