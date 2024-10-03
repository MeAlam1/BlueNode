package BlueNode.Nodes.Library;

import BlueNode.Nodes.Browser.PopupButtonProvider;
import BlueNode.Nodes.Links.ELinkType;
import BlueNode.Nodes.Links.Link;
import BlueNode.Nodes.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class NodeOne extends Node implements PopupButtonProvider {

    public NodeOne() {
        Label label = new Label("Node One");
        label.setStyle("-fx-background-color: #222222; -fx-padding: 10; -fx-border-color: #333333; -fx-border-width: 1; -fx-border-radius: 5; -fx-text-fill: white;");
        nodePane.getChildren().add(label);

        Link<Integer> createLink = new Link<>(ELinkType.CREATE, "Integer");
        createLink.create(nodePane, "right");

        Link<String> acceptLink = new Link<>(ELinkType.ACCEPT, "String");
        acceptLink.create(nodePane, "top");

        nodePane.setOnMouseClicked(event -> {
            Pane parentPane = (Pane) nodePane.getParent();
            if (parentPane != null) {
                parentPane.getChildren().remove(nodePane);
            }
        });
    }

    @Override
    public String getButtonText() {
        return "Node One Button";
    }

    @Override
    public Pane getNodePane() {
        return nodePane;
    }
}

