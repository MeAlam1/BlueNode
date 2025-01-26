package com.mealam.bluenode.nodes.input;

import com.mealam.bluenode.nodes.components.input.Input;
import com.mealam.bluenode.nodes.input.components.InputField;

public class IntField extends InputField {
    public IntField(Input pInput) {
        super(pInput);
    }

    @Override
    protected boolean validate(String text) {
        return text.matches("[0-9]*");
    }
}
