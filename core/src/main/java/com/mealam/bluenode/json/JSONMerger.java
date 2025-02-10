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

    public static void addJsonFromFile(File pFile, List<JsonObject> pJsonObjects) {
        JsonObject jsonObject = JSONLoader.loadJson(pFile.getAbsolutePath());
        if (jsonObject != null) {
            pJsonObjects.add(jsonObject);
            BaseLogger.log(BaseLogLevel.SUCCESS, "[File] Successfully loaded JSON: " + pFile.getPath());
        } else {
            BaseLogger.log(BaseLogLevel.WARNING, "[File] Failed to load JSON: " + pFile.getPath());
        }
    }

    public static void addJsonFromJarEntry(ZipFile pZipFile, ZipEntry pZipEntry, List<JsonObject> pJsonObjects) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(pZipFile.getInputStream(pZipEntry))) {
            JsonObject jsonObject = JSONLoader.loadJsonFromReader(reader);
            if (jsonObject != null) {
                pJsonObjects.add(jsonObject);
                BaseLogger.log(BaseLogLevel.SUCCESS, "[Jar] Successfully loaded JSON: " + pZipEntry.getName());
            } else {
                BaseLogger.log(BaseLogLevel.WARNING, "[Jar] Failed to load JSON: " + pZipEntry.getName());
            }
        } catch (IOException pIoException) {
            BaseLogger.log(BaseLogLevel.ERROR, "[Jar] Failed to load JSON: " + pZipEntry.getName(), pIoException);
        }
    }
}
