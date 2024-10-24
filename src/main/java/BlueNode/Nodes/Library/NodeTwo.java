package BlueNode.Nodes.Library;

import BlueNode.Nodes.Browser.PopupButtonProvider;
import BlueNode.Nodes.Types.AbstractNode;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class NodeTwo extends AbstractNode implements PopupButtonProvider {

    @Override
    public String getButtonText() {
        return "Node Two Button";
    }

    @Override
    public Pane getNodePane() {
        return nodePane;
    }

    @Override
    protected String getNodeTitle() {
        return "Node Two";
    }
}

