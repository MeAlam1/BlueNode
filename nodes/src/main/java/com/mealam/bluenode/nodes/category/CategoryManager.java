package com.mealam.bluenode.nodes.category;

import java.util.HashMap;
import java.util.Map;

public class CategoryManager {

    private static final Map<String, CategoryData> CATEGORY_DATA_MAP = new HashMap<>();

    static {
        CATEGORY_DATA_MAP.put("testCategory", new CategoryData("#FF5733"));
    }

    public static CategoryData getCategoryData(String category) {
        return CATEGORY_DATA_MAP.getOrDefault(category, new CategoryData("#FFFFFF"));
    }
}
