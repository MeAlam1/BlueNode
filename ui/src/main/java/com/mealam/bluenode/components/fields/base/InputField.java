package com.mealam.bluenode.components.fields.base;

import com.mealam.bluenode.nodes.components.input.Input;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;

public abstract class InputField extends TextField {
    private final Input input;

    public InputField(Input pInput) {
        this.input = pInput;

        this.setContextMenu(new ContextMenu());

        configureField();
        addEventListeners();
    }

    private void configureField() {
        this.setText(input.getProperties().getDefaultValue());
        this.setPromptText(input.getProperties().getName());
        this.getStyleClass().add("input-field");
    }

    private void addEventListeners() {
        this.setOnMousePressed(pEvent -> InputFieldContextMenu.handleRightClick(this, pEvent));
        this.setOnAction(pEvent -> saveValue());
        this.focusedProperty().addListener((pObservableValue, pOldFocused, pNewFocused) -> {
            if (!pNewFocused) saveValue();
        });
    }

    private void saveValue() {
        input.getProperties().setDefaultValue(this.getText());
        if (this.getParent() != null) {
            this.getParent().requestFocus();
        }
    }

    protected abstract boolean validate(String pText);

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
