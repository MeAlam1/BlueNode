package BlueNode.Listener;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class WindowDragger {

    private double xOffset = 0;
    private double yOffset = 0;

    public void enableWindowDrag(Stage pStage, Region pTitleBar) {
        pTitleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
            BaseLogger.log(ELogLevel.DEBUG, "WindowDragger" + "xOffset: " + xOffset + " yOffset: " + yOffset);
        });

        pTitleBar.setOnMouseDragged(pEvent -> {
            pStage.setX(pEvent.getScreenX() - xOffset);
            pStage.setY(pEvent.getScreenY() - yOffset);
            BaseLogger.log(ELogLevel.DEBUG, "WindowDragger" + "x: " + pStage.getX() + " y: " + pStage.getY());
        });
    }
}
