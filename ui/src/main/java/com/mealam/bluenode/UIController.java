package com.mealam.bluenode;

import com.mealam.bluenode.configs.StageConfigurer;
import com.mealam.bluenode.events.window.WindowInteractionHandler;
import com.mealam.bluenode.io.BlueNodeIO;
import com.mealam.bluenode.mainPanel.MainPanel;
import com.mealam.bluenode.styles.AsyncStyleLoader;
import com.mealam.bluenode.styles.StyleLoader;
import com.mealam.bluenode.titleBar.TitleBar;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.application.Application;
import javafx.stage.Stage;

public class UIController extends Application {

    @Override
    public void start(Stage pPrimaryStage) {
        try {
            AsyncStyleLoader.loadAllStyles(StyleLoader.class.getClassLoader().getResource("styles"));
            StageConfigurer.configureStage(pPrimaryStage);

            initializeComponents(pPrimaryStage);
            CoreController.initializeCore();
            if (Common.getDevMode()) {
                BlueNodeIO.create("test", "test");
            }
            pPrimaryStage.show();
            BaseLogger.log(BaseLogLevel.SUCCESS, "BlueNode application started successfully.");
        } catch (Exception pException) {
            BaseLogger.log(BaseLogLevel.ERROR, "An error occurred while starting the BlueNode application.", pException);
        }
    }

    private void initializeComponents(Stage pPrimaryStage) {
        TitleBar titleBar = new TitleBar(pPrimaryStage);
        MainPanel mainPanel = new MainPanel(pPrimaryStage);
        WindowInteractionHandler.setupWindowInteractions(pPrimaryStage, titleBar, UIConstants.MAIN_ROOT);

        UIConstants.MAIN_ROOT.setCenter(mainPanel);
        UIConstants.MAIN_ROOT.setTop(titleBar);

        BaseLogger.log(BaseLogLevel.SUCCESS, "Components initialized successfully.");
    }

    public static void main(String[] pArguments) {
        launch(pArguments);
    }
}
