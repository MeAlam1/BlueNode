package BlueNode.Nodes.Library;

import BlueNode.Nodes.Browser.PopupButtonProvider;
import BlueNode.Nodes.Types.MathNode;
import javafx.scene.layout.Pane;

public class NodeOne extends MathNode implements PopupButtonProvider {

    @Override
    public String getButtonText() {
        return "Node One Button";
    }

    @Override
    public Pane getNodePane() {
        return nodePane;
    }

    @Override
    protected String getNodeTitle() {
        return "Node One";
    }
}

