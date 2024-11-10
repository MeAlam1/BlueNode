package com.mealam.bluenode.contentbrowser;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

public class TreeCellFactory implements Callback<TreeView<String>, TreeCell<String>> {
    @Override
    public TreeCell<String> call(TreeView<String> treeView) {
        return new TreeCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("-fx-background-color: #2e2e2e;");
                } else {
                    setText(item);
                    setStyle("-fx-text-fill: white; -fx-background-color: #2e2e2e;");
                }
            }
        };
    }
}
