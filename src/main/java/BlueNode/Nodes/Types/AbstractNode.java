package BlueNode.Nodes.Types;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import BlueNode.Nodes.Interfaces.ISelectable;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public abstract class AbstractNode implements ISelectable {
    protected Pane nodePane;
    private boolean isSelected;
    private static AbstractNode currentlySelectedNode = null;

    public AbstractNode() {
        nodePane = new Pane();
        isSelected = false;

        nodePane.setOnMouseClicked(event -> {
            if (currentlySelectedNode != null && currentlySelectedNode != this) {
                currentlySelectedNode.deselect();
            }

            if (currentlySelectedNode != this) {
                this.select();
                currentlySelectedNode = this;
            }
        });

        nodePane.setOnKeyPressed(event -> {
            BaseLogger.log(ELogLevel.DEBUG, "Key pressed: " + event.getCode());
            if (isSelected && (event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE)) {
                Pane parentPane = (Pane) nodePane.getParent();
                if (parentPane != null) {
                    BaseLogger.log(ELogLevel.INFO, "Node removed");
                    parentPane.getChildren().remove(nodePane);
                    currentlySelectedNode = null;
                }
            }
        });

        nodePane.setFocusTraversable(true);
    }

    @Override
    public void select() {
        nodePane.setStyle("-fx-border-color: blue; -fx-border-width: 2; -fx-background-color: lightblue;");
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
}
