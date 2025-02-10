package com.mealam.bluenode.utils.nodes.input;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mealam.bluenode.nodes.NodeInputInit;
import com.mealam.bluenode.utils.json.JSONUtils;

public class InputCategoryUtils {

    public static String getInputCategoryData(String pInputType, String pData) {
        for (JsonObject input : NodeInputInit.allInputs) {
            if (input.has(pInputType)) {
                JsonElement typeElement = input.get(pInputType);
                return JSONUtils.extractDataFromElement(typeElement, pData);
            }
        }
        return "";
    }
}
