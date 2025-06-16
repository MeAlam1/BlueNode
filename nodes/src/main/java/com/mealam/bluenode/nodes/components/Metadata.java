package com.mealam.bluenode.nodes.components;

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

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("createdAt", properties.getCreatedAt().getTime());
        json.addProperty("updatedAt", properties.getUpdatedAt().getTime());

        JsonArray aliasesArray = new JsonArray();
        for (String alias : properties.getAliases()) {
            aliasesArray.add(alias);
        }
        json.add("aliases", aliasesArray);

        return json;
    }

    public static Metadata fromJson(JsonObject pJsonObject) {
        Metadata metadata = new Metadata();

        metadata.properties.createdAt = new Date(System.currentTimeMillis());
        metadata.properties.updatedAt = new Date(System.currentTimeMillis());
        JsonArray tagsJsonArray = pJsonObject.getAsJsonArray("aliases");
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

        public void setCreatedAt(Date pCreatedAt) {
            this.createdAt = pCreatedAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Date pUpdatedAt) {
            this.updatedAt = pUpdatedAt;
        }

        public List<String> getAliases() {
            return aliases;
        }

        public void setAliases(List<String> pAliases) {
            this.aliases = pAliases;
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
