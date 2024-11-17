package com.mealam.bluenode.nodes;

public class MathNode extends Node {

    public MathNode(double pX, double pY, String pId) {
        super(pX, pY, pId);
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
