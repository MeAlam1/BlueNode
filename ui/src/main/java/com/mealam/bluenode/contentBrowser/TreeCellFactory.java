package com.mealam.bluenode.contentBrowser;

import com.mealam.bluenode.ColorConstants;
import com.mealam.bluenode.utils.conversion.ColorUtils;
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
                if (pEmpty || pItem == null) {
                    setText(null);
                    setStyle("-fx-background-color: " + ColorUtils.colorToString(ColorConstants.BACKGROUND_COLOR));
                } else {
                    setText(pItem);
                    setStyle("-fx-text-fill: white; -fx-background-color: " + ColorUtils.colorToString(ColorConstants.BACKGROUND_COLOR));
                }
            }
        };
    }
}
