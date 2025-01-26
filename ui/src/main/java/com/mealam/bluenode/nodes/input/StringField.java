package com.mealam.bluenode.nodes.input;

import com.mealam.bluenode.nodes.components.input.Input;
import com.mealam.bluenode.nodes.input.components.InputField;

public class StringField extends InputField {
    public StringField(Input pInput) {
        super(pInput);
    }

    @Override
    protected boolean validate(String text) {
        return true;
    }
}
