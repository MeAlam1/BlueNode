package com.mealam.bluenode.nodes;

import com.google.gson.JsonObject;
import com.mealam.bluenode.json.JSONParser;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import java.util.List;

public class NodeCategoryInit {

    public static List<JsonObject> allNodeCategories;

    public static void loadNodeCategories(String pFolderPath) {
        allNodeCategories = JSONParser.parseJsonFiles(pFolderPath);
        BaseLogger.log(BaseLogLevel.INFO, "All Node Categories: " + allNodeCategories);
    }
}
