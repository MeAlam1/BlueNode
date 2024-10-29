package BlueNode.UI.MainPanel;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import BlueNode.Nodes.Browser.NodeBrowser;
import BlueNode.Nodes.Events.NodeEventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainPanel extends BorderPane {

    public static Canvas canvas = new Canvas();
    private final GraphicsContext graphicsContext;
    private final GridDrawer gridDrawer;
    private double translateX = 0;
    private double translateY = 0;
    private double lastX;
    private double lastY;

    public MainPanel(Stage pPrimaryStage) {
        graphicsContext = canvas.getGraphicsContext2D();
        gridDrawer = new GridDrawer();

        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());

        widthProperty().addListener((observable, oldValue, newValue) -> drawGrid());
        heightProperty().addListener((observable, oldValue, newValue) -> drawGrid());

        setCenter(canvas);

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, pEvent -> {
            lastX = pEvent.getX();
            lastY = pEvent.getY();
        });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, pEvent -> {
            translateX += pEvent.getX() - lastX;
            translateY += pEvent.getY() - lastY;

            graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gridDrawer.redraw(graphicsContext, canvas.getWidth(), canvas.getHeight(), translateX, translateY);

            lastX = pEvent.getX();
            lastY = pEvent.getY();
        });

        BaseLogger.log(ELogLevel.SUCCESS, "MainPanel initialized with infinite scrolling and zoom");

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
    }

    private void drawGrid() {
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        graphicsContext.save();
        graphicsContext.clearRect(0, 0, width, height);
        graphicsContext.translate(translateX, translateY);

        gridDrawer.drawGrid(graphicsContext, width, height, translateX, translateY);

        graphicsContext.restore();

        BaseLogger.log(ELogLevel.SUCCESS, "Infinite grid drawn with zoom and pan support");
    }
}
