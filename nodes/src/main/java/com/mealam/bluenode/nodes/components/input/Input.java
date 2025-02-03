package com.mealam.bluenode.nodes.components.input;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mealam.bluenode.nodes.components.input.category.InputCategoryData;
import com.mealam.bluenode.nodes.components.input.category.InputCategoryManager;

import java.util.ArrayList;
import java.util.List;

public class Input {

    private final InputProperties properties;

    public Input() {
        properties = new InputProperties();
    }

    public static Input fromJson(JsonObject jsonObject) {
        Input input = new Input();
        input.properties.setId(jsonObject.get("id").getAsString());
        input.properties.setName(jsonObject.get("name").getAsString());
        input.properties.setType(jsonObject.get("type").getAsString());
        input.properties.setWidth(20);
        InputCategoryData categoryData = InputCategoryManager.getCategoryData(input.properties.getType());
        input.properties.setCss(categoryData.cssName());

        input.properties.defaultValue = jsonObject.has("defaultValue") ? jsonObject.get("defaultValue").getAsString() : "";
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
        private double width;
        private double height;
        private String name;
        private String type;
        private String cssName;
        private String defaultValue;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
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

        public String getCss() {
            return cssName;
        }

        public void setCss(String cssName) {
            this.cssName = cssName;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        @Override
        public String toString() {
            return "\n    {\n" +
                    "      \"id\": \"" + id + "\",\n" +
                    "      \"width\": " + width + ",\n" +
                    "      \"height\": " + height + ",\n" +
                    "      \"name\": \"" + name + "\",\n" +
                    "      \"type\": \"" + type + "\",\n" +
                    "      \"CSS\": \"" + cssName + "\",\n" +
                    "      \"defaultValue\": " + defaultValue + "\n" +
                    "    }\n  ";
        }
    }
}
