package com.mealam.bluenode.mainPanel;

import com.mealam.bluenode.UIConstants;
import com.mealam.bluenode.handlers.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.NodeRenderer;
import com.mealam.bluenode.nodes.library.NodeLibrary;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainPanel extends BorderPane {

    private static GraphicsContext graphicsContext;
    private static Stage primaryStage;
    private static GridDrawer gridDrawer;
    private static Pane overlayPane;
    public static List<Node> nodes;

    public MainPanel(Stage pPrimaryStage) {
        primaryStage = pPrimaryStage;
        graphicsContext = UIConstants.MAIN_CANVAS.getGraphicsContext2D();
        gridDrawer = new GridDrawer();
        nodes = new ArrayList<>();

        overlayPane = new Pane();
        overlayPane.setPickOnBounds(false);

        StackPane stackPane = new StackPane(UIConstants.MAIN_CANVAS, overlayPane);
        setCenter(stackPane);

        UIConstants.MAIN_CANVAS.widthProperty().bind(widthProperty());
        UIConstants.MAIN_CANVAS.heightProperty().bind(heightProperty());
        overlayPane.prefWidthProperty().bind(UIConstants.MAIN_CANVAS.widthProperty());
        overlayPane.prefHeightProperty().bind(UIConstants.MAIN_CANVAS.heightProperty());

        widthProperty().addListener((observable, oldValue, newValue) -> drawGrid());
        heightProperty().addListener((observable, oldValue, newValue) -> drawGrid());

        new CanvasDragHandler(UIConstants.MAIN_CANVAS, graphicsContext, gridDrawer, nodes, overlayPane);

        UIConstants.MAIN_CANVAS.addEventHandler(MouseEvent.MOUSE_PRESSED, pEvent -> {
            if (pEvent.getButton() == MouseButton.SECONDARY) { // Right-click
                if (!isNodeAtLocation(pEvent.getX(), pEvent.getY(), 150, 100)) {
                    NodeLibrary.createPopup(pPrimaryStage, NodeLoaderUtils.getAllNodes(), pEvent);
                }
            }
        });

        BaseLogger.log(BaseLogLevel.SUCCESS, "MainPanel initialized with infinite scrolling and zoom");
    }

    private void drawGrid() {
        double width = UIConstants.MAIN_CANVAS.getWidth();
        double height = UIConstants.MAIN_CANVAS.getHeight();

        graphicsContext.save();
        graphicsContext.clearRect(0, 0, width, height);
        graphicsContext.translate(CanvasDragHandler.getTranslateX(), CanvasDragHandler.getTranslateY());

        gridDrawer.drawGrid(graphicsContext, width, height, CanvasDragHandler.getTranslateX(), CanvasDragHandler.getTranslateY());
        gridDrawer.redraw(graphicsContext, width, height, CanvasDragHandler.getTranslateX(), CanvasDragHandler.getTranslateY());
    }

    public static boolean isNodeAtLocation(double pX, double pY, double width, double height) {
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

    public static void placeNode(Node pNode) {
        if (!isNodeAtLocation(pNode.getProperties().getX(), pNode.getProperties().getY(), pNode.getProperties().getWidth(), pNode.getProperties().getHeight())) {
            nodes.add(pNode);
            BaseLogger.log(BaseLogLevel.INFO, pNode.getProperties().toString());

            for (Node node : nodes) {
                NodeRenderer.render(graphicsContext, overlayPane, node, CanvasDragHandler.getTranslateX(), CanvasDragHandler.getTranslateY());
            }

            BaseLogger.log(BaseLogLevel.INFO, "Node Decrypted ID: " + NodeIDGenerator.decryptID(pNode.getProperties().getId()));
        } else {
            BaseLogger.log(BaseLogLevel.WARNING, "No suitable position found for new node.");
        }
    }

    public static void placeNode(Node pNode, double x, double y) {
        pNode.getProperties().setX(x);
        pNode.getProperties().setY(y);
        placeNode(pNode);
    }
}
