package com.mealam.bluenode.components.fields;

import com.mealam.bluenode.nodes.components.input.Input;
import com.mealam.bluenode.components.fields.base.InputField;

public class IntField extends InputField {
    public IntField(Input pInput) {
        super(pInput);
    }

    @Override
    protected boolean validate(String text) {
        return text.matches("[0-9]*");
    }
}
