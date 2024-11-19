package com.mealam.bluenode.nodes;

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
