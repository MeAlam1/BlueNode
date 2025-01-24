package com.mealam.bluenode.nodes;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.List;
import java.util.ArrayList;

public class Metadata {
    private String createdBy;
    private String createdAt;
    private String updatedAt;
    private List<String> tags;

    public static Metadata fromJson(JsonObject jsonObject) {
        Metadata metadata = new Metadata();
        metadata.createdBy = jsonObject.has("createdBy") ? jsonObject.get("createdBy").getAsString() : null;
        metadata.createdAt = jsonObject.has("createdAt") ? jsonObject.get("createdAt").getAsString() : null;
        metadata.updatedAt = jsonObject.has("updatedAt") ? jsonObject.get("updatedAt").getAsString() : null;

        JsonArray tagsJsonArray = jsonObject.getAsJsonArray("tags");
        metadata.tags = new ArrayList<>();
        if (tagsJsonArray != null) {
            for (JsonElement tag : tagsJsonArray) {
                metadata.tags.add(tag.getAsString());
            }
        }

        return metadata;
    }

    // Getter methods
    public String getCreatedBy() { return createdBy; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public List<String> getTags() { return tags; }
}
