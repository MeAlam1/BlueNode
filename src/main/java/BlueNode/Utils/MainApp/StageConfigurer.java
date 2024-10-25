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

        BaseLogger.log(ELogLevel.SUCCESS, "Stage configured successfully.");
    }

    private static Image loadIcon() {
        try {
            BaseLogger.log(ELogLevel.SUCCESS, "Application icon loaded successfully.");
            return ImageLoader.getImage(LOGO_PATH);
        } catch (Exception pException) {
            BaseLogger.log(ELogLevel.ERROR, "Failed to load application icon.", pException);
            return null;
        }
    }
}
