package com.mealam.bluenode;

import com.mealam.bluenode.handlers.mainPanel.CanvasDragHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

// NOTE: This class is not used in the project. It is a placeholder for Temp Code.
public class JunkDrawer {

    public static void createTempTextField(Pane overlayPane, double x, double y, double width) {
        TextField textField = new TextField();
        textField.setPromptText("Enter text...");
        textField.setLayoutX(x + CanvasDragHandler.getTranslateX());
        textField.setLayoutY(y + CanvasDragHandler.getTranslateY());
        textField.setPrefWidth(width - 10);

        // Remove TextField when losing focus or pressing Enter
        textField.setOnAction(e -> overlayPane.getChildren().remove(textField));
        textField.focusedProperty().addListener((obs, oldFocused, newFocused) -> {
            if (!newFocused) {
                overlayPane.getChildren().remove(textField);
            }
        });

        overlayPane.getChildren().add(textField);
    }
}
