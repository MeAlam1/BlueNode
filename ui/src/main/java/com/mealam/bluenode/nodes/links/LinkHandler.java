package com.mealam.bluenode.nodes.links;

import com.mealam.bluenode.utils.io.NodeUtils;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class LinkHandler {
    private static Line currentLine = null;
    private static OutputLink currentOutput = null;
    private static final List<Connection> connections = new ArrayList<>();

    public static void startConnection(MouseEvent event) {
        if (!(event.getSource() instanceof OutputLink)) return;

        currentOutput = (OutputLink) event.getSource();

        Bounds outputBounds = currentOutput.localToScene(currentOutput.getBoundsInLocal());
        Bounds parentBounds = currentOutput.parentPane.sceneToLocal(outputBounds);
        double startX = parentBounds.getMinX() + parentBounds.getWidth() / 2;
        double startY = parentBounds.getMinY() + parentBounds.getHeight() / 2;

        currentLine = new Line(startX, startY, startX, startY);
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
            createLine(input);
        } else {
            removeLine();
        }
    }

    public static void createLine(InputLink input) {
        Bounds inputBounds = input.localToScene(input.getBoundsInLocal());
        Bounds parentBounds = currentLine.getParent().sceneToLocal(inputBounds);

        currentLine.setEndX(parentBounds.getMinX() + parentBounds.getWidth() / 2);
        currentLine.setEndY(parentBounds.getMinY() + parentBounds.getHeight() / 2);

        com.mealam.bluenode.nodes.Node sourceNode = currentOutput.parentPane.getNode();
        double sourceOffsetX = currentLine.getStartX();
        double sourceOffsetY = currentLine.getStartY();

        com.mealam.bluenode.nodes.Node targetNode = input.parentPane.getNode();
        double targetOffsetX = currentLine.getEndX();
        double targetOffsetY = currentLine.getEndY();

        connections.add(new Connection(currentLine, sourceNode, sourceOffsetX, sourceOffsetY,
                targetNode, targetOffsetX, targetOffsetY));

        executeConnection(currentOutput, input);
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

    public static List<Connection> getConnectionObjects() {
        return connections;
    }
}