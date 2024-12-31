package com.mealam.bluenode.nodes;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {

    private final double x;
    private final double y;
    private final String id;
    private final List<NodeComponent> components = new ArrayList<>();

    protected Node(double pX, double pY, String pId) {
        this.x = pX;
        this.y = pY;
        this.id = pId;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getId() {
        return id;
    }

    public List<NodeComponent> getComponents() {
        return components;
    }

    public void addComponent(NodeComponent pComponent) {
        components.add(pComponent);
    }

    public abstract String getType();

    public abstract String getColor();
}
