package com.mealam.bluenode.events.field;

import com.mealam.bluenode.components.fields.InputField;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyEventListener implements EventHandler<KeyEvent> {

    private final InputField inputField;

    public KeyEventListener(InputField inputField) {
        this.inputField = inputField;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.ESCAPE) {
            inputField.getParent().requestFocus();
        }
    }
}
