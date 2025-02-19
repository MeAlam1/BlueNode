package com.mealam.bluenode.nodes.links;

import com.mealam.bluenode.utils.io.NodeUtils;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LinkHandler {

    private static Line currentLine = null;
    private static OutputLink currentOutput = null;
    private static final List<Connection> connections = new ArrayList<>();

    public static void startConnection(MouseEvent pEvent) {
        if (!(pEvent.getSource() instanceof OutputLink)) return;

        currentOutput = (OutputLink) pEvent.getSource();

        Bounds outputBounds = currentOutput.localToScene(currentOutput.getBoundsInLocal());
        Bounds parentBounds = currentOutput.parentPane.sceneToLocal(outputBounds);
        double startX = parentBounds.getMinX() + parentBounds.getWidth();
        double startY = parentBounds.getMinY() + parentBounds.getHeight() / 2;

        currentLine = new Line(startX, startY, startX, startY);
        currentLine.setStroke(Color.WHITE);

        currentOutput.parentPane.getChildren().add(currentLine);
        pEvent.consume();
    }

    public static void dragConnection(MouseEvent pEvent) {
        if (currentLine != null) {
            currentLine.setEndX(pEvent.getX());
            currentLine.setEndY(pEvent.getY());
        }
    }

    public static void endConnection(MouseEvent pEvent) {
        if (currentLine == null || currentOutput == null) return;

        Node target = pEvent.getPickResult().getIntersectedNode();

        if (target instanceof InputLink input) {
            createLine(input);
        } else {
            removeLine();
        }
    }

    private static void createLine(InputLink pInput) {
        Bounds inputBounds = pInput.localToScene(pInput.getBoundsInLocal());
        Bounds parentBounds = currentLine.getParent().sceneToLocal(inputBounds);

        currentLine.setEndX(parentBounds.getMinX() + parentBounds.getWidth() / 2);
        currentLine.setEndY(parentBounds.getMinY() + parentBounds.getHeight() / 2);

        com.mealam.bluenode.nodes.Node sourceNode = currentOutput.parentPane.getNode();
        double sourceOffsetX = currentLine.getStartX();
        double sourceOffsetY = currentLine.getStartY();

        com.mealam.bluenode.nodes.Node targetNode = pInput.parentPane.getNode();
        double targetOffsetX = currentLine.getEndX();
        double targetOffsetY = currentLine.getEndY();

        connections.add(new Connection(currentLine, sourceNode, sourceOffsetX, sourceOffsetY,
                targetNode, targetOffsetX, targetOffsetY));

        executeConnection(currentOutput, pInput);
    }

    private static void executeConnection(OutputLink pOutput, InputLink pInput) {
        String nodeOutputID = pOutput.parentPane.getNode().getProperties().getId();
        String nodeInputID = pInput.parentPane.getNode().getProperties().getId();
        pInput.parentPane.getNode().getProperties().setInputNode(nodeOutputID);
        pOutput.parentPane.getNode().getProperties().setOutputNode(nodeInputID);
        NodeUtils.updateNode("test", pInput.parentPane.getNode().getProperties().getId(), pInput.parentPane.getNode().toJson());
        NodeUtils.updateNode("test", pOutput.parentPane.getNode().getProperties().getId(), pOutput.parentPane.getNode().toJson());
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
