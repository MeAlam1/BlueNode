package com.mealam.bluenode.titleBar;

import com.mealam.bluenode.UIController;
import com.mealam.bluenode.components.ImageLoader;
import com.mealam.bluenode.titleBar.buttons.FileButton;
import com.mealam.bluenode.titleBar.buttons.SettingsButton;
import com.mealam.bluenode.titleBar.buttons.controlButtons.ButtonPanel;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TitleBar extends BorderPane {
    public TitleBar(Stage pStage) {
        setStyle("-fx-background-color: #2D2D2D;");
        setPrefHeight(30);

        HBox leftPanel = new HBox();
        leftPanel.setPadding(new Insets(0, 5, 0, 5));
        leftPanel.setSpacing(10);
        leftPanel.setAlignment(Pos.CENTER_LEFT);
        HBox contentContainer = new HBox();
        contentContainer.setAlignment(Pos.CENTER_LEFT);

        ImageLoader logoLoader = new ImageLoader("/assets/images/logo.png", 40, 40);
        logoLoader.setPadding(new Insets(0, 5, 0, 5));
        contentContainer.getChildren().add(logoLoader);

        FileButton fileButton = new FileButton(pStage);
        contentContainer.getChildren().add(fileButton);

        SettingsButton settingsButton = new SettingsButton(pStage);
        contentContainer.getChildren().add(settingsButton);

        leftPanel.getChildren().add(contentContainer);

        ButtonPanel buttonPanel = new ButtonPanel(pStage);
        UIController.TITLE_BAR.getChildren().addAll(buttonPanel);
        UIController.TITLE_BAR.setAlignment(Pos.TOP_RIGHT);
        setCenter(UIController.TITLE_BAR);

        updatePadding(pStage.getWidth());

        pStage.widthProperty().addListener((observable, oldValue, newValue) -> updatePadding(newValue.doubleValue()));

        setLeft(leftPanel);

        BaseLogger.log(BaseLogLevel.SUCCESS, "TitleBar has been created successfully");
    }

    // TODO: This Method is really inefficient, it should be Optimized and fixed
    private void updatePadding(double pWindowWidth) {
        double leftPadding = Math.max(0, pWindowWidth - 325);
        UIController.TITLE_BAR.setPadding(new Insets(0, 0, 0, leftPadding));
    }
}

