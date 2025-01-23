package com.mealam.bluenode.nodes;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mealam.bluenode.mainPanel.MainPanel;
import com.mealam.bluenode.nodes.categories.MathNode;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import com.mealam.bluenode.utils.nodes.NodeIDGenerator;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NodePlacementTest {

    private List<Node> nodes;

    @BeforeEach
    public void setUp() {
        nodes = new ArrayList<>();
        BaseLogger.log(BaseLogLevel.INFO, "Test setup: New node list created.");
    }

    @Test
    public void testNodePlacement_Overlap() {
        Node firstNode = new MathNode(150, 100, 100, 100, NodeIDGenerator.generateID("MathNode"));
        nodes.add(firstNode);
        BaseLogger.log(BaseLogLevel.INFO, "First node placed at \n" +
                "(X: " + firstNode.getX() + ", Y: " + firstNode.getY() + ")");

        Node secondNode = new MathNode(150, 100, 120, 120, NodeIDGenerator.generateID("MathNode"));
        nodes.add(secondNode);
        BaseLogger.log(BaseLogLevel.INFO, "Second node placed at \n" +
                "(X: " + secondNode.getX() + ", Y: " + secondNode.getY() + ")");

        for (Node node : nodes) {
            if (!node.equals(secondNode)) {
                assertTrue(MainPanel.isOverlapping(secondNode, node),
                        "Second node overlaps with an existing node");
            }
        }
    }

    @Test
    public void testNodePlacement_withAdjustmentLoop() {
        Node firstNode = new MathNode(150, 100, 100, 100, NodeIDGenerator.generateID("MathNode"));
        nodes.add(firstNode);
        BaseLogger.log(BaseLogLevel.INFO, "First node placed at \n" +
                "(X: " + firstNode.getX() + ", Y: " + firstNode.getY() + ")");

        double x = 120;
        double y = 120;
        double width = 150;
        double height = 100;

        double adjustedX = adjustPosition(x, y, width, height);
        double adjustedY = adjustPosition(y, x, height, width);
        BaseLogger.log(BaseLogLevel.INFO, "Adjusted position for second node: \n" +
                "(X: " + adjustedX + ", Y: " + adjustedY + ")");

        Node secondNode = new MathNode(width, height, adjustedX, adjustedY, NodeIDGenerator.generateID("MathNode"));
        nodes.add(secondNode);

        assertTrue(adjustedX != x || adjustedY != y, "Node position should have been adjusted.");
        assertFalse(MainPanel.isOverlapping(secondNode, firstNode), "Second node overlaps with the first node after adjustment.");
    }

    private double adjustPosition(double pX, double pY, double pWidth, double pHeight) {
        double newX = pX;
        double newY = pY;
        int maxAttempts = 100;
        int attempt = 0;

        while (attempt < maxAttempts) {
            boolean overlaps = false;
            for (Node node : nodes) {
                if (MainPanel.isOverlapping(new MathNode(pWidth, pHeight, newX, newY, NodeIDGenerator.generateID("MathNode")), node)) {
                    overlaps = true;
                    break;
                }
            }
            if (!overlaps) break;

            BaseLogger.log(BaseLogLevel.INFO, "Position: \n" +
                    "(X: " + newX + ", Y: " + newY + ") \n" +
                    "overlaps with other nodes, adjusting...");
            newX += 10;
            newY += 10;
            attempt++;
        }
        return newX;
    }
}
