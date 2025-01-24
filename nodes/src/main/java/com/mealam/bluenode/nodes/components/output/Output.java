package com.mealam.bluenode.nodes.components.output;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class Output {

    private final OutputProperties properties;

    public Output() {
        properties = new OutputProperties();
    }

    public static Output fromJson(JsonObject jsonObject) {
        Output output = new Output();
        output.properties.id = jsonObject.get("id").getAsString();
        output.properties.name = jsonObject.get("name").getAsString();
        output.properties.type = jsonObject.get("type").getAsString();
        return output;
    }

    public static List<Output> fromJsonArray(JsonArray jsonArray) {
        List<Output> outputs = new ArrayList<>();
        for (JsonElement element : jsonArray) {
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

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
