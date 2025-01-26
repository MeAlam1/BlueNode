package com.mealam.bluenode.components.fields.base;

import com.mealam.bluenode.nodes.components.input.Input;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import static com.sun.javafx.scene.control.skin.Utils.computeTextWidth;

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

        textProperty().addListener((observable, oldValue, newValue) -> {
            Text text = new Text(newValue);
            text.setFont(this.getFont());
            double textWidth = text.getLayoutBounds().getWidth();
            double padding = this.getInsets().getLeft() + this.getInsets().getRight();
            setPrefWidth(Math.max(40, textWidth + padding));
            input.getProperties().setWidth(textWidth + padding);
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
