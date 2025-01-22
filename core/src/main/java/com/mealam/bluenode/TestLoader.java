package com.mealam.bluenode;

import com.google.gson.JsonObject;
import com.mealam.bluenode.json.JSONLoader;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;

public class TestLoader {
    public static JsonObject AllData = new JsonObject();

    public static void loadVariants(String pFolderPath) {
        AllData = JSONLoader.loadJson(pFolderPath);
        BaseLogger.log(BaseLogLevel.INFO, "All data: " + AllData);
    }
}
