package com.mealam.bluenode.utils.nodes;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.qual.NonNull;

public class NodeIDGenerator {

    private static final ConcurrentHashMap<String, AtomicInteger> counters = new ConcurrentHashMap<>();

    public static String generateID(@NonNull String pNodeType) {
        counters.putIfAbsent(pNodeType, new AtomicInteger(0));
        int uniqueNumber = counters.get(pNodeType).incrementAndGet();
        String timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss-SSS").format(new Date());
        String originalID = pNodeType + uniqueNumber + "_" + timestamp;
        return Base64.getEncoder().encodeToString(originalID.getBytes());
    }
}
