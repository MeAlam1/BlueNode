package com.mealam.bluenode.titleBar;

import com.mealam.bluenode.ColorConstants;
import com.mealam.bluenode.components.ImageLoader;
import com.mealam.bluenode.titleBar.buttons.FileButton;
import com.mealam.bluenode.titleBar.buttons.SettingsButton;
import com.mealam.bluenode.titleBar.buttons.controlButtons.ButtonPanel;
import com.mealam.bluenode.utils.conversion.ColorUtils;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TitleBar extends BorderPane {
    public TitleBar(Stage pStage) {
        setStyle("-fx-background-color: " + ColorUtils.colorToString(ColorConstants.NORMAL_COLOR));
        setPrefHeight(30);

        HBox leftPanel = new HBox();
        leftPanel.setPadding(new Insets(0, 5, 0, 5));
        leftPanel.setSpacing(10);
        leftPanel.setAlignment(Pos.CENTER_LEFT);

        ImageLoader logoLoader = new ImageLoader("/assets/images/logo.png", 40, 40);
        logoLoader.setPadding(new Insets(0, 5, 0, 5));
        leftPanel.getChildren().add(logoLoader);

        FileButton fileButton = new FileButton(pStage);
        leftPanel.getChildren().add(fileButton);

        SettingsButton settingsButton = new SettingsButton(pStage);
        leftPanel.getChildren().add(settingsButton);

        HBox rightPanel = new HBox();
        rightPanel.setAlignment(Pos.CENTER_RIGHT);

        ButtonPanel buttonPanel = new ButtonPanel(pStage);
        rightPanel.getChildren().add(buttonPanel);

        setRight(rightPanel);

        setLeft(leftPanel);

        BaseLogger.log(BaseLogLevel.SUCCESS, "TitleBar has been created successfully");
    }
}




