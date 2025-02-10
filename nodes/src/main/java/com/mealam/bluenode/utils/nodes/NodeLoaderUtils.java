package com.mealam.bluenode.utils.nodes;

import com.google.gson.JsonObject;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.NodeInit;
import java.util.ArrayList;
import java.util.List;

public class NodeLoaderUtils {

    public static List<Node> getAllNodes() {
        List<Node> nodes = new ArrayList<>();
        for (JsonObject nodeJson : NodeInit.allNodes) {
            for (String key : nodeJson.keySet()) {
                nodes.add(Node.fromJson(nodeJson.getAsJsonArray(key), 0, 0));
            }
        }
        return nodes;
    }
}
