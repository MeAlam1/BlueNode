package com.mealam.bluenode;

import com.mealam.bluenode.config.StageConfigurer;
import com.mealam.bluenode.contentBrowser.ContentBrowser;
import com.mealam.bluenode.utils.conversion.ColorUtils;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Arrays;

public class UIController extends Application {

    public static final BorderPane MAIN_ROOT = new BorderPane();
    public static final Canvas MAIN_CANVAS = new Canvas();
    public static final Scene MAIN_SCENE = new Scene(MAIN_ROOT);

    public static final ContentBrowser CONTENT_BROWSER = new ContentBrowser();;

    @Override
    public void start(Stage pMainStage) {
        try {
            StageConfigurer.configureStage(pMainStage);

            initializeComponents();

            pMainStage.show();
            BaseLogger.log(BaseLogLevel.SUCCESS, "BlueNode application started successfully.");
        } catch (Exception pException) {
            BaseLogger.log(BaseLogLevel.ERROR, "An error occurred while starting the BlueNode application.", pException);
        }
    }

    private void initializeComponents() {
        MAIN_ROOT.setStyle("-fx-background-color: " + ColorUtils.colorToString(Constants.BACKGROUND_COLOR)); // grey

        //WindowInteractionHandler.setupWindowInteractions(pPrimaryStage, titleBar, JavaFXConstants.mainRoot);

        BaseLogger.log(BaseLogLevel.SUCCESS, "Components initialized successfully.");
    }

    public static void main(String[] pArguments) {
        launch(pArguments);
    }
}
