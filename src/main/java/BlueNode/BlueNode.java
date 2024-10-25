package BlueNode;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import BlueNode.UI.ContentBrowser.ContentBrowser;
import BlueNode.UI.MainPanel.MainPanel;
import BlueNode.UI.TitleBar.TitleBar;
import BlueNode.Utils.MainApp.SceneConfigurer;
import BlueNode.Utils.MainApp.StageConfigurer;
import BlueNode.Utils.MainApp.WindowInteractionHandler;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BlueNode extends Application {

    public static ContentBrowser contentBrowser;
    public static BorderPane root;
    public static MainPanel mainPanel;
    public static final Color BACKGROUND_COLOR = Color.rgb(35, 35, 35);

    @Override
    public void start(Stage pPrimaryStage) {
        try {
            StageConfigurer.configureStage(pPrimaryStage);

            initializeComponents(pPrimaryStage);

            SceneConfigurer.configureScene(pPrimaryStage, root);

            pPrimaryStage.show();
            BaseLogger.log(ELogLevel.SUCCESS, "BlueNode application started successfully.");
        } catch (Exception pException) {
            BaseLogger.log(ELogLevel.ERROR, "An error occurred while starting the BlueNode application.", pException);
        }
    }

    private void initializeComponents(Stage pPrimaryStage) {
        root = new BorderPane();
        root.setStyle("-fx-background-color: #232323;");

        TitleBar titleBar = new TitleBar(pPrimaryStage);
        mainPanel = new MainPanel(pPrimaryStage);
        contentBrowser = new ContentBrowser();

        WindowInteractionHandler.setupWindowInteractions(pPrimaryStage, titleBar, root);

        root.setTop(titleBar);
        root.setCenter(mainPanel);

        BaseLogger.log(ELogLevel.SUCCESS, "Components initialized successfully.");
    }

    public static void main(String[] pArgs) {
        launch(pArgs);
    }
}
