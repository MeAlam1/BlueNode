package com.mealam.bluenode.nodes;

import com.mealam.bluenode.events.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.nodes.components.input.Input;
import com.mealam.bluenode.nodes.components.output.Output;
import com.mealam.bluenode.nodes.links.InputLink;
import com.mealam.bluenode.nodes.links.OutputLink;
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
        double titleBarHeight = height - 90;
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
        title.setTranslateX(25);
        titleBar.getChildren().add(title);

        OutputLink outputLink = new OutputLink(width, titleBarHeight / 2, this);
        titleBar.getChildren().add(outputLink);

        InputLink inputLink = new InputLink(0, titleBarHeight / 2, this);
        titleBar.getChildren().add(inputLink);

        this.getChildren().addAll(titleBar);
        if (node.getProperties().getInputs() != null && !node.getProperties().getInputs().isEmpty()) {
            createInputs(width, height);
        }

        if (node.getProperties().getOutputs() != null && !node.getProperties().getOutputs().isEmpty()) {
            createOutputs(width, height);
        }
    }

    private void createInputs(double pWidth, double pHeight) {
        double yOffset = pHeight / 2;

        for (Input input : node.getProperties().getInputs()) {
            InputRenderer.render(this,
                    input,
                    node,
                    this,
                    5,
                    yOffset,
                    pWidth,
                    pHeight);

            yOffset += 30;
        }
    }

    private void createOutputs(double pWidth, double pHeight) {
        double yOffset = pHeight / 2 + 20;

        for (Output output : node.getProperties().getOutputs()) {
            OutputRenderer.render(this,
                    output,
                    node,
                    this,
                    pWidth,
                    yOffset,
                    pWidth,
                    pHeight);

            yOffset += 30;
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
