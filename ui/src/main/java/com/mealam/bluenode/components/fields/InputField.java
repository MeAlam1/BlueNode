package com.mealam.bluenode.components.fields;

import com.mealam.bluenode.events.field.KeyEventListener;
import com.mealam.bluenode.events.field.TextFieldSizeUpdateListener;
import com.mealam.bluenode.events.field.ValidationHandler;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.NodePane;
import com.mealam.bluenode.nodes.components.input.Input;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class InputField extends TextField {

    private final Input input;
    private final ValidationHandler validationHandler;

    public InputField(Input pInput, Node pNode, NodePane nodePane) {
        this.input = pInput;
        this.validationHandler = new ValidationHandler(pInput);

        configureField();
        new TextFieldSizeUpdateListener(textProperty(), pNode, nodePane, this, input);
        this.addEventFilter(KeyEvent.KEY_PRESSED, new KeyEventListener(this));
        ContextMenu.addContextMenuListener(this);
    }

    private void configureField() {
        this.setText(input.getProperties().getValue());
        this.setPromptText(input.getProperties().getName());
        this.getStyleClass().add("input-field");
        this.getStyleClass().add(input.getProperties().getCss());
    }

    protected boolean validate(String pText) {
        return validationHandler.validate(pText);
    }

    @Override
    public void replaceText(int pStart, int pEnd, String pText) {
        if (validate(pText)) {
            super.replaceText(pStart, pEnd, pText);
        }
    }

    @Override
    public void replaceSelection(String pText) {
        if (validate(pText)) {
            super.replaceSelection(pText);
        }
    }
}