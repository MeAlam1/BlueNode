package com.mealam.bluenode.components.buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class TextButton extends Button {

    private static final String DEFAULT_CSS_CLASS = "text-button";

    public TextButton(String pText, String pCSSClass, double pWidth, double pHeight, EventHandler<MouseEvent> pAction) {
        super(pText);
        setPrefSize(pWidth, pHeight);
        getStyleClass().add(DEFAULT_CSS_CLASS);
        getStyleClass().add(pCSSClass);
        setOnMouseClicked(pAction);
    }
}
