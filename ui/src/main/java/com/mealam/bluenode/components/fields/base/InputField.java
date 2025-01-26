package com.mealam.bluenode.components.fields.base;

import com.mealam.bluenode.nodes.components.input.Input;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;

public abstract class InputField extends TextField {
    private final Input input;

    public InputField(Input pInput) {
        this.input = pInput;

        this.setContextMenu(new ContextMenu());
        this.getStyleClass().add("input-field");

        this.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                InputFieldContextMenu.showContextMenu(this, event);
            }
        });

        this.setOnAction(e -> {
            this.getParent().requestFocus();
            pInput.getProperties().setDefaultValue(this.getText());
        });

        this.focusedProperty().addListener((obs, oldFocused, newFocused) -> {
            if (!newFocused) {
                pInput.getProperties().setDefaultValue(this.getText());
            }
        });
    }

    protected abstract boolean validate(String text);

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
}
