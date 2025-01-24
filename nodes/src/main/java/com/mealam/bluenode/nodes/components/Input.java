package com.mealam.bluenode.nodes.components;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mealam.bluenode.interfaces.nodes.NodeComponent;
import java.util.ArrayList;
import java.util.List;

public class Input implements NodeComponent {

    private String id;
    private String name;
    private String type;
    private int defaultValue;

    public static Input fromJson(JsonObject jsonObject) {
        Input input = new Input();
        input.id = jsonObject.get("id").getAsString();
        input.name = jsonObject.get("name").getAsString();
        input.type = jsonObject.get("type").getAsString();
        input.defaultValue = jsonObject.has("defaultValue") ? jsonObject.get("defaultValue").getAsInt() : 0;
        return input;
    }

    public static List<Input> fromJsonArray(JsonArray jsonArray) {
        List<Input> inputs = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            inputs.add(Input.fromJson(element.getAsJsonObject()));
        }
        return inputs;
    }

    // Getter methods
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getDefaultValue() {
        return defaultValue;
    }
}
