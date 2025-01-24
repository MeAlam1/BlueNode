package com.mealam.bluenode.nodes.components.output.category;

import java.util.HashMap;
import java.util.Map;

public class OutputCategoryManager {

    private static final Map<String, OutputCategoryData> CATEGORY_DATA_MAP = new HashMap<>();

    public static void initCategoryData() {
        CATEGORY_DATA_MAP.putIfAbsent("number", new OutputCategoryData("#00FF00"));
    }

    public static OutputCategoryData getCategoryData(String category) {
        return CATEGORY_DATA_MAP.getOrDefault(category, new OutputCategoryData("#FFFFFF"));
    }
}
