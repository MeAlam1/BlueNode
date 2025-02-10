package com.mealam.bluenode.contentBrowser;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

public class TreeCellFactory implements Callback<TreeView<String>, TreeCell<String>> {

    @Override
    public TreeCell<String> call(TreeView<String> pTreeView) {
        return new TreeCell<>() {

            @Override
            protected void updateItem(String pItem, boolean pEmpty) {
                super.updateItem(pItem, pEmpty);
                getStyleClass().add("tree-cell");
                if (pEmpty || pItem == null) {
                    setText(null);
                } else {
                    setText(pItem);
                }
            }
        };
    }
}
