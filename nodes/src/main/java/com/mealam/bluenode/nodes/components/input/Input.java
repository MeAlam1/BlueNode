package com.mealam.bluenode.nodes.components.input;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mealam.bluenode.utils.nodes.input.InputCategoryUtils;

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

        String categoryType = input.getProperties().getType();

        input.properties.setWidth(20);
        input.properties.setCss(InputCategoryUtils.getInputCategoryData(categoryType, "cssName"));
        input.properties.setValidate(InputCategoryUtils.getInputCategoryData(categoryType, "validate"));
        input.properties.setAliases(InputCategoryUtils.getInputCategoryData(categoryType, "aliases"));

        input.properties.value = jsonObject.has("defaultValue") ? jsonObject.get("defaultValue").getAsString() : "";
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
        private String validate;
        private String aliases;
        private String value;

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

        public String getValidate() {
            return validate;
        }

        public void setValidate(String validate) {
            this.validate = validate;
        }

        public String getAliases() {
            return aliases;
        }

        public void setAliases(String aliases) {
            this.aliases = aliases;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
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
                    "      \"validate\": \"" + validate + "\",\n" +
                    "      \"aliases\": \"" + aliases + "\",\n" +
                    "      \"value\": " + value + "\n" +
                    "    }\n  ";
        }
    }
}
