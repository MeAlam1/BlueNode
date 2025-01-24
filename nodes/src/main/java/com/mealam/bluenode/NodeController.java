package com.mealam.bluenode;

import com.mealam.bluenode.nodes.category.NodeCategoryManager;
import com.mealam.bluenode.nodes.components.input.category.InputCategoryManager;
import com.mealam.bluenode.nodes.components.output.category.OutputCategoryManager;

public class NodeController {

    public static void initializeNodes() {
        NodeCategoryManager.initCategoryData();
        InputCategoryManager.initCategoryData();
        OutputCategoryManager.initCategoryData();
    }
}
