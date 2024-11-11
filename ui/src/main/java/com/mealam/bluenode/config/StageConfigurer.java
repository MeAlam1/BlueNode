package com.mealam.bluenode.config;

import com.mealam.bluenode.Constants;
import com.mealam.bluenode.UIController;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageConfigurer {
    public static void configureStage(Stage pStage) {
        pStage.setTitle(Constants.APP_NAME);
        pStage.setWidth(Constants.DEFAULT_WIDTH);
        pStage.setHeight(Constants.DEFAULT_HEIGHT);
        pStage.centerOnScreen();
        pStage.initStyle(StageStyle.UNDECORATED);
        //pStage.getIcons().add(loadIcon());
        pStage.setScene(UIController.MAIN_SCENE);

        BaseLogger.log(BaseLogLevel.SUCCESS, "Stage configured successfully.");
    }
}
