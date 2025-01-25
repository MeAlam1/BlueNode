package com.mealam.bluenode.nodes;

import com.mealam.bluenode.nodes.components.input.Input;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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
    public static void render(Pane parent, Input input, double x, double y, double width, double height) {
        TextField textField = new TextField();
        textField.setLayoutX(x + 5);
        textField.setLayoutY(y + (height / 2) - 10);
        textField.setPrefWidth(width - 15);
        textField.setPrefHeight(20);

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            input.getProperties().setDefaultValue(newValue);
        });

        parent.getChildren().add(textField);
    }
}
