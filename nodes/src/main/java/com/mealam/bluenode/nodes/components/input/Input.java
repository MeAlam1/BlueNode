package com.mealam.bluenode.nodes.components.input;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class Input {

    private final InputProperties properties;

    public Input() {
        properties = new InputProperties();
    }

    public static Input fromJson(JsonObject jsonObject) {
        Input input = new Input();
        input.properties.id = jsonObject.get("id").getAsString();
        input.properties.name = jsonObject.get("name").getAsString();
        input.properties.type = jsonObject.get("type").getAsString();
        input.properties.defaultValue = jsonObject.has("defaultValue") ? jsonObject.get("defaultValue").getAsInt() : 0;
        return input;
    }

    public static List<Input> fromJsonArray(JsonArray jsonArray) {
        List<Input> inputs = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            inputs.add(Input.fromJson(element.getAsJsonObject()));
        }
        return inputs;
    }

    @Override
    public String toString() {
        return properties.toString();
    }

    public InputProperties getProperties() {
        return properties;
    }

    public static class InputProperties {

        private String id;
        private String name;
        private String type;
        private int defaultValue;

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

        public int getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(int defaultValue) {
            this.defaultValue = defaultValue;
        }

        @Override
        public String toString() {
            return "\n    {\n" +
                    "      \"id\": \"" + id + "\",\n" +
                    "      \"name\": \"" + name + "\",\n" +
                    "      \"type\": \"" + type + "\",\n" +
                    "      \"defaultValue\": " + defaultValue + "\n" +
                    "    }\n  ";
        }
    }
}
