package com.mealam.bluenode.nodes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mealam.bluenode.nodes.components.input.Input;
import com.mealam.bluenode.nodes.components.output.Output;
import com.mealam.bluenode.utils.nodes.NodeCategoryUtils;
import org.checkerframework.checker.index.qual.NonNegative;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private final NodeProperties properties;

    public Node(double pX, double pY) {
        properties = new NodeProperties();
        properties.setX(pX);
        properties.setY(pY);
    }

    public JsonObject toJson() {
        JsonObject jsonObject = getJsonObject();

        JsonArray cssArray = new JsonArray();
        if (properties.getCSS() != null) {
            for (String css : properties.getCSS()) {
                cssArray.add(css);
            }
        }
        jsonObject.add("css", cssArray);

        JsonArray inputsArray = new JsonArray();
        if (properties.getInputs() != null) {
            for (Input input : properties.getInputs()) {
                inputsArray.add(input.toJson());
            }
        }
        jsonObject.add("inputs", inputsArray);

        JsonArray outputsArray = new JsonArray();
        if (properties.getOutputs() != null) {
            for (Output output : properties.getOutputs()) {
                outputsArray.add(output.toJson());
            }
        }
        jsonObject.add("outputs", outputsArray);

        if (properties.getMetadata() != null) {
            jsonObject.add("metadata", properties.getMetadata().toJson());
        }

        return jsonObject;
    }

    private JsonObject getJsonObject() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", properties.getId());
        jsonObject.addProperty("x", properties.getX());
        jsonObject.addProperty("y", properties.getY());
        jsonObject.addProperty("minWidth", properties.getMinWidth());
        jsonObject.addProperty("width", properties.getWidth());
        jsonObject.addProperty("minHeight", properties.getMinHeight());
        jsonObject.addProperty("height", properties.getHeight());
        jsonObject.addProperty("title", properties.getTitle());
        jsonObject.addProperty("description", properties.getDescription());
        jsonObject.addProperty("category", properties.getCategory());
        return jsonObject;
    }

    public static Node fromJson(JsonArray pJsonArray, double pX, double pY) {
        if (pJsonArray == null || pJsonArray.isEmpty()) {
            throw new IllegalArgumentException("JsonArray is null or empty, cannot create Node.");
        }

        JsonElement firstElement = pJsonArray.get(0);
        if (!firstElement.isJsonObject()) {
            throw new IllegalArgumentException("First element in JsonArray is not a JsonObject.");
        }

        JsonObject jsonObject = firstElement.getAsJsonObject();

        Node node = new Node(pX, pY);
        node.properties.setTitle(jsonObject.get("title").getAsString());
        node.properties.setDescription(jsonObject.get("description").getAsString());
        node.properties.setCategory(jsonObject.get("category").getAsString());

        List<String> categories = NodeCategoryUtils.getCategories(node.getProperties().getCategory());
        List<String> css = new ArrayList<>();
        for (String category : categories) {
            css.add(NodeCategoryUtils.getCategoryData(category, "cssName"));
        }
        node.getProperties().setCSS(css);

        JsonArray inputsJsonArray = jsonObject.getAsJsonArray("inputs");
        node.properties.setInputs(Input.fromJsonArray(inputsJsonArray));

        JsonArray outputsJsonArray = jsonObject.getAsJsonArray("outputs");
        node.properties.setOutputs(Output.fromJsonArray(outputsJsonArray));

        node.properties.setMetadata(Metadata.fromJson(jsonObject.getAsJsonObject("metadata")));

        return node;
    }

    @Override
    public String toString() {
        return properties.toString();
    }

    public NodeProperties getProperties() {
        return properties;
    }

    public static class NodeProperties {

        private String id;
        private double x;
        private double y;
        private @NonNegative double minWidth;
        private @NonNegative double width;
        private @NonNegative double minHeight;
        private @NonNegative double height;
        private List<String> CSS;
        private String title;
        private String description;
        private String category;
        private List<Input> inputs;
        private List<Output> outputs;
        private Metadata metadata;

        public String getId() {
            return id;
        }

        public void setId(String pId) {
            this.id = pId;
        }

        public double getX() {
            return x;
        }

        public void setX(double pX) {
            this.x = pX;
        }

        public double getY() {
            return y;
        }

        public void setY(double pY) {
            this.y = pY;
        }

        public double getMinWidth() {
            return minWidth;
        }

        public void setMinWidth(@NonNegative double pMinWidth) {
            this.minWidth = pMinWidth;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(@NonNegative double pWidth) {
            this.width = pWidth;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(@NonNegative double pHeight) {
            this.height = pHeight;
        }

        public double getMinHeight() {
            return minHeight;
        }

        public void setMinHeight(@NonNegative double pMinHeight) {
            this.minHeight = pMinHeight;
        }

        public List<String> getCSS() {
            return CSS;
        }

        public void setCSS(List<String> pCSS) {
            this.CSS = pCSS;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String pTitle) {
            this.title = pTitle;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String pDescription) {
            this.description = pDescription;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String pCategory) {
            this.category = pCategory;
        }

        public List<Input> getInputs() {
            return inputs;
        }

        public void setInputs(List<Input> pInputs) {
            this.inputs = pInputs;
        }

        public List<Output> getOutputs() {
            return outputs;
        }

        public void setOutputs(List<Output> pOutputs) {
            this.outputs = pOutputs;
        }

        public Metadata getMetadata() {
            return metadata;
        }

        public void setMetadata(Metadata pMetadata) {
            this.metadata = pMetadata;
        }

        @Override
        public String toString() {
            return "Node Properties: \n{\n" +
                    "  \"id\": \"" + id + "\",\n" +
                    "  \"x\": " + x + ",\n" +
                    "  \"y\": " + y + ",\n" +
                    "  \"width\": " + width + ",\n" +
                    "  \"height\": " + height + ",\n" +
                    "  \"CSS\": \"" + CSS + "\",\n" +
                    "  \"title\": \"" + title + "\",\n" +
                    "  \"description\": \"" + description + "\",\n" +
                    "  \"category\": \"" + category + "\",\n" +
                    "  \"inputs\": " + inputs + ",\n" +
                    "  \"outputs\": " + outputs + ",\n" +
                    "  \"metadata\": " + metadata + "\n" +
                    "}";
        }
    }
}
