package com.mealam.bluenode.nodes.components;

import com.mealam.bluenode.interfaces.nodes.NodeComponent;

public class InputComponent implements NodeComponent {

    private final String label;

    public InputComponent(String pLabel) {
        this.label = pLabel;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public double getHeight() {
        return 25;
    }
}
