package com.mealam.bluenode.events;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WindowResizer {

    private static final int RESIZE_MARGIN = 5;
    private ResizeDirection resizeDirection = ResizeDirection.NONE;
    private double startX;
    private double startY;

    public void enableWindowResize(Stage pStage, Pane pPane) {
        pPane.setOnMouseMoved(event -> updateCursor(event, pPane));
        pPane.setOnMousePressed(this::mousePressed);
        pPane.setOnMouseDragged(event -> resizeWindow(event, pStage, pPane));
        pPane.setOnMouseReleased(event -> resizeDirection = ResizeDirection.NONE);
    }

    private void updateCursor(MouseEvent pEvent, Pane pPane) {
        resizeDirection = determineResizeDirection(pEvent, pPane);

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

        pPane.setCursor(cursor);
    }

    private void mousePressed(MouseEvent pEvent) {
        startX = pEvent.getScreenX();
        startY = pEvent.getScreenY();
    }

    private void resizeWindow(MouseEvent pEvent, Stage pStage, Pane pPane) {
        if (resizeDirection == ResizeDirection.NONE) return;

        double deltaX = pEvent.getScreenX() - startX;
        double deltaY = pEvent.getScreenY() - startY;

        switch (resizeDirection) {
            case NW -> {
                pStage.setX(pStage.getX() + deltaX);
                pStage.setY(pStage.getY() + deltaY);
                pStage.setWidth(pStage.getWidth() - deltaX);
                pStage.setHeight(pStage.getHeight() - deltaY);
            }
            case NE -> {
                pStage.setY(pStage.getY() + deltaY);
                pStage.setWidth(pStage.getWidth() + deltaX);
                pStage.setHeight(pStage.getHeight() - deltaY);
            }
            case SW -> {
                pStage.setX(pStage.getX() + deltaX);
                pStage.setWidth(pStage.getWidth() - deltaX);
                pStage.setHeight(pStage.getHeight() + deltaY);
            }
            case SE -> {
                pStage.setWidth(pStage.getWidth() + deltaX);
                pStage.setHeight(pStage.getHeight() + deltaY);
            }
            case W -> {
                pStage.setX(pStage.getX() + deltaX);
                pStage.setWidth(pStage.getWidth() - deltaX);
            }
            case E -> pStage.setWidth(pStage.getWidth() + deltaX);
            case N -> {
                pStage.setY(pStage.getY() + deltaY);
                pStage.setHeight(pStage.getHeight() - deltaY);
            }
            case S -> pStage.setHeight(pStage.getHeight() + deltaY);
        }

        startX = pEvent.getScreenX();
        startY = pEvent.getScreenY();
    }

    private ResizeDirection determineResizeDirection(MouseEvent pEvent, Pane pPane) {
        boolean left = pEvent.getX() < RESIZE_MARGIN;
        boolean right = pEvent.getX() > pPane.getWidth() - RESIZE_MARGIN;
        boolean top = pEvent.getY() < RESIZE_MARGIN;
        boolean bottom = pEvent.getY() > pPane.getHeight() - RESIZE_MARGIN;

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
