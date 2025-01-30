package com.mealam.bluenode.nodes.category;

import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NodeCategoryHandler {

    public static List<String> getCategories(String categoryString) {
        return Arrays.stream(categoryString.split("/"))
                .map(String::trim)
                .toList();
    }


    public static void applyCategoryStyle(Node node) {
        List<String> categories = getCategories(node.getProperties().getCategory());
        BaseLogger.log(BaseLogLevel.INFO, "Applying category style to node with categories: " + categories);
        NodeCategoryData latestValidCategory = null;

        for (String category : categories) {
            NodeCategoryData categoryData = NodeCategoryManager.getCategoryData(category);
            if (categoryData != null && !Objects.equals(categoryData.color(), "#FFFFFF")) {
                latestValidCategory = categoryData;
            }
        }

        if (latestValidCategory != null) {
            node.getProperties().setColor(latestValidCategory.color());
        }
    }
}
