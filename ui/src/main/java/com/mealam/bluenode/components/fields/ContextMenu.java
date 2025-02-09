package com.mealam.bluenode.components.fields;

import javafx.event.Event;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class ContextMenu {

    private static final Popup popup = new Popup();

    public static void addContextMenuListener(TextField textField) {
        textField.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);

        textField.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                if (popup.isShowing()) {
                    popup.hide();
                } else {
                    showContextMenu(textField, event);
                }
                event.consume();
            } else if (event.getButton() == MouseButton.PRIMARY && popup.isShowing()) {
                popup.hide();
            }
        });
    }

    public static void showContextMenu(TextField textField, MouseEvent event) {
        popup.getContent().clear();

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
            popup.hide();
        });
        return menuItem;
    }
}
