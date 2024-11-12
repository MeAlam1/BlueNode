package com.mealam.bluenode.contentBrowser;

import com.mealam.bluenode.UIController;
import com.mealam.bluenode.utils.contentBrowser.TreeNode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

import java.io.File;

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
        if (!UIController.MAIN_ROOT.getChildren().contains(UIController.CONTENT_BROWSER)) {
            UIController.MAIN_ROOT.setBottom(UIController.CONTENT_BROWSER);
            UIController.CONTENT_BROWSER.prefHeightProperty().bind(UIController.MAIN_ROOT.heightProperty().divide(4));
            UIController.MAIN_CANVAS.heightProperty().bind(UIController.MAIN_ROOT.heightProperty().subtract(UIController.CONTENT_BROWSER.heightProperty()));
        }
    }

    public static void hideContentBrowser() {
        UIController.MAIN_ROOT.getChildren().remove(UIController.CONTENT_BROWSER);
        UIController.MAIN_CANVAS.heightProperty().bind(UIController.MAIN_ROOT.heightProperty());
        UIController.MAIN_CANVAS.widthProperty().bind(UIController.MAIN_ROOT.widthProperty());
    }
}
