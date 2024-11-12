package com.mealam.bluenode.listeners;

import com.mealam.bluenode.titleBar.TitleBar;
import javafx.stage.Stage;

public class WindowDragger {

    private double xOffset = 0;
    private double yOffset = 0;

    public void enableWindowDrag(Stage pStage, TitleBar pTitleBar) {
        pTitleBar.setOnMousePressed(pEvent -> {
            xOffset = pEvent.getSceneX();
            yOffset = pEvent.getSceneY();
        });

        pTitleBar.setOnMouseDragged(pEvent -> {
            pStage.setX(pEvent.getScreenX() - xOffset);
            pStage.setY(pEvent.getScreenY() - yOffset);
        });
    }
}
