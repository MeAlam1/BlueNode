package BlueNode.Utils.MainApp;

import BlueNode.Listener.WindowDragger;
import BlueNode.Listener.WindowResizer;
import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import BlueNode.UI.TitleBar.TitleBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WindowInteractionHandler {

    public static void setupWindowInteractions(Stage pStage, TitleBar pTitleBar, BorderPane pRoot) {
        WindowDragger windowDragger = new WindowDragger();
        windowDragger.enableWindowDrag(pStage, pTitleBar);

        WindowResizer windowResizer = new WindowResizer();
        windowResizer.enableWindowResize(pStage, pRoot);

        BaseLogger.log(ELogLevel.INFO, "Window interactions setup successfully.");
    }
}
