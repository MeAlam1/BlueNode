package com.mealam.bluenode;

import com.mealam.bluenode.configs.StageConfigurer;
import com.mealam.bluenode.contentBrowser.ContentBrowser;
import com.mealam.bluenode.handlers.WindowInteractionHandler;
import com.mealam.bluenode.titleBar.TitleBar;
import com.mealam.bluenode.utils.conversion.ColorUtils;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class UIController extends Application {

    public static final BorderPane MAIN_ROOT = new BorderPane();
    public static final Canvas MAIN_CANVAS = new Canvas();
    public static final Scene MAIN_SCENE = new Scene(MAIN_ROOT);

    public static final ContentBrowser CONTENT_BROWSER = new ContentBrowser();
    ;
    public static final StackPane TITLE_BAR = new StackPane();

    @Override
    public void start(Stage pMainStage) {
        try {
            StageConfigurer.configureStage(pMainStage);

            initializeComponents(pMainStage);

            pMainStage.show();
            BaseLogger.log(BaseLogLevel.SUCCESS, "BlueNode application started successfully.");
        } catch (Exception pException) {
            BaseLogger.log(BaseLogLevel.ERROR, "An error occurred while starting the BlueNode application.", pException);
        }
    }

    private void initializeComponents(Stage pMainStage) {
        MAIN_ROOT.setStyle("-fx-background-color: " + ColorUtils.colorToString(ColorConstants.BACKGROUND_COLOR));

        TitleBar titleBar = new TitleBar(pMainStage);
        WindowInteractionHandler.setupWindowInteractions(pMainStage, titleBar, MAIN_ROOT);

        MAIN_ROOT.setTop(titleBar);

        BaseLogger.log(BaseLogLevel.SUCCESS, "Components initialized successfully.");
    }

    public static void main(String[] pArguments) {
        launch(pArguments);
    }
}
