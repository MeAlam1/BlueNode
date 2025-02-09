package com.mealam.bluenode.nodes;

import com.mealam.bluenode.components.fields.InputField;
import com.mealam.bluenode.events.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.nodes.components.input.Input;

public class InputRenderer {

    public static void render(NodePane parent, Input input, Node pNode, NodePane pNodePane, double x, double y, double width, double height) {
        InputField textField = new InputField(input, pNode, pNodePane);
        textField.setLayoutX(x + CanvasDragHandler.getTranslateX());
        textField.setLayoutY(y + CanvasDragHandler.getTranslateY());
        textField.setMaxWidth(width - 10);
        textField.setPrefWidth(input.getProperties().getWidth());
        textField.setPrefHeight(20);

        parent.getChildren().add(textField);
    }
}