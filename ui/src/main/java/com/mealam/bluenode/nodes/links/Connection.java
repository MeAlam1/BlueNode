package com.mealam.bluenode.nodes.links;

import com.mealam.bluenode.nodes.Node;
import javafx.scene.shape.Line;

public record Connection(Line line, Node sourceNode, double sourceOffsetX, double sourceOffsetY, Node targetNode,
        double targetOffsetX, double targetOffsetY) {}
