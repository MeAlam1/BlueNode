package com.mealam.bluenode.nodes.links;

import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

class LinkHandler {
    private static Line currentLine = null;
    private static OutputLink currentOutput = null;

    public static void startConnection(MouseEvent event) {
        if (!(event.getSource() instanceof OutputLink)) return;

        ((OutputLink) event.getSource()).startDragAndDrop(TransferMode.LINK);

        currentOutput = (OutputLink) event.getSource();
        currentLine = new Line(currentOutput.getCenterX(), currentOutput.getCenterY(),
                currentOutput.getCenterX(), currentOutput.getCenterY());
        currentLine.setStroke(Color.BLACK);

        currentOutput.parentPane.getChildren().add(currentLine);
        event.consume();
    }

    public static void dragConnection(MouseEvent event) {
        if (currentLine != null) {
            currentLine.setEndX(event.getX());
            currentLine.setEndY(event.getY());
        }
    }

    public static void endConnection(DragEvent event) {
        if (currentLine == null) return;

        BaseLogger.log(BaseLogLevel.ERROR, "Source: " + event.getSource());

        if (event.getSource() instanceof InputLink input) {

            currentLine.setEndX(input.getCenterX());
            currentLine.setEndY(input.getCenterY());

            executeConnection(currentOutput, input);
        } else {
            removeLine();
        }
    }

    private static void executeConnection(OutputLink output, InputLink input) {
        input.parentPane.getNode().getProperties().setInputNode(output.parentPane.getNode().getProperties().getId());
        output.parentPane.getNode().getProperties().setOutputNode(input.parentPane.getNode().getProperties().getId());
    }

    private static void removeLine() {
        if (currentLine != null) {
            currentOutput.parentPane.getChildren().remove(currentLine);
            currentLine = null;
        }
    }
}