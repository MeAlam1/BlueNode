package com.mealam.bluenode.nodes.categories;

import com.mealam.bluenode.nodes.Node;

public class MathNode extends Node {

    public MathNode(double pWidth, double pHeight, double pX, double pY, String pId) {
        super(pWidth, pHeight, pX, pY, pId);
    }

    @Override
    public String getType() {
        return "Math";
    }

    @Override
    public String getColor() {
        return "#FF5733";
    }
}
