package com.mealam.bluenode.nodes.components.input;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mealam.bluenode.utils.json.JSONUtils;
import com.mealam.bluenode.utils.nodes.input.InputCategoryUtils;
import java.util.ArrayList;
import java.util.List;

public class Input {

    private final InputProperties properties;

    public Input() {
        properties = new InputProperties();
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("id", properties.getId());
        json.addProperty("name", properties.getName());
        json.addProperty("type", properties.getType());
        json.addProperty("width", properties.getWidth());
        json.addProperty("height", properties.getHeight());
        json.addProperty("CSS", properties.getCss());
        json.addProperty("validate", properties.getValidate());
        json.addProperty("aliases", properties.getAliases());
        json.addProperty("value", properties.getValue());
        return json;
    }

    public static Input fromJson(JsonObject pJsonObject) {
        Input input = new Input();

        input.properties.setId(JSONUtils.getOrDefault(pJsonObject, "id", "Error404"));
        input.properties.setName(JSONUtils.getOrDefault(pJsonObject, "name", "Error404"));
        input.properties.setType(JSONUtils.getOrDefault(pJsonObject, "type", "Error404"));

        String categoryType = input.getProperties().getType();

        input.properties.setWidth(20);
        input.properties.setCss(InputCategoryUtils.getInputCategoryData(categoryType, "cssName"));
        input.properties.setValidate(InputCategoryUtils.getInputCategoryData(categoryType, "validate"));
        input.properties.setAliases(InputCategoryUtils.getInputCategoryData(categoryType, "aliases"));

        input.properties.value = JSONUtils.getOrDefault(pJsonObject, "defaultValue", "Error404");
        return input;
    }

    public static List<Input> fromJsonArray(JsonArray pJsonArray) {
        List<Input> inputs = new ArrayList<>();
        for (JsonElement element : pJsonArray) {
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

        public void setId(String pId) {
            this.id = pId;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double pWidth) {
            this.width = pWidth;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double pHeight) {
            this.height = pHeight;
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

        public String getCss() {
            return cssName;
        }

        public void setCss(String pCSSName) {
            this.cssName = pCSSName;
        }

        public String getValidate() {
            return validate;
        }

        public void setValidate(String pValidate) {
            this.validate = pValidate;
        }

        public String getAliases() {
            return aliases;
        }

        public void setAliases(String pAliases) {
            this.aliases = pAliases;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String pValue) {
            this.value = pValue;
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
