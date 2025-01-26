package com.mealam.bluenode.components.fields;

import com.mealam.bluenode.components.fields.base.InputField;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.components.input.Input;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class IntField extends InputField {

    public IntField(GraphicsContext pGraphicsContext, Pane pPane, Input pInput, Node pNode) {
        super(pGraphicsContext, pPane, pInput, pNode);
    }

    @Override
    protected boolean validate(String text) {
        return text.matches("[0-9]*");
    }
}
