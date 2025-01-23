package com.mealam.bluenode;

import com.mealam.bluenode.nodes.NodeLoader;

public class CoreController {
    public static void initializeCore() {
        NodeLoader.loadNodes("nodes");
    }
}
