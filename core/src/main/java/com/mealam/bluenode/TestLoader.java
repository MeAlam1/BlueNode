package com.mealam.bluenode;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mealam.bluenode.json.JSONParser;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;

import java.util.HashMap;
import java.util.Map;

public class TestLoader extends JSONParser {
    public static final Map<String, JsonObject> AllData = new HashMap<>();

    private static final TestLoader LOADER = new TestLoader();

    public static void loadVariants(String pFolderPath) {
        // TODO: NOT HARDCODE IT
        LOADER.loadData("nodes/src/main/resources/" + pFolderPath);
        AllData.putAll(LOADER.getDataMap());
        parseJSON(LOADER.getMergedJsonObject());
        BaseLogger.log(BaseLogLevel.INFO, "All data: " + AllData);
    }

    private static void parseJSON(JsonObject pJsonObject) {
        for (Map.Entry<String, JsonElement> entry : pJsonObject.entrySet()) {
            if (entry.getValue().isJsonObject()) {
                AllData.putIfAbsent(entry.getKey(), entry.getValue().getAsJsonObject());
            } else if (entry.getValue().isJsonArray()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.add(entry.getKey(), entry.getValue().getAsJsonArray());
                AllData.putIfAbsent(entry.getKey(), jsonObject);
            }
        }
    }
}
