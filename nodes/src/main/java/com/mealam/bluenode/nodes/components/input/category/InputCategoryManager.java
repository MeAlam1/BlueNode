package com.mealam.bluenode.nodes.components.input.category;

import java.util.HashMap;
import java.util.Map;

public class InputCategoryManager {

    private static final Map<String, InputCategoryData> CATEGORY_DATA_MAP = new HashMap<>();

    public static void initCategoryData() {
        CATEGORY_DATA_MAP.putIfAbsent("float", new InputCategoryData("float-input"));
    }

    public static InputCategoryData getCategoryData(String category) {
        return CATEGORY_DATA_MAP.getOrDefault(category, new InputCategoryData("input"));
    }
}
