package com.mealam.bluenode.format;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class BlueNodeSerializer {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void saveAsBlueNode(String json, String filePath) throws IOException {
        Map data = objectMapper.readValue(json, Map.class);
        objectMapper.writeValue(new File(filePath), data);
    }
}
