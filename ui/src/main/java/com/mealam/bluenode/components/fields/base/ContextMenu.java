package com.mealam.bluenode.components.fields.base;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class ContextMenu {

    public static void showContextMenu(TextField textField, MouseEvent event) {
        Popup popup = new Popup();

        VBox menuBox = new VBox();
        menuBox.getStyleClass().add("context-menu");

        Text undoOption = createMenuItem("Undo", textField::undo);
        Text customOption = createMenuItem("Random Action", () -> {
            System.out.println("Random action Clicked");
        });

        menuBox.getChildren().addAll(undoOption, customOption);
        popup.getContent().add(menuBox);
        popup.setAutoHide(true);
        popup.show(textField, event.getScreenX(), event.getScreenY());
    }

    private static Text createMenuItem(String text, Runnable action) {
        Text menuItem = new Text(text);
        menuItem.getStyleClass().add("menu-item");
        menuItem.setOnMouseClicked(e -> {
            action.run();
        });
        return menuItem;
    }
}
