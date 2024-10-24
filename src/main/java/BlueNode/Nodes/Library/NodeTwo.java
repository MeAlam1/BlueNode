package BlueNode.Nodes.Library;

import BlueNode.Nodes.Browser.PopupButtonProvider;
import BlueNode.Nodes.Types.AbstractNode;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class NodeTwo extends AbstractNode implements PopupButtonProvider {
    public NodeTwo() {
        Label label = new Label("Node Two");
        label.setStyle("-fx-background-color: #222222; -fx-padding: 10; -fx-border-color: #333333; -fx-border-width: 1; -fx-border-radius: 5; -fx-text-fill: white;");
        nodePane.getChildren().add(label);
    }

    @Override
    public String getButtonText() {
        return "Node Two Button";
    }

    @Override
    public Pane getNodePane() {
        return nodePane;
    }
}

