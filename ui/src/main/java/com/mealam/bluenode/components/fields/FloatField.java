package com.mealam.bluenode.components.fields;

import com.mealam.bluenode.nodes.components.input.Input;
import com.mealam.bluenode.components.fields.base.InputField;

public class FloatField extends InputField {
    public FloatField(Input input) {
        super(input);
    }

    @Override
    protected boolean validate(String text) {
        return text.matches("[0-9]*\\.?[0-9]*");
    }
}
