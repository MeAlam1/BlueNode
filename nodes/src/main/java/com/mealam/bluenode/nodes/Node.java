package com.mealam.bluenode.nodes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mealam.bluenode.nodes.category.NodeCategoryData;
import com.mealam.bluenode.nodes.category.NodeCategoryManager;
import com.mealam.bluenode.nodes.components.input.Input;
import com.mealam.bluenode.nodes.components.output.Output;
import java.util.List;
import org.checkerframework.checker.index.qual.NonNegative;

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

        NodeCategoryData categoryData = NodeCategoryManager.getCategoryData(node.properties.getCategory());
        node.properties.setColor(categoryData.color());

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
        private double width;
        private double height;
        private String color;
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

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
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
                    "  \"color\": \"" + color + "\",\n" +
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
