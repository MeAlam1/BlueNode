package com.mealam.bluenode.nodes;

public abstract class Node {

    private final double X;
    private final double Y;
    private final String id;

    protected Node(double pX, double pY, String pId) {
        this.X = pX;
        this.Y = pY;
        this.id = pId;
    }

    public double getPX() {
        return X;
    }

    public double getPY() {
        return Y;
    }

    public String getId() {
        return id;
    }

    public abstract String getType();

    public abstract String getColor();
}
