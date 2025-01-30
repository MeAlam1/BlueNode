package com.mealam.bluenode.nodes.library;

import com.mealam.bluenode.handlers.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.mainPanel.GridDrawer;
import com.mealam.bluenode.mainPanel.MainPanel;
import com.mealam.bluenode.nodes.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.List;

public class NodeLibrary {

    public static void createPopup(Stage parent, List<Node> items, MouseEvent event) {
        VBox vBox = new VBox(5);
        vBox.getStyleClass().add("node-library-popup");

        double screenX = event.getScreenX();
        double screenY = event.getScreenY();

        Popup popup = new Popup();

        for (Node item : items) {
            Button button = getButtons(event, item, popup);
            button.getStyleClass().add("node-library-popup-button");
            vBox.getChildren().add(button);
        }

        popup.getContent().add(vBox);
        popup.show(parent);

        double popupWidth = vBox.getWidth();
        double popupHeight = vBox.getHeight();

        popup.hide();

        double centeredX = screenX - (popupWidth / 2);
        double centeredY = screenY - (popupHeight / 2);

        popup.setX(centeredX);
        popup.setY(centeredY);

        popup.show(parent);
    }

    private static Button getButtons(MouseEvent event, Node item, Popup popup) {
        Button button = new Button(item.getProperties().getTitle());
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(actionEvent -> {
            System.out.println("Clicked: " + item.getProperties().getTitle());
            double mouseX = event.getX();
            double mouseY = event.getY();

            double nodeWidth = 150;
            double nodeHeight = 100;

            double centerX = mouseX - (nodeWidth / 2);
            double centerY = mouseY - (nodeHeight / 2);

            double snappedX = GridDrawer.snapToGrid(centerX - CanvasDragHandler.getTranslateX());
            double snappedY = GridDrawer.snapToGrid(centerY - CanvasDragHandler.getTranslateY());
            Node getNode = MainPanel.getNewNode(item);
            MainPanel.placeNode(getNode, snappedX, snappedY);
            popup.hide();
        });
        return button;
    }
}