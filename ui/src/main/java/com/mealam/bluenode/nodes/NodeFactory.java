package com.mealam.bluenode.nodes;

import com.mealam.bluenode.utils.nodes.NodeIDGenerator;

public class NodeFactory {
    public static Node createNode(Node pNode) {
        if (pNode == null) {
            throw new NullPointerException("Node not found in Library");
        }
        pNode.getProperties().setId(NodeIDGenerator.generateID(pNode.getProperties().getTitle()));
        pNode.getProperties().setWidth(150);
        pNode.getProperties().setHeight(100);
        return pNode;
    }
}