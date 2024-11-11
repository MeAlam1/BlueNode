package com.mealam.bluenode.listeners;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WindowResizer {

    private static final int RESIZE_MARGIN = 5;
    private ResizeDirection resizeDirection = ResizeDirection.NONE;
    private double startX;
    private double startY;

    public void enableWindowResize(Stage stage, Pane pane) {
        pane.setOnMouseMoved(event -> updateCursor(event, pane));
        pane.setOnMousePressed(this::mousePressed);
        pane.setOnMouseDragged(event -> resizeWindow(event, stage, pane));
        pane.setOnMouseReleased(event -> resizeDirection = ResizeDirection.NONE);
    }

    private void updateCursor(MouseEvent event, Pane pane) {
        resizeDirection = determineResizeDirection(event, pane);

        Cursor cursor = switch (resizeDirection) {
            case NW -> Cursor.NW_RESIZE;
            case NE -> Cursor.NE_RESIZE;
            case SW -> Cursor.SW_RESIZE;
            case SE -> Cursor.SE_RESIZE;
            case W -> Cursor.W_RESIZE;
            case E -> Cursor.E_RESIZE;
            case N -> Cursor.N_RESIZE;
            case S -> Cursor.S_RESIZE;
            default -> Cursor.DEFAULT;
        };

        pane.setCursor(cursor);
    }

    private void mousePressed(MouseEvent event) {
        startX = event.getScreenX();
        startY = event.getScreenY();
    }

    private void resizeWindow(MouseEvent event, Stage stage, Pane pane) {
        if (resizeDirection == ResizeDirection.NONE) return;

        double deltaX = event.getScreenX() - startX;
        double deltaY = event.getScreenY() - startY;

        switch (resizeDirection) {
            case NW -> {
                stage.setX(stage.getX() + deltaX);
                stage.setY(stage.getY() + deltaY);
                stage.setWidth(stage.getWidth() - deltaX);
                stage.setHeight(stage.getHeight() - deltaY);
            }
            case NE -> {
                stage.setY(stage.getY() + deltaY);
                stage.setWidth(stage.getWidth() + deltaX);
                stage.setHeight(stage.getHeight() - deltaY);
            }
            case SW -> {
                stage.setX(stage.getX() + deltaX);
                stage.setWidth(stage.getWidth() - deltaX);
                stage.setHeight(stage.getHeight() + deltaY);
            }
            case SE -> {
                stage.setWidth(stage.getWidth() + deltaX);
                stage.setHeight(stage.getHeight() + deltaY);
            }
            case W -> {
                stage.setX(stage.getX() + deltaX);
                stage.setWidth(stage.getWidth() - deltaX);
            }
            case E -> stage.setWidth(stage.getWidth() + deltaX);
            case N -> {
                stage.setY(stage.getY() + deltaY);
                stage.setHeight(stage.getHeight() - deltaY);
            }
            case S -> stage.setHeight(stage.getHeight() + deltaY);
        }

        startX = event.getScreenX();
        startY = event.getScreenY();
    }

    private ResizeDirection determineResizeDirection(MouseEvent event, Pane pane) {
        boolean left = event.getX() < RESIZE_MARGIN;
        boolean right = event.getX() > pane.getWidth() - RESIZE_MARGIN;
        boolean top = event.getY() < RESIZE_MARGIN;
        boolean bottom = event.getY() > pane.getHeight() - RESIZE_MARGIN;

        if (left && top) return ResizeDirection.NW;
        if (right && top) return ResizeDirection.NE;
        if (left && bottom) return ResizeDirection.SW;
        if (right && bottom) return ResizeDirection.SE;
        if (left) return ResizeDirection.W;
        if (right) return ResizeDirection.E;
        if (top) return ResizeDirection.N;
        if (bottom) return ResizeDirection.S;

        return ResizeDirection.NONE;
    }

    private enum ResizeDirection {
        NONE, NW, NE, SW, SE, W, E, N, S
    }
}
