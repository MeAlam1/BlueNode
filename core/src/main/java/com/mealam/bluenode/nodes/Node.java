package com.mealam.bluenode.nodes;

public abstract class Node {

    private final double pX;
    private final double pY;
    private final String id;

    protected Node(double pX, double pY, String pId) {
        this.pX = pX;
        this.pY = pY;
        this.id = pId;
    }

    public double getPX() {
        return pX;
    }

    public double getPY() {
        return pY;
    }

    public String getId() {
        return id;
    }

    public abstract String getType();

    public abstract String getColor();
}
