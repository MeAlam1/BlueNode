package com.mealam.bluenode;

import com.mealam.bluenode.nodes.category.NodeCategoryManager;

public class NodeController {

    public static void initializeNodes() {
        NodeCategoryManager.initCategoryData();
    }
}
