package com.mealam.bluenode.nodes;

import com.mealam.bluenode.handlers.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.nodes.components.input.Input;
import com.mealam.bluenode.nodes.input.FloatField;
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
        FloatField textField = new FloatField(input);
        textField.setText(input.getProperties().getDefaultValue());
        textField.setPromptText(input.getProperties().getName());
        textField.setLayoutX(x + CanvasDragHandler.getTranslateX());
        textField.setLayoutY(y + CanvasDragHandler.getTranslateY());
        textField.setPrefWidth(width - 10);

        parent.getChildren().add(textField);
    }
}
