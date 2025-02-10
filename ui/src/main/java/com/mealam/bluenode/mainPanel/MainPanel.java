package com.mealam.bluenode.mainPanel;

import com.mealam.bluenode.UIConstants;
import com.mealam.bluenode.events.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.events.mainPanel.CanvasInteractionHandler;
import com.mealam.bluenode.mainPanel.grid.GridManager;
import com.mealam.bluenode.nodes.NodeManager;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainPanel extends BorderPane {

    private final GridManager gridManager;

    public MainPanel(Stage pPrimaryStage) {
        GraphicsContext graphicsContext = UIConstants.MAIN_CANVAS.getGraphicsContext2D();
        Pane overlayPane = new Pane();
        overlayPane.setPickOnBounds(false);
        NodeManager.setOverlayPane(overlayPane);

        StackPane stackPane = new StackPane(UIConstants.MAIN_CANVAS, overlayPane);
        setCenter(stackPane);

        gridManager = new GridManager(graphicsContext);
        new CanvasInteractionHandler(pPrimaryStage);

        UIConstants.MAIN_CANVAS.widthProperty().bind(widthProperty());
        UIConstants.MAIN_CANVAS.heightProperty().bind(heightProperty());
        overlayPane.prefWidthProperty().bind(UIConstants.MAIN_CANVAS.widthProperty());
        overlayPane.prefHeightProperty().bind(UIConstants.MAIN_CANVAS.heightProperty());

        new CanvasDragHandler(UIConstants.MAIN_CANVAS, graphicsContext, gridManager.getGridDrawer(), NodeManager.getNodes(), overlayPane);

        widthProperty().addListener((observable, oldValue, newValue) -> gridManager.drawGrid());
        heightProperty().addListener((observable, oldValue, newValue) -> gridManager.drawGrid());

        BaseLogger.log(BaseLogLevel.SUCCESS, "MainPanel initialized with infinite scrolling and zoom");
    }
}
