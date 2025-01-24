package com.mealam.bluenode;

import com.mealam.bluenode.nodes.NodeInit;

public class CoreController {

    public static void initializeCore() {
        NodeInit.loadNodes("nodes");
    }
}
