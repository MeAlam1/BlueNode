package com.mealam.bluenode.nodes;

import com.mealam.bluenode.components.fields.InputField;
import com.mealam.bluenode.handlers.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.nodes.components.input.Input;

public class InputRenderer {

    /**
     * Renders a TextField at the specified location on the given parent container.
     *
     * @param parent the parent container to add the TextField to
     * @param input  the Input object to associate with the TextField
     * @param x      the x-coordinate of the TextField
     * @param y      the y-coordinate of the TextField
     * @param width  the width of the TextField
     * @param height the height of the TextField
     */
    public static void render(NodePane parent, Input input, Node pNode, double x, double y, double width, double height) {
        InputField textField = new InputField(parent, input, pNode);
        textField.setLayoutX(x + CanvasDragHandler.getTranslateX());
        textField.setLayoutY(y + CanvasDragHandler.getTranslateY());
        textField.setMaxWidth(width - 10);
        textField.setPrefWidth(input.getProperties().getWidth());
        textField.setPrefHeight(20);

        parent.getChildren().add(textField);
    }

}
