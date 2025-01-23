package com.mealam.bluenode;

import com.google.gson.JsonObject;
import com.mealam.bluenode.json.JSONParser;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import java.util.List;

public class TestLoader {

    public static List<JsonObject> AllData = null;

    public static void loadVariants(String pFolderPath) {
        List<JsonObject> allJsonData = JSONParser.parseJsonFiles(pFolderPath);
        AllData = allJsonData;
        BaseLogger.log(BaseLogLevel.INFO, "All data: " + AllData);
    }
}
