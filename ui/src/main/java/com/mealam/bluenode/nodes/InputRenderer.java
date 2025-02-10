package com.mealam.bluenode.nodes;

import com.mealam.bluenode.components.fields.InputField;
import com.mealam.bluenode.events.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.nodes.components.input.Input;

public class InputRenderer {

    public static void render(NodePane pParentNodePane, Input pInput, Node pNode, NodePane pNodePane, double pX, double pY, double pWidth, double pHeight) {
        InputField textField = new InputField(pInput, pNode, pNodePane);
        textField.setLayoutX(pX + CanvasDragHandler.getTranslateX());
        textField.setLayoutY(pY + CanvasDragHandler.getTranslateY());
        textField.setMaxWidth(pWidth - 10);
        textField.setPrefWidth(pInput.getProperties().getWidth());
        textField.setPrefHeight(20);

        pParentNodePane.getChildren().add(textField);
    }
}