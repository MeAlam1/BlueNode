package com.mealam.bluenode.components.fields.base;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class InputFieldContextMenu {

    public static void showContextMenu(TextField textField, MouseEvent event) {
        Popup popup = new Popup();

        VBox menuBox = new VBox();
        menuBox.setSpacing(5);

        Text undoOption = new Text("Undo");
        undoOption.setOnMouseClicked(e -> {
            textField.undo();
            popup.hide();
        });

        Text customOption = new Text("Random Action");
        customOption.setOnMouseClicked(e -> {
            System.out.println("Random action Clicked");
            popup.hide();
        });

        menuBox.getChildren().addAll(undoOption, customOption);

        popup.getContent().add(menuBox);
        popup.setAutoHide(true);
        popup.show(textField, event.getScreenX(), event.getScreenY());
    }
}
