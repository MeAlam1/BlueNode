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

    public static void addContextMenuListener(TextField pTextField) {
        pTextField.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);

        pTextField.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                if (popup.isShowing()) {
                    popup.hide();
                } else {
                    showContextMenu(pTextField, event);
                }
                event.consume();
            } else if (event.getButton() == MouseButton.PRIMARY && popup.isShowing()) {
                popup.hide();
            }
        });
    }

    public static void showContextMenu(TextField pTextField, MouseEvent pEvent) {
        popup.getContent().clear();

        VBox menuBox = new VBox();
        menuBox.getStyleClass().add("context-menu");

        Text undoOption = createMenuItem("Undo", pTextField::undo);
        Text customOption = createMenuItem("Random Action", () -> {
            System.out.println("Random action Clicked");
        });

        menuBox.getChildren().addAll(undoOption, customOption);
        popup.getContent().add(menuBox);

        popup.setAutoHide(true);
        popup.show(pTextField, pEvent.getScreenX(), pEvent.getScreenY());
    }

    private static Text createMenuItem(String pText, Runnable pAction) {
        Text menuItem = new Text(pText);
        menuItem.getStyleClass().add("menu-item");
        menuItem.setOnMouseClicked(e -> {
            pAction.run();
            popup.hide();
        });
        return menuItem;
    }
}
