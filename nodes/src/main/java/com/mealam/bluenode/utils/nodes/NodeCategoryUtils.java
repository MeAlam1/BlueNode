package com.mealam.bluenode.utils.nodes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mealam.bluenode.nodes.NodeCategoryInit;
import com.mealam.bluenode.utils.json.JSONUtils;
import java.util.Arrays;
import java.util.List;

public class NodeCategoryUtils {

    public static String getCategoryData(String pCategory, String pData) {
        for (JsonObject category : NodeCategoryInit.allNodeCategories) {
            if (category.has(pCategory)) {
                JsonElement typeElement = category.get(pCategory);
                return JSONUtils.extractDataFromElement(typeElement, pData);
            }
        }
        return "";
    }

    public static List<String> getCategories(String pCategory) {
        return Arrays.stream(pCategory.split("/"))
                .map(String::trim)
                .toList();
    }
}
