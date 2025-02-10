package com.mealam.bluenode.utils.nodes;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.qual.NonNull;

public class NodeIDGenerator {

    private static final ConcurrentHashMap<String, AtomicInteger> counters = new ConcurrentHashMap<>();

    /**
     * Generates a unique ID for a node based on its type, an incrementing counter, and a human-readable timestamp.
     *
     * @param pNodeType The type of the node (e.g., "MathNode", "LogicNode").
     * @return A unique ID in the format: NodeType-<Counter>-<ReadableTimestamp>, Base64 encoded.
     */
    public static String generateID(@NonNull String pNodeType) {
        counters.putIfAbsent(pNodeType, new AtomicInteger(0));
        int uniqueNumber = counters.get(pNodeType).incrementAndGet();
        String timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss-SSS").format(new Date());
        String originalID = pNodeType + uniqueNumber + "_" + timestamp;
        return Base64.getEncoder().encodeToString(originalID.getBytes());
    }

    /**
     * Decrypts the Base64 encoded node ID back to the original format.
     *
     * @param pEncodedID The Base64 encoded ID.
     * @return The original node ID string.
     */
    public static String decryptID(String pEncodedID) {
        byte[] decodedBytes = Base64.getDecoder().decode(pEncodedID);
        return new String(decodedBytes);
    }
}
