package com.mealam.bluenode.utils.contentBrowser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    private final String name;
    private final List<TreeNode> children = new ArrayList<>();

    public TreeNode(String pName) {
        this.name = pName;
    }

    public String getName() {
        return name;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public static TreeNode fromFolder(File pFolder) {
        if (pFolder == null || !pFolder.isDirectory()) return null;

        TreeNode root = new TreeNode(pFolder.getName());
        populateTree(root, pFolder);
        return root;
    }

    private static void populateTree(TreeNode pParent, File pFolder) {
        File[] files = pFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                TreeNode childNode = new TreeNode(file.getName());
                pParent.getChildren().add(childNode);
                if (file.isDirectory()) {
                    populateTree(childNode, file);
                }
            }
        }
    }
}
