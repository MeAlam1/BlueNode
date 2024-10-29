package BlueNode.UI.MainPanel;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import BlueNode.Nodes.Browser.NodeBrowser;
import BlueNode.Nodes.Events.NodeEventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainPanel extends BorderPane {

    public static Canvas canvas = new Canvas();
    private final GraphicsContext graphicsContext;
    private final GridDrawer gridDrawer;

    public MainPanel(Stage pPrimaryStage) {
        graphicsContext = canvas.getGraphicsContext2D();
        gridDrawer = new GridDrawer();

        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());

        widthProperty().addListener((observable, oldValue, newValue) -> drawGrid());
        heightProperty().addListener((observable, oldValue, newValue) -> drawGrid());

        setCenter(canvas);

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, pEvent -> {
            if (pEvent.getButton() == MouseButton.PRIMARY) {
                // Left click
                BaseLogger.log(ELogLevel.SUCCESS, "Left mouse button clicked");
            } else if (pEvent.getButton() == MouseButton.MIDDLE) {
                // Middle click
                BaseLogger.log(ELogLevel.SUCCESS, "Mouse mouse button clicked");
            } else if (pEvent.getButton() == MouseButton.SECONDARY) {
                // Right click
                BaseLogger.log(ELogLevel.SUCCESS, "Right mouse button clicked");
                NodeBrowser.showCustomPopup(pPrimaryStage, pEvent);
                NodeEventHandler.addGlobalClickListener((Pane) pPrimaryStage.getScene().getRoot());
            }
        });

        BaseLogger.log(ELogLevel.SUCCESS, "MainPanel initialized");
    }

    private void drawGrid() {
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        gridDrawer.drawGrid(graphicsContext, width, height);

        BaseLogger.log(ELogLevel.SUCCESS, "Grid drawn");
    }
}
