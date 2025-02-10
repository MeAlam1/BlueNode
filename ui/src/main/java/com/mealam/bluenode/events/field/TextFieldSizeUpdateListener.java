package com.mealam.bluenode.events.field;

import com.mealam.bluenode.components.fields.InputField;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.NodePane;
import com.mealam.bluenode.nodes.components.input.Input;
import com.mealam.bluenode.utils.nodes.layout.NodeLayoutManager;
import javafx.beans.property.StringProperty;
import javafx.scene.text.Text;

public class TextFieldSizeUpdateListener {

    StringProperty textProperty;
    Node pNode;
    NodePane nodePane;
    InputField inputField;
    Input input;

    public TextFieldSizeUpdateListener(StringProperty pStringProperty, Node pNode, NodePane pNodePane, InputField pInputField, Input pInput) {
        this.textProperty = pStringProperty;
        this.pNode = pNode;
        this.nodePane = pNodePane;
        this.inputField = pInputField;
        this.input = pInput;
        addListeners();
    }

    public void addListeners() {
        assert textProperty != null;
        textProperty.addListener((observable, oldValue, newValue) -> {
            updateFieldSize(pNode, nodePane, newValue);
        });
    }

    private void updateFieldSize(Node pNode, NodePane pNodePane, String pNewValue) {
        input.getProperties().setValue(pNewValue);

        double newWidth = getNewWidth(pNewValue);
        inputField.setPrefWidth(newWidth);
        input.getProperties().setWidth(newWidth);

        double minNodeWidth = pNode.getProperties().getMinWidth();
        NodeLayoutManager.updateNodeWidth(pNode, minNodeWidth);

        pNodePane.updateSize();
    }

    private double getNewWidth(String pNewValue) {
        Text text = new Text(pNewValue);
        text.setFont(inputField.getFont());
        double textWidth = text.getLayoutBounds().getWidth();
        double padding = inputField.getInsets().getLeft() + inputField.getInsets().getRight();
        return Math.max(40, textWidth + padding);
    }
}
