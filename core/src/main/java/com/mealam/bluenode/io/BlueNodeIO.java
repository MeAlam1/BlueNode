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

    public static void save(JsonObject pJson, String pFilePath) {
        try {
            String fullPath = pFilePath.endsWith(FILE_EXTENSION) ? pFilePath : pFilePath + FILE_EXTENSION;
            BlueNodeSerializer.saveAsBlueNode(pJson.toString(), fullPath);
            //BaseLogger.log(BaseLogLevel.SUCCESS, "Saved to: " + fullPath);
        } catch (IOException pIOException) {
            throw new RuntimeException("Error saving file: " + pIOException);
        }
    }

    public static JsonObject load(String pFilePath) {
        try {
            if (!pFilePath.endsWith(FILE_EXTENSION)) {
                pFilePath = pFilePath + FILE_EXTENSION;
            }
            File file = new File(pFilePath);
            if (!file.exists()) {
                if (file.createNewFile()) {
                    BaseLogger.log(BaseLogLevel.WARNING, "File did not exist, creating new file: " + pFilePath);
                    return new JsonObject();
                } else {
                    throw new RuntimeException("Failed to create file: " + pFilePath);
                }
            }
            String jsonString = BlueNodeDeserializer.loadFromBlueNode(pFilePath);
            return JsonParser.parseString(jsonString).getAsJsonObject();
        } catch (IOException pIOException) {
            throw new RuntimeException("Error handling file: " + pIOException);
        }
    }

    public static void delete(String pFilePath) {
        try {
            if (!pFilePath.endsWith(FILE_EXTENSION)) {
                pFilePath = pFilePath + FILE_EXTENSION;
            }
            File file = new File(pFilePath);
            if (file.exists()) {
                if (file.delete()) {
                    BaseLogger.log(BaseLogLevel.SUCCESS, "Deleted file: " + pFilePath);
                } else {
                    throw new RuntimeException("Failed to delete file: " + pFilePath);
                }
            } else {
                BaseLogger.log(BaseLogLevel.WARNING, "File did not exist: " + pFilePath);
            }
        } catch (Exception pException) {
            throw new RuntimeException("Error deleting file: " + pException);
        }
    }

    public static void create(String pFilePath, String pFileName) {
        try {
            if (!pFilePath.endsWith(FILE_EXTENSION)) {
                pFilePath = pFilePath + FILE_EXTENSION;
            }
            File file = new File(pFilePath);
            if (file.exists()) {
                BaseLogger.log(BaseLogLevel.INFO, "File already exists: " + pFilePath);
                return;
            }
            if (file.createNewFile()) {
                BaseLogger.log(BaseLogLevel.SUCCESS, "Created new file: " + pFilePath);
                JsonObject content = getJsonObject(pFileName);
                save(content, pFilePath);
            } else {
                throw new RuntimeException("Failed to create file: " + pFilePath);
            }
        } catch (IOException pIOException) {
            throw new RuntimeException("Error creating file: " + pIOException);
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
