package com.mealam.bluenode.nodes;

import com.google.gson.JsonObject;
import com.mealam.bluenode.json.JSONParser;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;

import java.util.List;

public class NodeInit {

    public static List<JsonObject> allNodes;

    public static void loadNodes(String pFolderPath) {
        allNodes = JSONParser.parseJsonFiles(pFolderPath);
        BaseLogger.log(BaseLogLevel.INFO, "All Nodes: " + allNodes);
    }
}
