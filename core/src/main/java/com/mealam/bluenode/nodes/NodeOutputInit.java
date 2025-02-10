package com.mealam.bluenode.nodes;

import com.google.gson.JsonObject;
import com.mealam.bluenode.json.JSONParser;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import java.util.List;

public class NodeOutputInit {

    public static List<JsonObject> allOutputs;

    public static void loadOutputs(String pFolderPath) {
        allOutputs = JSONParser.parseJsonFiles(pFolderPath);
        BaseLogger.log(BaseLogLevel.INFO, "All Outputs: " + allOutputs);
    }
}
