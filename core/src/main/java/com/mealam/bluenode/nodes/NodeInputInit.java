package com.mealam.bluenode.nodes;

import com.google.gson.JsonObject;
import com.mealam.bluenode.json.JSONParser;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;

import java.util.List;

public class NodeInputInit {

    public static List<JsonObject> allInputs;

    public static void loadInputs(String pFolderPath) {
        allInputs = JSONParser.parseJsonFiles(pFolderPath);
        BaseLogger.log(BaseLogLevel.INFO, "All Inputs: " + allInputs);
    }
}
