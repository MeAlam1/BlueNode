package com.mealam.bluenode.nodes.input;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class FloatField extends TextField {

    public FloatField() {
        this.setContextMenu(new ContextMenu());
        this.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Right-click
                showCustomMenu(event);
            }
        });
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (validate(text)) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        if (validate(text)) {
            super.replaceSelection(text);
        }
    }

    private boolean validate(String text) {
        return text.matches("[0-9]*\\.?[0-9]*");
    }

    private void showCustomMenu(MouseEvent event) {
        Popup popup = new Popup();

        VBox menuBox = new VBox();
        menuBox.setSpacing(5);

        Text undoOption = new Text("Undo");
        undoOption.setOnMouseClicked(e -> {
            undo();
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
        popup.show(this, event.getScreenX(), event.getScreenY());
    }
}