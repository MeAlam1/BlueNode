package com.mealam.bluenode.components.fields;

import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.NodePane;
import com.mealam.bluenode.nodes.components.output.Output;
import javafx.scene.shape.Circle;

public class OutputField extends Circle {

    private final Output output;

    public OutputField(Output pOutput, Node pNode, NodePane pNodePane) {
        this.output = pOutput;

        configureField();
    }

    private void configureField() {
        this.getStyleClass().add("output-field");
        this.getStyleClass().add(output.getProperties().getCss());

        this.setRadius(10);
        this.setLayoutX(output.getProperties().getX());
        this.setLayoutY(output.getProperties().getY());
    }
}
