package BlueNode.Utils.MainApp;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import BlueNode.UI.Components.ImageLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageConfigurer {

    private static final String TITLE = "Lua Node Editor";
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 800;
    private static final String LOGO_PATH = "/assets/images/logo.png";

    public static void configureStage(Stage pStage) {
        pStage.setTitle(TITLE);
        pStage.setWidth(DEFAULT_WIDTH);
        pStage.setHeight(DEFAULT_HEIGHT);
        pStage.centerOnScreen();
        pStage.initStyle(StageStyle.UNDECORATED);
        pStage.getIcons().add(loadIcon());

        BaseLogger.log("Stage configured successfully.", ELogLevel.INFO);
    }

    private static Image loadIcon() {
        try {
            BaseLogger.log("Application icon loaded successfully.", ELogLevel.INFO);
            return ImageLoader.getImage(LOGO_PATH);
        } catch (Exception pException) {
            BaseLogger.log("Failed to load application icon.", ELogLevel.ERROR, pException);
            return null;
        }
    }
}
