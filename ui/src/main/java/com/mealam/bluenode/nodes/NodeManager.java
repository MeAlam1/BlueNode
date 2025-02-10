package com.mealam.bluenode.nodes;

import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import com.mealam.bluenode.utils.nodes.NodeIDGenerator;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class NodeManager {

    private static Pane overlayPane;
    private static final List<Node> nodes = new ArrayList<>();

    public static boolean isNodeAtLocation(double x, double y, double width, double height) {
        for (Node node : nodes) {
            double nodeX = node.getProperties().getX();
            double nodeY = node.getProperties().getY();
            double nodeWidth = node.getProperties().getWidth();
            double nodeHeight = node.getProperties().getHeight();

            if (x < nodeX + nodeWidth && x + width > nodeX && y < nodeY + nodeHeight && y + height > nodeY) {
                return true;
            }
        }
        return false;
    }

    public static void placeNode(Node node) {
        if (!isNodeAtLocation(node.getProperties().getX(), node.getProperties().getY(),
                node.getProperties().getWidth(), node.getProperties().getHeight())) {
            nodes.add(node);
            NodePane nodePane = new NodePane(node);
            overlayPane.getChildren().add(nodePane);

            BaseLogger.log(BaseLogLevel.INFO, node.getProperties().toString());
            BaseLogger.log(BaseLogLevel.INFO, "Node Decrypted ID: " + NodeIDGenerator.decryptID(node.getProperties().getId()));
        } else {
            BaseLogger.log(BaseLogLevel.WARNING, "No suitable position found for new node.");
        }
    }

    public static void placeNode(Node node, double x, double y) {
        node.getProperties().setX(x);
        node.getProperties().setY(y);
        placeNode(node);
    }

    public static void setOverlayPane(Pane pOverlayPane) {
        overlayPane = pOverlayPane;
    }

    public static List<Node> getNodes() {
        return nodes;
    }
}
