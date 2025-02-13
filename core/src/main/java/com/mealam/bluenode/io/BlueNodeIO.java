package com.mealam.bluenode.io;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mealam.bluenode.format.BlueNodeDeserializer;
import com.mealam.bluenode.format.BlueNodeSerializer;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;

import java.io.File;
import java.io.IOException;

public class BlueNodeIO {

    private static final String FILE_EXTENSION = ".bluenode";

    public static void save(JsonObject json, String filePath) {
        try {
            String fullPath = filePath.endsWith(FILE_EXTENSION) ? filePath : filePath + FILE_EXTENSION;
            BlueNodeSerializer.saveAsBlueNode(json.toString(), fullPath);
            //BaseLogger.log(BaseLogLevel.SUCCESS, "Saved to: " + fullPath);
        } catch (IOException e) {
            throw new RuntimeException("Error saving file: " + e.getMessage());
        }
    }

    public static JsonObject load(String filePath) {
        try {
            if (!filePath.endsWith(FILE_EXTENSION)) {
                filePath = filePath + FILE_EXTENSION;
            }
            File file = new File(filePath);
            if (!file.exists()) {
                if (file.createNewFile()) {
                    BaseLogger.log(BaseLogLevel.WARNING, "File did not exist, creating new file: " + filePath);
                    return new JsonObject();
                } else {
                    throw new RuntimeException("Failed to create file: " + filePath);
                }
            }
            String jsonString = BlueNodeDeserializer.loadFromBlueNode(filePath);
            return JsonParser.parseString(jsonString).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException("Error handling file: " + e.getMessage());
        }
    }

    public static void delete(String filePath) {
        try {
            if (!filePath.endsWith(FILE_EXTENSION)) {
                filePath = filePath + FILE_EXTENSION;
            }
            File file = new File(filePath);
            if (file.exists()) {
                if (file.delete()) {
                    BaseLogger.log(BaseLogLevel.SUCCESS, "Deleted file: " + filePath);
                } else {
                    throw new RuntimeException("Failed to delete file: " + filePath);
                }
            } else {
                BaseLogger.log(BaseLogLevel.WARNING, "File did not exist: " + filePath);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting file: " + e.getMessage());
        }
    }
}
