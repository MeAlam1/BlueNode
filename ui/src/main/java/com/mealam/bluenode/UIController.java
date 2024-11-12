package com.mealam.bluenode;

import com.mealam.bluenode.configs.StageConfigurer;
import com.mealam.bluenode.contentBrowser.ContentBrowser;
import com.mealam.bluenode.handlers.WindowInteractionHandler;
import com.mealam.bluenode.mainPanel.MainPanel;
import com.mealam.bluenode.titleBar.TitleBar;
import com.mealam.bluenode.utils.conversion.ColorUtils;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UIController extends Application {

    public static final BorderPane MAIN_ROOT = new BorderPane();
    public static final Canvas MAIN_CANVAS = new Canvas();
    public static final Scene MAIN_SCENE = new Scene(MAIN_ROOT);

    public static final ContentBrowser CONTENT_BROWSER = new ContentBrowser();

    @Override
    public void start(Stage pPrimaryStage) {
        try {
            StageConfigurer.configureStage(pPrimaryStage);

            initializeComponents(pPrimaryStage);

            pPrimaryStage.show();
            BaseLogger.log(BaseLogLevel.SUCCESS, "BlueNode application started successfully.");
        } catch (Exception pException) {
            BaseLogger.log(BaseLogLevel.ERROR, "An error occurred while starting the BlueNode application.", pException);
        }
    }

    private void initializeComponents(Stage pPrimaryStage) {
        MAIN_ROOT.setStyle("-fx-background-color: " + ColorUtils.colorToString(ColorConstants.BACKGROUND_COLOR));

        TitleBar titleBar = new TitleBar(pPrimaryStage);
        MainPanel mainPanel = new MainPanel();
        WindowInteractionHandler.setupWindowInteractions(pPrimaryStage, titleBar, MAIN_ROOT);

        MAIN_ROOT.setTop(titleBar);
        MAIN_ROOT.setCenter(mainPanel);

        BaseLogger.log(BaseLogLevel.SUCCESS, "Components initialized successfully.");
    }

    public static void main(String[] pArguments) {
        launch(pArguments);
    }
}
