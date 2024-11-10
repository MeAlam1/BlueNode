package com.mealam.bluenode.contentbrowser;

import com.mealam.bluenode.JavaFXConstants;
import com.mealam.bluenode.utils.contentbrowser.TreeNode;
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

    public void updateContent(File folder) {
        TreeNode rootNode = TreeNode.fromFolder(folder);
        if (rootNode != null) {
            treeView.setRoot(convertToTreeItem(rootNode));
            showContentBrowser();
        } else {
            collapseContent();
        }
    }

    private TreeItem<String> convertToTreeItem(TreeNode node) {
        TreeItem<String> treeItem = new TreeItem<>(node.getName());
        for (TreeNode child : node.getChildren()) {
            treeItem.getChildren().add(convertToTreeItem(child));
        }
        return treeItem;
    }

    private void collapseContent() {
        treeView.setRoot(null);
        hideContentBrowser();
    }

    public static void showContentBrowser() {
        if (!JavaFXConstants.mainRoot.getChildren().contains(JavaFXConstants.contentBrowser)) {
            JavaFXConstants.mainRoot.setBottom(JavaFXConstants.contentBrowser);
            JavaFXConstants.contentBrowser.prefHeightProperty().bind(JavaFXConstants.mainRoot.heightProperty().divide(4));
            JavaFXConstants.mainCanvas.heightProperty().bind(JavaFXConstants.mainRoot.heightProperty().subtract(JavaFXConstants.contentBrowser.heightProperty()));
        }
    }

    public static void hideContentBrowser() {
        JavaFXConstants.mainRoot.getChildren().remove(JavaFXConstants.contentBrowser);
        JavaFXConstants.mainCanvas.heightProperty().bind(JavaFXConstants.mainRoot.heightProperty());
        JavaFXConstants.mainCanvas.widthProperty().bind(JavaFXConstants.mainRoot.widthProperty());
    }
}
