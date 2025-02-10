package com.mealam.bluenode.events.field;

import com.mealam.bluenode.components.fields.InputField;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyEventListener implements EventHandler<KeyEvent> {

    private final InputField inputField;

    public KeyEventListener(InputField pInputField) {
        this.inputField = pInputField;
    }

    @Override
    public void handle(KeyEvent pEvent) {
        if (pEvent.getCode() == KeyCode.ENTER || pEvent.getCode() == KeyCode.ESCAPE) {
            inputField.getParent().requestFocus();
        }
    }
}
