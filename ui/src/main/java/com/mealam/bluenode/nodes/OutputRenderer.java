package com.mealam.bluenode.nodes;

import com.mealam.bluenode.components.fields.OutputField;
import com.mealam.bluenode.events.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.nodes.components.output.Output;

public class OutputRenderer {

    public static void render(NodePane pParentNodePane, Output pOutput, Node pNode, NodePane pNodePane, double pX, double pY, double pWidth, double pHeight) {
        pOutput.getProperties().setX(pX + CanvasDragHandler.getTranslateX());
        pOutput.getProperties().setY(pY + CanvasDragHandler.getTranslateY());

        OutputField ouputField = new OutputField(pOutput, pNode, pNodePane);

        pParentNodePane.getChildren().add(ouputField);
    }
}
