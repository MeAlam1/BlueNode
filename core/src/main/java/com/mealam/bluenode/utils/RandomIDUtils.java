package com.mealam.bluenode.utils;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class RandomIDUtils {

    public static String generateID(@NonNull String pFileName) {
        String timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss-SSS").format(new Date());
        String originalID = pFileName + "_" + timestamp;
        return Base64.getEncoder().encodeToString(originalID.getBytes());
    }

    public static String decryptID(String pEncodedID) {
        byte[] decodedBytes = Base64.getDecoder().decode(pEncodedID);
        return new String(decodedBytes);
    }
}
