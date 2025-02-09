package com.mealam.bluenode.components.fields;

import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.NodePane;
import com.mealam.bluenode.nodes.components.input.Input;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class InputField extends TextField {

    private final Input input;

    public InputField(Input pInput, Node pNode, NodePane nodePane) {
        this.input = pInput;

        configureField();

        addEventListeners(pNode, nodePane);
        addFocusListeners();
    }

    private void configureField() {
        this.setText(input.getProperties().getValue());
        this.setPromptText(input.getProperties().getName());
        this.getStyleClass().add("input-field");
        this.getStyleClass().add(input.getProperties().getCss());
    }

    private void addEventListeners(Node pNode, NodePane nodePane) {
        textProperty().addListener((observable, oldValue, newValue) -> {
            updateFieldSize(pNode, nodePane, newValue);
        });
    }

    private void updateFieldSize(Node pNode, NodePane nodePane, String newValue) {
        input.getProperties().setValue(newValue);
        Text text = new Text(newValue);
        text.setFont(this.getFont());
        double textWidth = text.getLayoutBounds().getWidth();
        double padding = this.getInsets().getLeft() + this.getInsets().getRight();
        double newWidth = Math.max(40, textWidth + padding);
        pNode.updateSize();
        this.setPrefWidth(newWidth);
        input.getProperties().setWidth(newWidth);
        input.getProperties().setValue(newValue);
        nodePane.updateSize();

    }

    private void addFocusListeners() {
        this.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.ESCAPE) {
                this.getParent().requestFocus();
            }
        });
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