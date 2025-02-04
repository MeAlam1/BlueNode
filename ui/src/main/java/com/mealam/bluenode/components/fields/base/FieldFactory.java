package com.mealam.bluenode.components.fields.base;

import com.mealam.bluenode.components.fields.FloatField;
import com.mealam.bluenode.components.fields.IntField;
import com.mealam.bluenode.components.fields.StringField;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.components.input.Input;

import java.util.HashMap;
import java.util.Map;

public class FieldFactory {
    private static final Map<String, Class<? extends InputField>> fieldMap = new HashMap<>();

    static {
        fieldMap.put("float", FloatField.class);
        fieldMap.put("string", StringField.class);
        fieldMap.put("integer", IntField.class);
    }

    public static InputField createField(String type, GraphicsContext pGraphicsContext, Pane parent, Input input, Node pNode) {
        Class<? extends InputField> fieldClass = fieldMap.get(type);
        if (fieldClass != null) {
            try {
                return fieldClass.getConstructor(GraphicsContext.class, Pane.class, Input.class, Node.class)
                        .newInstance(pGraphicsContext, parent, input, pNode);
            } catch (Exception exception) {
                throw new RuntimeException("Failed to create field", exception);
            }
        }
        throw new IllegalArgumentException("Unsupported field type: " + type);
    }
}