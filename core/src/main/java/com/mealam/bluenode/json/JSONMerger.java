package com.mealam.bluenode.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;

import java.util.Map;

public class JSONMerger {
    
    public void mergeJsonObjects(JsonObject pTarget, JsonObject pSource) {
        for (Map.Entry<String, JsonElement> entry : pSource.entrySet()) {
            String key = entry.getKey();
            JsonElement sourceElement = entry.getValue();

            if (pTarget.has(key)) {
                JsonElement targetElement = pTarget.get(key);

                if (targetElement.isJsonArray() && sourceElement.isJsonArray()) {
                    JsonArray targetArray = targetElement.getAsJsonArray();
                    JsonArray sourceArray = sourceElement.getAsJsonArray();

                    for (JsonElement element : sourceArray) {
                        targetArray.add(element);
                    }

                    BaseLogger.log(BaseLogLevel.INFO, "Merged array for key: " + key);
                } else {
                    pTarget.add(key, sourceElement);
                    BaseLogger.log(BaseLogLevel.WARNING, "Overwriting value for key: " + key);
                }
            } else {
                pTarget.add(key, sourceElement);
                BaseLogger.log(BaseLogLevel.SUCCESS, "Added new key: " + key);
            }
        }
    }
}
