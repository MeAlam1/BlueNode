package com.mealam.bluenode.components.fields;

import com.mealam.bluenode.handlers.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.components.input.Input;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class InputField extends TextField {

    private final Input input;

    public InputField(Pane pPane, Input pInput, Node pNode) {
        this.input = pInput;

        configureField();

        addEventListeners(pPane, pNode);
    }

    private void configureField() {
        this.setText(input.getProperties().getValue());
        this.setPromptText(input.getProperties().getName());
        this.getStyleClass().add("input-field");
        this.getStyleClass().add(input.getProperties().getCss());
    }

    private void addEventListeners(Pane pPane, Node pNode) {
        textProperty().addListener((observable, oldValue, newValue) -> {
            updateFieldSize(pNode, newValue);
        });

        this.setOnAction(event -> saveValue());

        this.focusedProperty().addListener((observable, oldFocused, newFocused) -> {
            if (!newFocused) {
                saveValue();
            }
        });
    }

    private void updateFieldSize(Node pNode, String newValue) {
        Text text = new Text(newValue);
        text.setFont(this.getFont());
        double textWidth = text.getLayoutBounds().getWidth();
        double padding = this.getInsets().getLeft() + this.getInsets().getRight();
        double newWidth = Math.max(40, textWidth + padding);
        pNode.updateSize();
        this.setPrefWidth(newWidth);
        input.getProperties().setWidth(newWidth);
        input.getProperties().setValue(newValue);
        CanvasDragHandler.redraw();
    }

    private void saveValue() {
        input.getProperties().setValue(this.getText());
        if (this.getParent() != null) {
            this.getParent().requestFocus();
        }
    }

    protected boolean validate(String pText) {
        return pText.matches(input.getProperties().getValidate());
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