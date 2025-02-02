package com.mealam.bluenode.nodes.library;

import com.mealam.bluenode.mainPanel.MainPanel;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.NodeFactory;
import com.mealam.bluenode.nodes.category.NodeCategoryHandler;
import java.util.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class NodeLibrary {

    public static void createPopup(Stage parent, List<Node> items, MouseEvent event) {
        Popup popup = new Popup();
        VBox vBox = new VBox();

        Map<String, TitledPane> categoryMap = new HashMap<>();

        for (Node item : items) {
            List<String> categories = NodeCategoryHandler.getCategories(item.getProperties().getCategory());

            if (categories.isEmpty()) continue;

            TitledPane currentPane = categoryMap.computeIfAbsent(categories.getFirst(), NodeLibrary::createTitledPane);

            for (int i = 1; i < categories.size(); i++) {
                String subCategory = categories.get(i);
                currentPane = getOrCreateSubPane(currentPane, subCategory);
            }

            Button nodeButton = new Button(item.getProperties().getTitle());
            nodeButton.setMaxWidth(Double.MAX_VALUE);
            nodeButton.setOnAction(actionEvent -> {
                MainPanel.placeNode(NodeFactory.createNode(item), event.getX(), event.getY());
                popup.hide();
            });

            if (currentPane.getContent() instanceof VBox) {
                ((VBox) currentPane.getContent()).getChildren().add(nodeButton);
            } else {
                VBox contentBox = new VBox(nodeButton);
                currentPane.setContent(contentBox);
            }
        }

        vBox.getChildren().addAll(categoryMap.values());

        popup.getContent().add(vBox);

        popup.setAutoHide(true);

        popup.show(parent, event.getScreenX(), event.getScreenY());
    }

    private static TitledPane createTitledPane(String title) {
        TitledPane titledPane = new TitledPane(title, new VBox());
        titledPane.setExpanded(false);
        return titledPane;
    }

    private static TitledPane getOrCreateSubPane(TitledPane parent, String subCategory) {
        if (parent.getContent() instanceof VBox contentBox) {
            for (javafx.scene.Node node : contentBox.getChildren()) {
                if (node instanceof TitledPane subPane && subPane.getText().equals(subCategory)) {
                    return subPane;
                }
            }
        }

        TitledPane newSubPane = createTitledPane(subCategory);
        if (parent.getContent() instanceof VBox) {
            ((VBox) parent.getContent()).getChildren().add(newSubPane);
        } else {
            VBox contentBox = new VBox(newSubPane);
            parent.setContent(contentBox);
        }
        return newSubPane;
    }
}
