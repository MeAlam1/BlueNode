package com.mealam.bluenode.utils.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JSONUtils {

    public static String extractDataFromElement(JsonElement element, String key) {
        if (element.isJsonObject()) {
            JsonObject jsonObject = element.getAsJsonObject();
            if (jsonObject.has(key)) {
                return jsonObject.get(key).getAsString();
            }
            for (String innerKey : jsonObject.keySet()) {
                JsonElement innerElement = jsonObject.get(innerKey);
                String result = extractDataFromElement(innerElement, key);
                if (result != null) return result;
            }
        } else if (element.isJsonArray()) {
            JsonArray jsonArray = element.getAsJsonArray();
            for (JsonElement arrayElement : jsonArray) {
                String result = extractDataFromElement(arrayElement, key);
                if (result != null) return result;
            }
        }
        return "";
    }
}
