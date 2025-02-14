package com.mealam.bluenode.utils.io;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mealam.bluenode.io.BlueNodeIO;

public class NodeUtils {

    public static void addNode(String filePath, JsonObject node) {
        try {
            JsonObject mainJson = BlueNodeIO.load(filePath);
            if (!mainJson.has("nodes") || !mainJson.get("nodes").isJsonArray()) {
                mainJson.add("nodes", new JsonArray());
            }
            mainJson.getAsJsonArray("nodes").add(node);
            BlueNodeIO.save(mainJson, filePath);

            //BaseLogger.log(BaseLogLevel.SUCCESS, "Node added successfully to: " + filePath);
        } catch (Exception e) {
            throw new RuntimeException("Error adding node: " + e.getMessage());
        }
    }

    public static void updateNode(String filePath, String nodeId, JsonObject newNodeData) {
        try {
            JsonObject mainJson = BlueNodeIO.load(filePath);

            if (!mainJson.has("nodes") || !mainJson.get("nodes").isJsonArray()) {
                throw new RuntimeException("No nodes found in the file.");
            }

            JsonArray nodes = mainJson.getAsJsonArray("nodes");
            boolean nodeFound = false;

            for (int i = 0; i < nodes.size(); i++) {
                JsonElement element = nodes.get(i);
                if (element.isJsonObject()) {
                    JsonObject node = element.getAsJsonObject();
                    if (node.has("id") && node.get("id").getAsString().equals(nodeId)) {
                        nodes.set(i, newNodeData);
                        nodeFound = true;
                        break;
                    }
                }
            }

            if (!nodeFound) {
                throw new RuntimeException("Node with ID " + nodeId + " not found.");
            }
            BlueNodeIO.save(mainJson, filePath);

            //BaseLogger.log(BaseLogLevel.SUCCESS, "Node with ID " + nodeId + " replaced successfully in: " + filePath);
        } catch (Exception e) {
            throw new RuntimeException("Error updating node: " + e.getMessage());
        }
    }

    public static void deleteNode(String filePath, String nodeId) {
        try {
            JsonObject mainJson = BlueNodeIO.load(filePath);

            if (!mainJson.has("nodes") || !mainJson.get("nodes").isJsonArray()) {
                throw new RuntimeException("No nodes found in the file.");
            }

            JsonArray nodes = mainJson.getAsJsonArray("nodes");
            boolean nodeFound = false;

            for (int i = 0; i < nodes.size(); i++) {
                JsonElement element = nodes.get(i);
                if (element.isJsonObject()) {
                    JsonObject node = element.getAsJsonObject();
                    if (node.has("id") && node.get("id").getAsString().equals(nodeId)) {
                        nodes.remove(i);
                        nodeFound = true;
                        break;
                    }
                }
            }

            if (!nodeFound) {
                throw new RuntimeException("Node with ID " + nodeId + " not found.");
            }
            BlueNodeIO.save(mainJson, filePath);

            //BaseLogger.log(BaseLogLevel.SUCCESS, "Node with ID " + nodeId + " deleted successfully from: " + filePath);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting node: " + e.getMessage());
        }
    }

    public static JsonObject getNodeJSON(String filePath, String nodeId) {
        try {
            JsonObject mainJson = BlueNodeIO.load(filePath);

            if (!mainJson.has("nodes") || !mainJson.get("nodes").isJsonArray()) {
                throw new RuntimeException("No nodes found in the file.");
            }

            JsonArray nodes = mainJson.getAsJsonArray("nodes");

            for (int i = 0; i < nodes.size(); i++) {
                JsonElement element = nodes.get(i);
                if (element.isJsonObject()) {
                    JsonObject node = element.getAsJsonObject();
                    if (node.has("id") && node.get("id").getAsString().equals(nodeId)) {
                        return node;
                    }
                }
            }

            throw new RuntimeException("Node with ID " + nodeId + " not found.");
        } catch (Exception e) {
            throw new RuntimeException("Error getting node: " + e.getMessage());
        }
    }
}
