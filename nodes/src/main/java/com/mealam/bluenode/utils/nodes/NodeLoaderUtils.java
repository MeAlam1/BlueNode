package com.mealam.bluenode.utils.nodes;

import com.google.gson.JsonObject;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.NodeInit;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class NodeLoaderUtils {

    public static Node getNodeByKey(@NonNull String pKey, double pX, double pY) {
        for (JsonObject nodeJson : NodeInit.allNodes) {
            for (String key : nodeJson.keySet()) {
                if (key.equals(pKey)) {
                    return Node.fromJson(nodeJson.getAsJsonArray(key), pX, pY);
                }
            }
        }
        /* IDEA:
            * If the node is not found, log an error message.
            * Return a hidden Node that tells the user that the node is not found.
            * Also create a system that allows the user to send a report to the developers.
            * This will help the developers to know what nodes are missing/broken and add/fix them in the next update.
         */
        BaseLogger.log(BaseLogLevel.ERROR, "Node with key " + pKey + " not found");
        return null;
    }

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
