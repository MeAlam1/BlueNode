package com.mealam.bluenode.utils.io;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mealam.bluenode.format.BlueNodeDeserializer;
import com.mealam.bluenode.format.BlueNodeSerializer;

import java.io.File;
import java.io.IOException;

public class BlueNodeFileUtils {

    private static final String FILE_EXTENSION = ".bluenode";

    public static void save(JsonObject json, String filePath) {
        try {
            String fullPath = filePath.endsWith(FILE_EXTENSION) ? filePath : filePath + FILE_EXTENSION;
            BlueNodeSerializer.saveAsBlueNode(json.toString(), fullPath);
            System.out.println("Saved to: " + fullPath);
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
                throw new RuntimeException("File does not exist: " + filePath);
            }

            String jsonString = BlueNodeDeserializer.loadFromBlueNode(filePath);
            return JsonParser.parseString(jsonString).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException("Error loading file: " + e.getMessage());
        }
    }
}
