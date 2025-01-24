package com.mealam.bluenode.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JSONLoader {

    private static final Gson gson = new Gson();

    public static JsonObject loadJson(Object filePath) {
        String resourcePath = filePath.toString();
        try {
            URL resourceUrl = JSONLoader.class.getClassLoader().getResource(resourcePath);
            if (resourceUrl == null) {
                BaseLogger.log(BaseLogLevel.ERROR, "Resource not found: " + resourcePath);
                return new JsonObject();
            }

            if ("jar".equals(resourceUrl.getProtocol())) {
                return loadJsonFromJar(resourceUrl, resourcePath);
            } else {
                return loadJsonFromFile(new File(resourceUrl.toURI()));
            }
        } catch (IOException exception) {
            BaseLogger.log(BaseLogLevel.ERROR, "Failed to load JSON resource: " + filePath, exception);
            throw new RuntimeException("Failed to load JSON resource: " + filePath, exception);
        } catch (URISyntaxException e) {
            BaseLogger.log(BaseLogLevel.ERROR, "Invalid resource path: " + filePath, e);
            throw new RuntimeException("Invalid resource path: " + filePath, e);
        }
    }

    private static JsonObject loadJsonFromFile(File file) throws IOException {
        if (!file.exists() || !file.canRead()) {
            BaseLogger.log(BaseLogLevel.ERROR,
                    !file.exists() ? "File not found: " + file : "File not readable: " + file);
            return new JsonObject();
        }

        try (InputStream inputStream = new FileInputStream(file);
                InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return loadJsonFromReader(reader);
        }
    }

    private static JsonObject loadJsonFromJar(URL resourceUrl, String resourcePath) throws IOException {
        String jarFilePath = resourceUrl.getPath().substring(5, resourceUrl.getPath().indexOf("!"));
        try (ZipFile zipFile = new ZipFile(new File(jarFilePath))) {
            ZipEntry entry = zipFile.getEntry(resourcePath);
            if (entry == null) {
                BaseLogger.log(BaseLogLevel.ERROR, "[Jar] Resource not found: " + resourcePath);
                return new JsonObject();
            }

            try (InputStream inputStream = zipFile.getInputStream(entry);
                    InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                return loadJsonFromReader(reader);
            }
        }
    }

    public static JsonObject loadJsonFromReader(Reader reader) {
        return gson.fromJson(reader, JsonObject.class);
    }
}
