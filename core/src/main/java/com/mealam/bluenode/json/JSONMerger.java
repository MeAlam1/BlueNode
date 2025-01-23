package com.mealam.bluenode.json;

import com.google.gson.JsonObject;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JSONMerger {

    public static void addJsonFromFile(File file, List<JsonObject> jsonObjects) {
        JsonObject jsonObject = JSONLoader.loadJson(file.getAbsolutePath());
        if (jsonObject != null) {
            jsonObjects.add(jsonObject);
            BaseLogger.log(BaseLogLevel.SUCCESS, "Successfully loaded JSON from: " + file.getPath());
        } else {
            BaseLogger.log(BaseLogLevel.WARNING, "Failed to load JSON from: " + file.getPath());
        }
    }

    public static void addJsonFromJarEntry(ZipFile zipFile, ZipEntry entry, List<JsonObject> jsonObjects) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(zipFile.getInputStream(entry))) {
            JsonObject jsonObject = JSONLoader.loadJsonFromReader(reader);
            if (jsonObject != null) {
                jsonObjects.add(jsonObject);
                BaseLogger.log(BaseLogLevel.SUCCESS, "Successfully loaded JSON from JAR entry: " + entry.getName());
            } else {
                BaseLogger.log(BaseLogLevel.WARNING, "Failed to load JSON from JAR entry: " + entry.getName());
            }
        }
    }
}
