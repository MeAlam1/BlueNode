package com.mealam.bluenode.contentBrowser;

import com.mealam.bluenode.UIConstants;
import com.mealam.bluenode.utils.contentBrowser.TreeNode;
import java.io.File;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

public class ContentBrowser extends VBox {

    private final TreeView<String> treeView;

    public ContentBrowser() {
        treeView = new TreeView<>();
        treeView.setStyle("-fx-background: #2e2e2e; -fx-control-inner-background: #2e2e2e;");
        treeView.setCellFactory(new TreeCellFactory());

        getChildren().add(treeView);
        setStyle("-fx-background-color: #2e2e2e;");
    }

    public void updateContent(File pFolder) {
        TreeNode rootNode = TreeNode.fromFolder(pFolder);
        if (rootNode != null) {
            treeView.setRoot(convertToTreeItem(rootNode));
            showContentBrowser();
        } else {
            collapseContent();
        }
    }

    private TreeItem<String> convertToTreeItem(TreeNode pNode) {
        TreeItem<String> treeItem = new TreeItem<>(pNode.getName());
        for (TreeNode child : pNode.getChildren()) {
            treeItem.getChildren().add(convertToTreeItem(child));
        }
        return treeItem;
    }

    private void collapseContent() {
        treeView.setRoot(null);
        hideContentBrowser();
    }

    public static void showContentBrowser() {
        if (!UIConstants.MAIN_ROOT.getChildren().contains(UIConstants.CONTENT_BROWSER)) {
            UIConstants.MAIN_ROOT.setBottom(UIConstants.CONTENT_BROWSER);
            UIConstants.CONTENT_BROWSER.prefHeightProperty().bind(UIConstants.MAIN_ROOT.heightProperty().divide(4));
            UIConstants.MAIN_CANVAS.heightProperty().bind(UIConstants.MAIN_ROOT.heightProperty().subtract(UIConstants.CONTENT_BROWSER.heightProperty()));
        }
    }

    public static void hideContentBrowser() {
        UIConstants.MAIN_ROOT.getChildren().remove(UIConstants.CONTENT_BROWSER);
        UIConstants.MAIN_CANVAS.heightProperty().bind(UIConstants.MAIN_ROOT.heightProperty());
        UIConstants.MAIN_CANVAS.widthProperty().bind(UIConstants.MAIN_ROOT.widthProperty());
    }
}
