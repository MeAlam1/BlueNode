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

    public TextFieldSizeUpdateListener(StringProperty stringProperty, Node pNode, NodePane nodePane, InputField inputField, Input input) {
        this.textProperty = stringProperty;
        this.pNode = pNode;
        this.nodePane = nodePane;
        this.inputField = inputField;
        this.input = input;
        addListeners();
    }

    public void addListeners() {
        assert textProperty != null;
        textProperty.addListener((observable, oldValue, newValue) -> {
            updateFieldSize(pNode, nodePane, newValue);
        });
    }

    private void updateFieldSize(Node pNode, NodePane nodePane, String newValue) {
        input.getProperties().setValue(newValue);

        double newWidth = getNewWidth(newValue);
        inputField.setPrefWidth(newWidth);
        input.getProperties().setWidth(newWidth);

        double minNodeWidth = pNode.getProperties().getMinWidth();
        NodeLayoutManager.updateNodeWidth(pNode, minNodeWidth, 20);

        nodePane.updateSize();
    }

    private double getNewWidth(String newValue) {
        Text text = new Text(newValue);
        text.setFont(inputField.getFont());
        double textWidth = text.getLayoutBounds().getWidth();
        double padding = inputField.getInsets().getLeft() + inputField.getInsets().getRight();
        return Math.max(40, textWidth + padding);
    }
}
