package com.mealam.bluenode.nodes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Metadata {

    private final MetadataProperties properties;

    public Metadata() {
        properties = new MetadataProperties();
    }

    public static Metadata fromJson(JsonObject jsonObject) {
        Metadata metadata = new Metadata();

        metadata.properties.createdAt = new Date(System.currentTimeMillis());
        metadata.properties.updatedAt = new Date(System.currentTimeMillis());
        JsonArray tagsJsonArray = jsonObject.getAsJsonArray("aliases");
        metadata.properties.aliases = new ArrayList<>();
        if (tagsJsonArray != null) {
            for (JsonElement tag : tagsJsonArray) {
                metadata.properties.aliases.add(tag.getAsString());
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

        private Date createdAt;
        private Date updatedAt;
        private List<String> aliases;

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
        }

        public List<String> getAliases() {
            return aliases;
        }

        public void setAliases(List<String> aliases) {
            this.aliases = aliases;
        }

        @Override
        public String toString() {
            return "{\n" +
                    "    \"createdAt\": " + createdAt + ",\n" +
                    "    \"updatedAt\": " + updatedAt + ",\n" +
                    "    \"aliases\": " + aliases + "\n" +
                    "  }";
        }
    }
}
