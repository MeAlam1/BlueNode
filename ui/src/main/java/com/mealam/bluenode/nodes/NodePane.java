package com.mealam.bluenode.nodes;

import com.mealam.bluenode.events.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.nodes.components.input.Input;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class NodePane extends Pane {

    private final Node nodeData;
    private Pane titleBar;
    private Text title;

    public NodePane(Node nodeData) {
        this.nodeData = nodeData;
        initializeNode();
    }

    private void initializeNode() {
        double x = nodeData.getProperties().getX() + CanvasDragHandler.getTranslateX();
        double y = nodeData.getProperties().getY() + CanvasDragHandler.getTranslateY();
        double width = nodeData.getProperties().getWidth();
        double height = nodeData.getProperties().getHeight();
        double titleBarHeight = height - 70;
        this.setPrefSize(width, height);
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.getStyleClass().add("node-pane");

        titleBar = new Pane();
        titleBar.getStyleClass().add("node-title-bar");
        for (String css : nodeData.getProperties().getCSS()) {
            titleBar.getStyleClass().add(css);
        }
        titleBar.setPrefSize(width, titleBarHeight);
        title = new Text(nodeData.getProperties().getTitle());
        title.setTranslateY(20);
        title.setTranslateX(5);
        titleBar.getChildren().add(title);

        this.getChildren().addAll(titleBar);
        createInputs(width, height);
    }

    private void createInputs(double width, double height) {
        for (Input input : nodeData.getProperties().getInputs()) {
            InputRenderer.render(this,
                    input,
                    nodeData,
                    this,
                    5,
                    (height / 2),
                    width,
                    height);
        }
    }

    public void updateSize() {
        double width = nodeData.getProperties().getWidth();
        double height = nodeData.getProperties().getHeight();
        double titleBarHeight = height - 70;
        this.setPrefSize(width, height);
        titleBar.setPrefSize(width, titleBarHeight);
        title.setText(nodeData.getProperties().getTitle());
    }

    public Node getNodeData() {
        return nodeData;
    }
}