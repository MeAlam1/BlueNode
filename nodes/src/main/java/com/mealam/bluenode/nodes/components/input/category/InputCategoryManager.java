package com.mealam.bluenode.nodes.components.input.category;

import java.util.HashMap;
import java.util.Map;

public class InputCategoryManager {

    private static final Map<String, InputCategoryData> CATEGORY_DATA_MAP = new HashMap<>();

    public static void initCategoryData() {
        CATEGORY_DATA_MAP.putIfAbsent("number", new InputCategoryData("#00FF00"));
    }

    public static InputCategoryData getCategoryData(String category) {
        return CATEGORY_DATA_MAP.getOrDefault(category, new InputCategoryData("#FFFFFF"));
    }
}
