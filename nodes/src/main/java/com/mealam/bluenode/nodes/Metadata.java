package com.mealam.bluenode.nodes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class Metadata {

    private final MetadataProperties properties;

    public Metadata() {
        properties = new MetadataProperties();
    }

    public static Metadata fromJson(JsonObject jsonObject) {
        Metadata metadata = new Metadata();

        JsonArray tagsJsonArray = jsonObject.getAsJsonArray("tags");
        metadata.properties.tags = new ArrayList<>();
        if (tagsJsonArray != null) {
            for (JsonElement tag : tagsJsonArray) {
                metadata.properties.tags.add(tag.getAsString());
            }
        }

        return metadata;
    }

    @Override
    public String toString() {
        return properties.toString();
    }

    public MetadataProperties getProperties() {
        return properties;
    }

    public static class MetadataProperties {

        private List<String> tags;

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        @Override
        public String toString() {
            return "{\n" +
                    "    \"tags\": " + tags + "\n" +
                    "  }";
        }
    }
}
