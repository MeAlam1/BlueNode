package com.mealam.bluenode.nodes.library;

import com.mealam.bluenode.nodes.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.List;

public class NodeLibrary {

    public static void createPopup(Stage parent, List<Node> items, double x, double y) {
        VBox vBox = new VBox(5);
        vBox.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: black; -fx-border-width: 1;");

        for (Node item : items) {
            Button button = new Button(item.getProperties().getTitle());
            button.setMaxWidth(Double.MAX_VALUE);
            button.setOnAction(event -> {
                System.out.println("Clicked: " + item.getProperties().getTitle());
            });
            vBox.getChildren().add(button);
        }

        Popup popup = new Popup();
        popup.getContent().add(vBox);

        popup.setX(x);
        popup.setY(y);

        popup.show(parent);
    }
}
