package com.mealam.bluenode.nodes.library;

import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.NodeFactory;
import com.mealam.bluenode.nodes.NodeManager;
import com.mealam.bluenode.utils.nodes.NodeCategoryUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class NodeLibrary {

    public static void createPopup(Stage pParentStage, List<Node> pNodes, MouseEvent pEvent) {
        Popup popup = new Popup();
        VBox vBox = new VBox();
        vBox.getStyleClass().add("node-library-popup");

        Map<String, TitledPane> categoryMap = new HashMap<>();

        for (Node node : pNodes) {
            List<String> categories = NodeCategoryUtils.getCategories(node.getProperties().getCategory());

            if (categories.isEmpty()) continue;

            TitledPane currentPane = categoryMap.computeIfAbsent(categories.getFirst(), NodeLibrary::createTitledPane);

            for (int i = 1; i < categories.size(); i++) {
                String subCategory = categories.get(i);
                currentPane = getOrCreateSubPane(currentPane, subCategory);
            }

            Button nodeButton = new Button(node.getProperties().getTitle());
            nodeButton.setMaxWidth(Double.MAX_VALUE);
            nodeButton.getStyleClass().add("node-library-popup-button");
            nodeButton.setOnAction(actionEvent -> {
                NodeManager.placeNode(NodeFactory.createNode(node), pEvent.getX(), pEvent.getY());
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

        popup.show(pParentStage, pEvent.getScreenX(), pEvent.getScreenY());
    }

    private static TitledPane createTitledPane(String pTitle) {
        TitledPane titledPane = new TitledPane(pTitle, new VBox());
        titledPane.setExpanded(false);
        titledPane.getStyleClass().add("node-library-popup-category");
        return titledPane;
    }

    private static TitledPane getOrCreateSubPane(TitledPane pParentTitledPane, String pSubCategory) {
        if (pParentTitledPane.getContent() instanceof VBox contentBox) {
            for (javafx.scene.Node node : contentBox.getChildren()) {
                if (node instanceof TitledPane subPane && subPane.getText().equals(pSubCategory)) {
                    return subPane;
                }
            }
        }

        TitledPane newSubPane = createTitledPane(pSubCategory);
        if (pParentTitledPane.getContent() instanceof VBox) {
            ((VBox) pParentTitledPane.getContent()).getChildren().add(newSubPane);
        } else {
            VBox contentBox = new VBox(newSubPane);
            pParentTitledPane.setContent(contentBox);
        }
        return newSubPane;
    }
}
