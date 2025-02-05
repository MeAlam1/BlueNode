package com.mealam.bluenode;

import com.mealam.bluenode.nodes.NodeCategoryInit;
import com.mealam.bluenode.nodes.NodeInit;
import com.mealam.bluenode.nodes.NodeInputInit;
import com.mealam.bluenode.nodes.NodeOutputInit;

public class CoreController {

    public static void initializeCore() {
        NodeInit.loadNodes("nodes");
        NodeInputInit.loadInputs("data/inputs");
        NodeOutputInit.loadOutputs("data/outputs");
        NodeCategoryInit.loadNodeCategories("data/categories");
    }
}
