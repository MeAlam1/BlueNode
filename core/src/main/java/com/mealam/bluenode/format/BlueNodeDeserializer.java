package com.mealam.bluenode.format;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class BlueNodeDeserializer {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String loadFromBlueNode(String pFilePath) throws IOException {
        Map data = objectMapper.readValue(new File(pFilePath), Map.class);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
    }
}
