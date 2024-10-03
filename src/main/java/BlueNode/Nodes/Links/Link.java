package BlueNode.Nodes.Links;

import javafx.geometry.Bounds;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


public class Link<T> {
    private final ELinkType linkType;
    private final String dataType;
    private Pane nodePane;
    private Circle linkVisual;
    private final Line connectionLine;
    private boolean isDragging = false;

    public Link(ELinkType type, String dataType) {
        this.linkType = type;
        this.dataType = dataType;
        this.connectionLine = new Line();
        connectionLine.setStrokeWidth(2);
        connectionLine.setStroke(Color.GRAY);
    }

    public void create(Pane nodePane, String position) {
        this.nodePane = nodePane;

        linkVisual = new Circle(5);
        linkVisual.setFill(linkType == ELinkType.CREATE ? Color.BLUE : Color.GREEN);

        positionLinkAtEdge(nodePane, position);

        nodePane.getChildren().add(linkVisual);

        Text dataLabel = new Text(dataType);
        dataLabel.setFill(Color.WHITE);
        dataLabel.setLayoutX(linkVisual.getLayoutX() + 10);
        dataLabel.setLayoutY(linkVisual.getLayoutY());
        nodePane.getChildren().add(dataLabel);

        if (linkType == ELinkType.CREATE) {
            enableLinkCreation();
        }
    }

    private void positionLinkAtEdge(Pane nodePane, String position) {
        Bounds bounds = nodePane.getBoundsInLocal();
        switch (position.toLowerCase()) {
            case "top":
                linkVisual.setLayoutX(bounds.getWidth() / 2);
                linkVisual.setLayoutY(0);
                break;
            case "bottom":
                linkVisual.setLayoutX(bounds.getWidth() / 2);
                linkVisual.setLayoutY(bounds.getHeight());
                break;
            case "left":
                linkVisual.setLayoutX(0);
                linkVisual.setLayoutY(bounds.getHeight() / 2);
                break;
            case "right":
                linkVisual.setLayoutX(bounds.getWidth());
                linkVisual.setLayoutY(bounds.getHeight() / 2);
                break;
        }
    }

    private void enableLinkCreation() {
        linkVisual.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                isDragging = true;
                connectionLine.setStartX(linkVisual.getLayoutX());
                connectionLine.setStartY(linkVisual.getLayoutY());
                nodePane.getChildren().add(connectionLine);
            }
        });

        linkVisual.setOnMouseDragged(event -> {
            if (isDragging) {
                connectionLine.setEndX(event.getX());
                connectionLine.setEndY(event.getY());
            }
        });

        linkVisual.setOnMouseReleased(event -> {
            if (isDragging) {
                isDragging = false;
                nodePane.getChildren().remove(connectionLine);
                // TODO, you could add logic to detect if the end position of the line
                // intersects with an "accept" link and finalize the connection.
            }
        });
    }

    public ELinkType getLinkType() {
        return linkType;
    }

    public String getDataType() {
        return dataType;
    }

    public Circle getLinkVisual() {
        return linkVisual;
    }
}
