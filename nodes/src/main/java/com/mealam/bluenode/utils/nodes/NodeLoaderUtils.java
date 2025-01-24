package com.mealam.bluenode.utils.nodes;

import com.google.gson.JsonObject;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.NodeLoader;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;

public class NodeLoaderUtils {
    public static Node getNodeByKey(String pKey, double pX, double pY) {
        for (JsonObject nodeJson : NodeLoader.allNodes) {
            for (String key : nodeJson.keySet()) {
                if (key.equals(pKey)) {
                    return Node.fromJson(nodeJson.getAsJsonArray(key), pX, pY);
                }
            }
        }

        BaseLogger.log(BaseLogLevel.ERROR, "Node with key " + pKey + " not found");
        return null;
    }

}
