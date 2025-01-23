package com.mealam.bluenode.json;

import com.google.gson.JsonObject;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipFile;

public class JSONParser {

    public static List<JsonObject> parseJsonFiles(String folderPath) {
        List<JsonObject> jsonObjects = new ArrayList<>();
        try {
            URL resourceUrl = JSONParser.class.getClassLoader().getResource(folderPath);
            if (resourceUrl == null) {
                BaseLogger.log(BaseLogLevel.ERROR, "Resource not found: " + folderPath);
                return jsonObjects;
            }

            if ("jar".equals(resourceUrl.getProtocol())) {
                processJarDirectory(resourceUrl, folderPath, jsonObjects);
            } else {
                File folder = new File(resourceUrl.toURI());
                if (folder.exists() && folder.isDirectory()) {
                    processDirectory(folder, jsonObjects);
                } else {
                    BaseLogger.log(BaseLogLevel.ERROR, "Invalid folder path: " + folderPath);
                }
            }
        } catch (Exception e) {
            BaseLogger.log(BaseLogLevel.ERROR, "Error processing folder: " + folderPath, e);
        }
        return jsonObjects;
    }

    private static void processDirectory(File directory, List<JsonObject> jsonObjects) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    processDirectory(file, jsonObjects);
                } else if (file.isFile() && file.getName().endsWith(".json")) {
                    JSONMerger.addJsonFromFile(file, jsonObjects);
                }
            }
        }
    }

    private static void processJarDirectory(URL jarUrl, String folderPath, List<JsonObject> jsonObjects) throws IOException {
        String jarFilePath = jarUrl.getPath().substring(5, jarUrl.getPath().indexOf("!"));
        try (ZipFile zipFile = new ZipFile(new File(jarFilePath))) {
            zipFile.stream()
                    .filter(entry -> entry.getName().startsWith(folderPath) && entry.getName().endsWith(".json"))
                    .forEach(entry -> {
                        try {
                            JSONMerger.addJsonFromJarEntry(zipFile, entry, jsonObjects);
                        } catch (IOException e) {
                            BaseLogger.log(BaseLogLevel.ERROR, "Error reading entry from JAR: " + entry.getName(), e);
                        }
                    });
        }
    }
}
