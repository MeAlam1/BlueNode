package com.mealam.bluenode.utils.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JSONUtils {

    public static String extractDataFromElement(JsonElement pElement, String pKey) {
        if (pElement.isJsonObject()) {
            JsonObject jsonObject = pElement.getAsJsonObject();
            if (jsonObject.has(pKey)) {
                return jsonObject.get(pKey).getAsString();
            }
            for (String innerKey : jsonObject.keySet()) {
                JsonElement innerElement = jsonObject.get(innerKey);
                String result = extractDataFromElement(innerElement, pKey);
                if (result != null) return result;
            }
        } else if (pElement.isJsonArray()) {
            JsonArray jsonArray = pElement.getAsJsonArray();
            for (JsonElement arrayElement : jsonArray) {
                String result = extractDataFromElement(arrayElement, pKey);
                if (result != null) return result;
            }
        }
        return "";
    }
}
