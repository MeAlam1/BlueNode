package com.mealam.bluenode.utils.nodes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class NodeIDGenerator {

    private static final ConcurrentHashMap<String, AtomicInteger> counters = new ConcurrentHashMap<>();

    /**
     * Generates a unique ID for a node based on its type, an incrementing counter, and a human-readable timestamp.
     *
     * @param pNodeType The type of the node (e.g., "MathNode", "LogicNode").
     * @return A unique ID in the format: NodeType-<Counter>-<ReadableTimestamp>.
     */
    public static String generateID(String pNodeType) {
        counters.putIfAbsent(pNodeType, new AtomicInteger(0));
        int uniqueNumber = counters.get(pNodeType).incrementAndGet();

        String timestamp = new SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new Date());

        return pNodeType + "-" + uniqueNumber + "-" + timestamp;
    }
}
