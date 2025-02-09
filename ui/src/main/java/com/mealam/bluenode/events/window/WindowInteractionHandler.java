package com.mealam.bluenode.events.window;

import com.mealam.bluenode.titleBar.TitleBar;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WindowInteractionHandler {

    public static void setupWindowInteractions(Stage pStage, TitleBar pTitleBar, BorderPane pRoot) {
        WindowDragger windowDragger = new WindowDragger();
        windowDragger.enableWindowDrag(pStage, pTitleBar);

        WindowResizer windowResizer = new WindowResizer();
        windowResizer.enableWindowResize(pStage, pRoot);

        BaseLogger.log(BaseLogLevel.SUCCESS, "Window interactions setup successfully.");
    }
}
