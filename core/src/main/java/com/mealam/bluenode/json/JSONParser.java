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

    public static List<JsonObject> parseJsonFiles(String pFolderPath) {
        List<JsonObject> jsonObjects = new ArrayList<>();
        try {
            URL resourceUrl = JSONParser.class.getClassLoader().getResource(pFolderPath);
            if (resourceUrl == null) {
                BaseLogger.log(BaseLogLevel.ERROR, "Resource not found: " + pFolderPath);
                return jsonObjects;
            }

            if ("jar".equals(resourceUrl.getProtocol())) {
                processJarDirectory(resourceUrl, pFolderPath, jsonObjects);
            } else {
                File folder = new File(resourceUrl.toURI());
                if (folder.exists() && folder.isDirectory()) {
                    processDirectory(folder, jsonObjects);
                } else {
                    BaseLogger.log(BaseLogLevel.ERROR, "Invalid folder path: " + pFolderPath);
                }
            }
        } catch (Exception pException) {
            BaseLogger.log(BaseLogLevel.ERROR, "Error processing folder: " + pFolderPath, pException);
        }
        return jsonObjects;
    }

    private static void processDirectory(File pDirectory, List<JsonObject> pJsonObjects) {
        File[] files = pDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    processDirectory(file, pJsonObjects);
                } else if (file.isFile() && file.getName().endsWith(".json")) {
                    JSONMerger.addJsonFromFile(file, pJsonObjects);
                }
            }
        }
    }

    private static void processJarDirectory(URL pJarUrl, String pFolderPath, List<JsonObject> pJsonObjects) throws IOException {
        String jarFilePath = pJarUrl.getPath().substring(5, pJarUrl.getPath().indexOf("!"));
        try (ZipFile zipFile = new ZipFile(new File(jarFilePath))) {
            zipFile.stream()
                    .filter(entry -> entry.getName().startsWith(pFolderPath) && entry.getName().endsWith(".json"))
                    .forEach(entry -> {
                        try {
                            JSONMerger.addJsonFromJarEntry(zipFile, entry, pJsonObjects);
                        } catch (IOException pIoException) {
                            BaseLogger.log(BaseLogLevel.ERROR, "[Jar] Error reading entry: " + entry.getName(), pIoException);
                        }
                    });
        }
    }
}
