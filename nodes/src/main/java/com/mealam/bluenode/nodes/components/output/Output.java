package com.mealam.bluenode.nodes.components.output;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mealam.bluenode.interfaces.nodes.NodeComponent;
import java.util.ArrayList;
import java.util.List;

public class Output implements NodeComponent {

    private String id;
    private String name;
    private String type;

    public static Output fromJson(JsonObject jsonObject) {
        Output output = new Output();
        output.id = jsonObject.get("id").getAsString();
        output.name = jsonObject.get("name").getAsString();
        output.type = jsonObject.get("type").getAsString();
        return output;
    }

    public static List<Output> fromJsonArray(JsonArray jsonArray) {
        List<Output> outputs = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            outputs.add(Output.fromJson(element.getAsJsonObject()));
        }
        return outputs;
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
}
