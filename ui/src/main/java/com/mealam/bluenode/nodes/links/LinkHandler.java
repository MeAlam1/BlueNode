package com.mealam.bluenode.nodes.links;

import com.mealam.bluenode.utils.io.NodeUtils;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

class LinkHandler {
    private static Line currentLine = null;
    private static OutputLink currentOutput = null;

    public static void startConnection(MouseEvent event) {
        if (!(event.getSource() instanceof OutputLink)) return;

        currentOutput = (OutputLink) event.getSource();
        currentLine = new Line(currentOutput.getCenterX(), currentOutput.getCenterY(),
                currentOutput.getCenterX(), currentOutput.getCenterY());
        currentLine.setStroke(Color.WHITE);

        currentOutput.parentPane.getChildren().add(currentLine);
        event.consume();
    }

    public static void dragConnection(MouseEvent event) {
        if (currentLine != null) {
            currentLine.setEndX(event.getX());
            currentLine.setEndY(event.getY());
        }
    }

    public static void endConnection(MouseEvent event) {
        if (currentLine == null || currentOutput == null) return;

        Node target = event.getPickResult().getIntersectedNode();

        if (target instanceof InputLink input) {
            Bounds inputBounds = input.localToScene(input.getBoundsInLocal());
            Bounds parentBounds = currentLine.getParent().sceneToLocal(inputBounds);

            currentLine.setEndX(parentBounds.getMinX() + parentBounds.getWidth() / 2);
            currentLine.setEndY(parentBounds.getMinY() + parentBounds.getHeight() / 2);


            executeConnection(currentOutput, input);
        } else {
            removeLine();
        }
    }

    private static void executeConnection(OutputLink output, InputLink input) {
        String nodeOutputID = output.parentPane.getNode().getProperties().getId();
        String nodeInputID = input.parentPane.getNode().getProperties().getId();
        input.parentPane.getNode().getProperties().setInputNode(nodeOutputID);
        output.parentPane.getNode().getProperties().setOutputNode(nodeInputID);
        NodeUtils.updateNode("test", input.parentPane.getNode().getProperties().getId(), input.parentPane.getNode().toJson());
        NodeUtils.updateNode("test", output.parentPane.getNode().getProperties().getId(), output.parentPane.getNode().toJson());
    }

    private static void removeLine() {
        if (currentLine != null) {
            currentOutput.parentPane.getChildren().remove(currentLine);
            currentLine = null;
        }
    }
}