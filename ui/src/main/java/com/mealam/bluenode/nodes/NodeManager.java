package com.mealam.bluenode.nodes;

import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import com.mealam.bluenode.utils.nodes.NodeIDGenerator;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Pane;

public class NodeManager {

    private static Pane overlayPane;
    private static final List<Node> nodes = new ArrayList<>();

    public static boolean isNodeAtLocation(double pX, double pY, double pWidth, double pHeight) {
        for (Node node : nodes) {
            double nodeX = node.getProperties().getX();
            double nodeY = node.getProperties().getY();
            double nodeWidth = node.getProperties().getWidth();
            double nodeHeight = node.getProperties().getHeight();

            if (pX < nodeX + nodeWidth && pX + pWidth > nodeX && pY < nodeY + nodeHeight && pY + pHeight > nodeY) {
                return true;
            }
        }
        return false;
    }

    public static void placeNode(Node pNode) {
        if (!isNodeAtLocation(pNode.getProperties().getX(), pNode.getProperties().getY(),
                pNode.getProperties().getWidth(), pNode.getProperties().getHeight())) {
            nodes.add(pNode);
            NodePane nodePane = new NodePane(pNode);
            overlayPane.getChildren().add(nodePane);

            BaseLogger.log(BaseLogLevel.INFO, pNode.getProperties().toString());
            BaseLogger.log(BaseLogLevel.INFO, "Node Decrypted ID: " + NodeIDGenerator.decryptID(pNode.getProperties().getId()));
        } else {
            BaseLogger.log(BaseLogLevel.WARNING, "No suitable position found for new node.");
        }
    }

    public static void placeNode(Node pNode, double pX, double pY) {
        pNode.getProperties().setX(pX);
        pNode.getProperties().setY(pY);
        placeNode(pNode);
    }

    public static void setOverlayPane(Pane pOverlayPane) {
        overlayPane = pOverlayPane;
    }

    public static List<Node> getNodes() {
        return nodes;
    }
}
