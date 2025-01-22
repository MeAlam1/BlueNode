package com.mealam.bluenode.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Optional;

public class JSONLoader {

    /**
     * A {@code private static} {@link Gson} instance for parsing JSON data.
     *
     * @since 1.0.0
     */
    private static final Gson gson = new Gson();

    public JsonObject loadJson(Path filePath) {
        try {
            File file = filePath.toFile();
            if (!file.exists() || !file.canRead()) {
                BaseLogger.log(BaseLogLevel.ERROR, "File not found or not readable: " + filePath);
                return new JsonObject();
            }

            try (InputStream inputStream = new FileInputStream(file);
                 InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {

                JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
                BaseLogger.log(BaseLogLevel.SUCCESS, "Successfully loaded JSON from file: " + filePath);
                return jsonObject;
            }
        } catch (IOException exception) {
            BaseLogger.log(BaseLogLevel.ERROR, "Failed to load JSON file: " + filePath, exception);
            throw new RuntimeException("Failed to load JSON resource: " + filePath, exception);
        }
    }

}
