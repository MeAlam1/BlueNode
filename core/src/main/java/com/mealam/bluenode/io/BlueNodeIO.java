package com.mealam.bluenode.io;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mealam.bluenode.Constants;
import com.mealam.bluenode.format.BlueNodeDeserializer;
import com.mealam.bluenode.format.BlueNodeSerializer;
import com.mealam.bluenode.json.JSONLoader;
import com.mealam.bluenode.utils.RandomIDUtils;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;

import java.io.File;
import java.io.IOException;
import java.util.Date;

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

    public static void create(String filePath, String pFileName) {
        try {
            if (!filePath.endsWith(FILE_EXTENSION)) {
                filePath = filePath + FILE_EXTENSION;
            }
            File file = new File(filePath);
            if (file.exists()) {
                BaseLogger.log(BaseLogLevel.INFO, "File already exists: " + filePath);
                return;
            }
            if (file.createNewFile()) {
                BaseLogger.log(BaseLogLevel.SUCCESS, "Created new file: " + filePath);
                JsonObject content = getJsonObject(pFileName);
                save(content, filePath);
            } else {
                throw new RuntimeException("Failed to create file: " + filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error creating file: " + e.getMessage());
        }
    }

    private static JsonObject getJsonObject(String pFileName) {
        JsonObject template = JSONLoader.loadJson("data/template/SourceFileTemplate.json");
        template.addProperty("id", RandomIDUtils.generateID(pFileName));
        template.addProperty("name", pFileName);
        template.addProperty("version", ""); //TODO: Get version from somewhere later
        template.addProperty("editor_version", Constants.APP_VERSION);
        template.addProperty("created_at", new Date().getTime());
        template.addProperty("updated_at", new Date().getTime());

        template.add("nodes", new JsonObject());
        template.add("custom_data", new JsonObject());

        return template;
    }

}
