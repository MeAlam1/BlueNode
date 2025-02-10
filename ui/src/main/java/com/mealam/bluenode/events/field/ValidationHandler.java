package com.mealam.bluenode.events.field;

import com.mealam.bluenode.nodes.components.input.Input;

public class ValidationHandler {

    private final Input input;

    public ValidationHandler(Input pInput) {
        this.input = pInput;
    }

    public boolean validate(String pText) {
        return pText.matches(input.getProperties().getValidate());
    }
}
