package com.mealam.bluenode.nodes.components.output;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mealam.bluenode.utils.json.JSONUtils;
import java.util.ArrayList;
import java.util.List;

public class Output {

    private final OutputProperties properties;

    public Output() {
        properties = new OutputProperties();
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("id", properties.getId());
        json.addProperty("name", properties.getName());
        json.addProperty("type", properties.getType());
        return json;
    }

    public static Output fromJson(JsonObject pJsonObject) {
        Output output = new Output();
        output.properties.id = JSONUtils.getOrDefault(pJsonObject, "id", "Error404");
        output.properties.name = JSONUtils.getOrDefault(pJsonObject, "name", "Error404");
        output.properties.type = JSONUtils.getOrDefault(pJsonObject, "type", "Error404");
        return output;
    }

    public static List<Output> fromJsonArray(JsonArray pJsonArray) {
        List<Output> outputs = new ArrayList<>();
        for (JsonElement element : pJsonArray) {
            outputs.add(Output.fromJson(element.getAsJsonObject()));
        }
        return outputs;
    }

    @Override
    public String toString() {
        return properties.toString();
    }

    public OutputProperties getProperties() {
        return properties;
    }

    public static class OutputProperties {

        private String id;
        private String name;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String pId) {
            this.id = pId;
        }

        public String getName() {
            return name;
        }

        public void setName(String pName) {
            this.name = pName;
        }

        public String getType() {
            return type;
        }

        public void setType(String pType) {
            this.type = pType;
        }

        @Override
        public String toString() {
            return "\n    {\n" +
                    "      \"id\": \"" + id + "\",\n" +
                    "      \"name\": \"" + name + "\",\n" +
                    "      \"type\": " + type + "\n" +
                    "    }\n  ";
        }
    }
}
