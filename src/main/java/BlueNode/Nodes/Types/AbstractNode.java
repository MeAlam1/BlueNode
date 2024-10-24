package BlueNode.Nodes.Types;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import BlueNode.Nodes.Events.NodeEventHandler;
import BlueNode.Nodes.Interfaces.ISelectable;
import BlueNode.Nodes.Style.AbstractNodeStyle;
import BlueNode.UI.MainPanel.GridDrawer;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

        double nodesWidth = 148.0;
        double nodesHeight = 85.0;

        nodePane.setStyle(AbstractNodeStyle.getDefaultBorderStyle() + "-fx-padding: 0; -fx-spacing: 0;");

        titleBar.setStyle("-fx-background-color: " + AbstractNodeStyle.TITLE_BAR_COLOR + "; -fx-padding: 10; -fx-background-radius: 10 10 0 0;");
        titleBar.setMinWidth(nodesWidth);
        titleBar.setPrefWidth(nodesWidth);
        titleBar.setMaxWidth(nodesWidth);

        contentArea.setStyle(AbstractNodeStyle.getContentAreaStyle());
        contentArea.setMinSize(nodesWidth, nodesHeight);
        contentArea.setPrefSize(nodesWidth, nodesHeight);
        contentArea.setMaxSize(nodesWidth, nodesHeight);

        Text title = new Text(getNodeTitle());
        title.setStyle(AbstractNodeStyle.TITLE_STYLE);
        titleBar.getChildren().add(title);

        nodePane.getChildren().addAll(titleBar, contentArea);
        nodePane.setSpacing(0);

        nodePane.requestLayout();

        nodePane.setOnMouseClicked(event -> NodeEventHandler.handleSelect(this));

        nodePane.setOnKeyPressed(event -> {
            BaseLogger.log(ELogLevel.DEBUG, "Key pressed: " + event.getCode());
            NodeEventHandler.handleKeyPress(this, event.getCode());
        });

        final double[] dragDelta = new double[2];

        nodePane.setOnMousePressed(event -> {
            dragDelta[0] = nodePane.getLayoutX() - event.getSceneX();
            dragDelta[1] = nodePane.getLayoutY() - event.getSceneY();
        });

        nodePane.setOnMouseDragged(event -> {
            nodePane.setLayoutX(event.getSceneX() + dragDelta[0]);
            nodePane.setLayoutY(event.getSceneY() + dragDelta[1]);
        });

        nodePane.setOnMouseReleased(event -> {
            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();

            double nodeX = nodePane.getLayoutX();
            double nodeY = nodePane.getLayoutY();
            double nodeWidth = nodePane.getWidth();
            double nodeHeight = nodePane.getHeight();

            double snappedX;
            double snappedY;

            boolean isCloserToLeft = mouseX < (nodeX + nodeWidth / 2);
            boolean isCloserToTop = mouseY < (nodeY + nodeHeight / 2);

            if (isCloserToLeft) {
                snappedX = GridDrawer.snapToGrid(nodeX);
            } else {
                snappedX = GridDrawer.snapToGrid(nodeX + nodeWidth) - nodeWidth;
            }

            if (isCloserToTop) {
                snappedY = GridDrawer.snapToGrid(nodeY);
            } else {
                snappedY = GridDrawer.snapToGrid(nodeY + nodeHeight) - nodeHeight;
            }

            nodePane.setLayoutX(snappedX);
            nodePane.setLayoutY(snappedY);
        });


        nodePane.setFocusTraversable(true);
    }


    @Override
    public void select() {
        nodePane.setStyle(AbstractNodeStyle.getSelectedBorderStyle());
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
        nodePane.setStyle(AbstractNodeStyle.getDefaultBorderStyle());
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
