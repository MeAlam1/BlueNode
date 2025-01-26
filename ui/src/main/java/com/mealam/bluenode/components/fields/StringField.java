package com.mealam.bluenode.components.fields;

import com.mealam.bluenode.nodes.components.input.Input;
import com.mealam.bluenode.components.fields.base.InputField;

public class StringField extends InputField {
    public StringField(Input pInput) {
        super(pInput);
    }

    @Override
    protected boolean validate(String text) {
        return true;
    }
}
