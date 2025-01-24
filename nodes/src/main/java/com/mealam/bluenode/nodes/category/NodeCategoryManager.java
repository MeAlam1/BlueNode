package com.mealam.bluenode.nodes.category;

import java.util.HashMap;
import java.util.Map;

public class NodeCategoryManager {

    private static final Map<String, NodeCategoryData> CATEGORY_DATA_MAP = new HashMap<>();

    public static void initCategoryData() {
        CATEGORY_DATA_MAP.putIfAbsent("testCategory", new NodeCategoryData("#FF5733"));
    }

    public static NodeCategoryData getCategoryData(String category) {
        return CATEGORY_DATA_MAP.getOrDefault(category, new NodeCategoryData("#FFFFFF"));
    }
}
