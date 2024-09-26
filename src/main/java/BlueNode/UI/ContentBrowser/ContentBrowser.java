package BlueNode.UI.ContentBrowser;

import BlueNode.BlueNode;
import BlueNode.UI.MainPanel.MainPanel;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.File;

public class ContentBrowser extends VBox {

    private final TreeView<String> treeView;

    public ContentBrowser() {
        treeView = new TreeView<>();
        treeView.setStyle("-fx-background: #2e2e2e; -fx-control-inner-background: #2e2e2e;");

        treeView.setCellFactory(new Callback<>() {
            @Override
            public TreeCell<String> call(TreeView<String> pParam) {
                return new TreeCell<>() {
                    @Override
                    protected void updateItem(String pItem, boolean pEmpty) {
                        super.updateItem(pItem, pEmpty);
                        if (pEmpty || pItem == null) {
                            setText(null);
                            setStyle("-fx-background-color: #2e2e2e;");
                        } else {
                            setText(pItem);
                            setStyle("-fx-text-fill: white; -fx-background-color: #2e2e2e;");
                        }
                    }
                };
            }
        });

        getChildren().add(treeView);
        setStyle("-fx-background-color: #2e2e2e;");
    }


    public void updateContent(File pFolder) {
        if (pFolder != null && pFolder.exists() && pFolder.isDirectory()) {
            TreeItem<String> rootItem = new TreeItem<>(pFolder.getName());
            rootItem.setExpanded(true);
            populateTree(rootItem, pFolder);
            treeView.setRoot(rootItem);
            showContentBrowser();
        } else {
            collapseContent();
        }
    }

    private void populateTree(TreeItem<String> pParentItem, File pFolder) {
        File[] files = pFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                TreeItem<String> item = new TreeItem<>(file.getName());
                pParentItem.getChildren().add(item);
                if (file.isDirectory()) {
                    populateTree(item, file);
                }
            }
        }
    }

    private void collapseContent() {
        treeView.setRoot(null);
        hideContentBrowser();
    }

    public static void showContentBrowser() {
        if (!BlueNode.root.getChildren().contains(BlueNode.contentBrowser)) {
            BlueNode.root.setBottom(BlueNode.contentBrowser);
            BlueNode.contentBrowser.prefHeightProperty().bind(BlueNode.root.heightProperty().divide(4));
            MainPanel.canvas.heightProperty().bind(BlueNode.root.heightProperty().subtract(BlueNode.contentBrowser.heightProperty()));
        }
    }

    public static void hideContentBrowser() {
        BlueNode.root.getChildren().remove(BlueNode.contentBrowser);
        MainPanel.canvas.heightProperty().bind(BlueNode.root.heightProperty());
        MainPanel.canvas.widthProperty().bind(BlueNode.root.widthProperty());
    }
}