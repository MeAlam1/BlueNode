package com.mealam.bluenode.nodes;

import com.mealam.bluenode.events.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.nodes.components.input.Input;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class NodePane extends Pane {

    private final Node node;
    private Pane titleBar;
    private Text title;

    public NodePane(Node pNode) {
        this.node = pNode;
        initializeNode();
    }

    private void initializeNode() {
        double x = node.getProperties().getX() + CanvasDragHandler.getTranslateX();
        double y = node.getProperties().getY() + CanvasDragHandler.getTranslateY();
        double width = node.getProperties().getWidth();
        double height = node.getProperties().getHeight();
        double titleBarHeight = height - 70;
        this.setPrefSize(width, height);
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.getStyleClass().add("node-pane");

        titleBar = new Pane();
        titleBar.getStyleClass().add("node-title-bar");
        for (String css : node.getProperties().getCSS()) {
            titleBar.getStyleClass().add(css);
        }
        titleBar.setPrefSize(width, titleBarHeight);
        title = new Text(node.getProperties().getTitle());
        title.setTranslateY(20);
        title.setTranslateX(5);
        titleBar.getChildren().add(title);

        this.getChildren().addAll(titleBar);
        createInputs(width, height);
    }

    private void createInputs(double pWidth, double pHeight) {
        for (Input input : node.getProperties().getInputs()) {
            InputRenderer.render(this,
                    input,
                    node,
                    this,
                    5,
                    (pHeight / 2),
                    pWidth,
                    pHeight);
        }
    }

    public void updateSize() {
        double width = node.getProperties().getWidth();
        double height = node.getProperties().getHeight();
        double titleBarHeight = height - 70;
        this.setPrefSize(width, height);
        titleBar.setPrefSize(width, titleBarHeight);
        title.setText(node.getProperties().getTitle());
    }

    public Node getNode() {
        return node;
    }
}
