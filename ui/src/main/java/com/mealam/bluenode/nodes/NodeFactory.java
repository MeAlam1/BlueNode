package com.mealam.bluenode.nodes;

import com.mealam.bluenode.utils.nodes.NodeIDGenerator;

public class NodeFactory {

    public static Node createNode(Node pNode) {
        if (pNode == null) {
            throw new NullPointerException("Node not found in Library");
        }
        pNode.getProperties().setId(NodeIDGenerator.generateID(pNode.getProperties().getTitle()));
        pNode.getProperties().setMinWidth(150);
        pNode.getProperties().setMinHeight(130);
        pNode.getProperties().setWidth(pNode.getProperties().getMinWidth());
        pNode.getProperties().setHeight(pNode.getProperties().getMinHeight());
        pNode.getProperties().setInputNode("-");
        pNode.getProperties().setOutputNode("-");
        return pNode;
    }
}
