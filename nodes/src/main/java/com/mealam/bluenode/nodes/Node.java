package com.mealam.bluenode.nodes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mealam.bluenode.nodes.category.CategoryData;
import com.mealam.bluenode.nodes.category.CategoryManager;
import com.mealam.bluenode.nodes.components.Input;
import com.mealam.bluenode.nodes.components.Output;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import java.util.List;

public class Node {

    private String id;
    private final double x;
    private final double y;
    private double width;
    private double height;
    private String color;
    private String title;
    private String description;
    private String category;
    private List<Input> inputs;
    private List<Output> outputs;
    private Metadata metadata;

    public Node(double pX, double pY) {
        x = pX;
        y = pY;
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
        node.title = jsonObject.get("title").getAsString();
        node.description = jsonObject.get("description").getAsString();
        node.category = jsonObject.get("category").getAsString();
        CategoryData categoryData = CategoryManager.getCategoryData(node.category);
        node.color = categoryData.color();
        BaseLogger.log(BaseLogLevel.INFO, "All Data from Node: " + node.title + " " + node.description + " " + node.category);

        JsonArray inputsJsonArray = jsonObject.getAsJsonArray("inputs");
        //node.inputs = Input.fromJsonArray(inputsJsonArray);

        JsonArray outputsJsonArray = jsonObject.getAsJsonArray("outputs");
        //node.outputs = Output.fromJsonArray(outputsJsonArray);

        //node.metadata = Metadata.fromJson(jsonObject.getAsJsonObject("metadata"));

        return node;
    }

    public String getId() {
        return id;
    }

    public void setId(String pId) {
        this.id = pId;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getColor() {
        return color;
    }

    public String getCategory() {
        return category;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public List<Output> getOutputs() {
        return outputs;
    }

    public Metadata getMetadata() {
        return metadata;
    }
}
