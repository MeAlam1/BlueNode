package com.mealam.bluenode.nodes;

import com.mealam.bluenode.handlers.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.nodes.components.input.Input;
import com.mealam.bluenode.nodes.input.CustomTextField;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
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
        CustomTextField textField = new CustomTextField();
        textField.setText(input.getProperties().getDefaultValue());
        textField.setPromptText(input.getProperties().getName());
        textField.setLayoutX(x + CanvasDragHandler.getTranslateX());
        textField.setLayoutY(y + CanvasDragHandler.getTranslateY());
        textField.setPrefWidth(width - 10);

        textField.setOnAction(e -> parent.getChildren().remove(textField));
        textField.focusedProperty().addListener((obs, oldFocused, newFocused) -> {
            if (!newFocused) {
                parent.getChildren().remove(textField);
                input.getProperties().setDefaultValue(textField.getText());
            }
        });

        parent.getChildren().add(textField);
    }
}
