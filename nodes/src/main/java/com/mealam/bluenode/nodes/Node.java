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

    public static Node fromJson(JsonArray jsonArray, double pX, double pY) {
        if (jsonArray == null || jsonArray.isEmpty()) {
            throw new IllegalArgumentException("JsonArray is null or empty, cannot create Node.");
        }

        JsonElement firstElement = jsonArray.get(0);
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

        public void setId(String id) {
            this.id = id;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getMinWidth() {
            return minWidth;
        }

        public void setMinWidth(@NonNegative double minWidth) {
            this.minWidth = minWidth;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(@NonNegative double width) {
            this.width = width;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(@NonNegative double height) {
            this.height = height;
        }

        public double getMinHeight() {
            return minHeight;
        }

        public void setMinHeight(@NonNegative double minHeight) {
            this.minHeight = minHeight;
        }

        public List<String> getCSS() {
            return CSS;
        }

        public void setCSS(List<String> CSS) {
            this.CSS = CSS;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public List<Input> getInputs() {
            return inputs;
        }

        public void setInputs(List<Input> inputs) {
            this.inputs = inputs;
        }

        public List<Output> getOutputs() {
            return outputs;
        }

        public void setOutputs(List<Output> outputs) {
            this.outputs = outputs;
        }

        public Metadata getMetadata() {
            return metadata;
        }

        public void setMetadata(Metadata metadata) {
            this.metadata = metadata;
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
