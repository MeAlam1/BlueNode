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

    public static JsonObject loadJson(Object pFilePath) {
        String resourcePath = pFilePath.toString();
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
        } catch (IOException pIoException) {
            BaseLogger.log(BaseLogLevel.ERROR, "Failed to load JSON resource: " + pFilePath, pIoException);
        } catch (URISyntaxException pURISyntaxException) {
            BaseLogger.log(BaseLogLevel.ERROR, "Invalid resource path: " + pFilePath, pURISyntaxException);
        }
        return null;
    }

    private static JsonObject loadJsonFromFile(File pFile) throws IOException {
        if (!pFile.exists() || !pFile.canRead()) {
            BaseLogger.log(BaseLogLevel.ERROR,
                    !pFile.exists() ? "File not found: " + pFile : "File not readable: " + pFile);
            return new JsonObject();
        }

        try (InputStream inputStream = new FileInputStream(pFile);
                InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return loadJsonFromReader(reader);
        } catch (Exception pException) {
            BaseLogger.log(BaseLogLevel.ERROR, "Failed to load JSON file: " + pFile, pException);
            return new JsonObject();
        }
    }

    private static JsonObject loadJsonFromJar(URL pResourceUrl, String pResourcePath) throws IOException {
        String jarFilePath = pResourceUrl.getPath().substring(5, pResourceUrl.getPath().indexOf("!"));
        try (ZipFile zipFile = new ZipFile(new File(jarFilePath))) {
            ZipEntry entry = zipFile.getEntry(pResourcePath);
            if (entry == null) {
                BaseLogger.log(BaseLogLevel.ERROR, "[Jar] Resource not found: " + pResourcePath);
                return new JsonObject();
            }

            try (InputStream inputStream = zipFile.getInputStream(entry);
                    InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                return loadJsonFromReader(reader);
            } catch (Exception pException) {
                BaseLogger.log(BaseLogLevel.ERROR, "(0)[Jar] Failed to load JSON resource: " + pResourcePath, pException);
                return new JsonObject();
            }
        } catch (Exception pException) {
            BaseLogger.log(BaseLogLevel.ERROR, "(1)[Jar] Failed to load JSON resource: " + pResourcePath, pException);
            return new JsonObject();
        }
    }

    public static JsonObject loadJsonFromReader(Reader pReader) {
        return gson.fromJson(pReader, JsonObject.class);
    }
}
