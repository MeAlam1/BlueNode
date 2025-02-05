package com.mealam.bluenode;

import com.mealam.bluenode.configs.StageConfigurer;
import com.mealam.bluenode.handlers.WindowInteractionHandler;
import com.mealam.bluenode.mainPanel.MainPanel;
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
            /* OPTIMIZE:
             *  [AI] - Load styles in a separate thread to prevent UI from freezing.
             *  [MeAlam] - Refactor all Styling across the UI into the new Styling system.
             */
            StyleLoader.loadAllStyles(StyleLoader.class.getClassLoader().getResource("styles"));
            StageConfigurer.configureStage(pPrimaryStage);

            initializeComponents(pPrimaryStage);
            CoreController.initializeCore();

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
