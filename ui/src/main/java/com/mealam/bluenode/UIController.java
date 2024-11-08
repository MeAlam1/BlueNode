package com.mealam.bluenode;

import javafx.application.Application;
import javafx.stage.Stage;

public class UIController extends Application {

    @Override
    public void start(Stage pMainStage) {
        pMainStage.setTitle("BlueNode");
        pMainStage.show();
    }

    public static void main(String[] pArguments) {
        launch(pArguments);
    }
}
