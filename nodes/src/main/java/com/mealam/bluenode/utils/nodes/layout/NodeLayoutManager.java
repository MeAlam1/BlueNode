package com.mealam.bluenode.utils.nodes.layout;

import com.mealam.bluenode.nodes.Node;

public class NodeLayoutManager {

    public static void updateNodeWidth(Node pNode, double pMinNodeWidth, double pWidthIncrement) {
        double maxWidth = pNode.getProperties().getInputs().stream()
                .mapToDouble(input -> input.getProperties().getWidth())
                .max()
                .orElse(0);

        double calculatedWidth = Math.max(pMinNodeWidth, pMinNodeWidth + maxWidth + pWidthIncrement);
        pNode.getProperties().setWidth(calculatedWidth);
    }
}
