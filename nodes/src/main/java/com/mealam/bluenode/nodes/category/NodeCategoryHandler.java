package com.mealam.bluenode.nodes.category;

import java.util.Arrays;
import java.util.List;

public class NodeCategoryHandler {

    public static List<String> getCategories(String categoryString) {
        return Arrays.stream(categoryString.split("/"))
                .map(String::trim)
                .toList();
    }
}
