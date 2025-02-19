package com.mealam.bluenode.nodes.links;

import com.mealam.bluenode.nodes.Node;
import javafx.scene.shape.Line;

public record Connection(Line pLine, Node pSourceNode, double pSourceOffsetX, double pSourceOffsetY, Node pTargetNode,
        double pTargetOffsetX, double pTargetOffsetY) {}
