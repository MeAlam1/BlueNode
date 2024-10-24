package BlueNode.Nodes.Types;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import BlueNode.Nodes.Events.NodeEventHandler;
import BlueNode.Nodes.Interfaces.ISelectable;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Separator;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public abstract class AbstractNode implements ISelectable {
    protected VBox nodePane;
    protected HBox titleBar;
    protected Pane contentArea;
    private boolean isSelected;
    private static AbstractNode currentlySelectedNode = null;

    public static AbstractNode getCurrentlySelectedNode() {
        return currentlySelectedNode;
    }

    public static void setCurrentlySelectedNode(AbstractNode pNode) {
        currentlySelectedNode = pNode;
    }

    public AbstractNode() {
        nodePane = new VBox();
        titleBar = new HBox();
        contentArea = new Pane();
        isSelected = false;

        titleBar.setStyle("-fx-background-color: #222222; -fx-padding: 10; -fx-border-color: #333333; -fx-border-width: 1; -fx-border-radius: 5; -fx-text-fill: white;");
        contentArea.setStyle("-fx-background-color: #222222; -fx-padding: 10; -fx-border-color: #333333; -fx-border-width: 1; -fx-border-radius: 5;");

        Text title = new Text(getNodeTitle());
        title.setStyle("-fx-fill: white;");
        titleBar.getChildren().add(title);

        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: white; -fx-padding: 1;");

        nodePane.getChildren().addAll(titleBar, separator, contentArea);

        nodePane.setOnMouseClicked(event -> {
            NodeEventHandler.handleSelect(this);
        });

        nodePane.setOnKeyPressed(event -> {
            BaseLogger.log(ELogLevel.DEBUG, "Key pressed: " + event.getCode());
            NodeEventHandler.handleKeyPress(this, event.getCode());
        });

        nodePane.setFocusTraversable(true);
    }

    @Override
    public void select() {
        nodePane.setStyle("-fx-border-color: blue; -fx-border-width: 2;");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(5.0);
        dropShadow.setOffsetY(5.0);
        dropShadow.setColor(Color.DARKBLUE);
        nodePane.setEffect(dropShadow);
        nodePane.setOpacity(0.9);
        nodePane.requestFocus();
        isSelected = true;
    }

    @Override
    public void deselect() {
        nodePane.setStyle("");
        nodePane.setEffect(null);
        nodePane.setOpacity(1.0);
        isSelected = false;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    public abstract Pane getNodePane();

    protected abstract String getNodeTitle();
}