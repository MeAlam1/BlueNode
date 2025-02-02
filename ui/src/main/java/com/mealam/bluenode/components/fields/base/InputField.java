package com.mealam.bluenode.components.fields.base;

import com.mealam.bluenode.handlers.mainPanel.CanvasDragHandler;
import com.mealam.bluenode.nodes.Node;
import com.mealam.bluenode.nodes.NodeRenderer;
import com.mealam.bluenode.nodes.components.input.Input;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public abstract class InputField extends TextField {

    private final Input input;

    public InputField(GraphicsContext pGraphicsContext, Pane pPane, Input pInput, Node pNode) {
        this.input = pInput;

        this.setContextMenu(new javafx.scene.control.ContextMenu());
        configureField();
        addEventListeners(pGraphicsContext, pPane, pNode);
    }

    private void configureField() {
        this.setText(input.getProperties().getDefaultValue());
        this.setPromptText(input.getProperties().getName());
        this.getStyleClass().add("input-field");
    }

    private void addEventListeners(GraphicsContext pGraphicsContext, Pane pPane, Node pNode) {
        this.setOnMousePressed(pEvent -> ContextMenu.showContextMenu(this, pEvent));
        this.setOnAction(pEvent -> saveValue());
        this.focusedProperty().addListener((pObservableValue, pOldFocused, pNewFocused) -> {
            if (!pNewFocused) saveValue();
        });

        textProperty().addListener((observable, oldValue, newValue) -> {
            renderNewText(pGraphicsContext, pPane, pNode, newValue);
        });
    }

    private void renderNewText(GraphicsContext pGraphicsContext, Pane pPane, Node pNode, String newValue) {
        Text text = new Text(newValue);
        text.setFont(this.getFont());
        double textWidth = text.getLayoutBounds().getWidth();
        double padding = this.getInsets().getLeft() + this.getInsets().getRight();
        double newWidth = Math.max(40, textWidth + padding);

        pNode.updateSize();
        pGraphicsContext.clearRect(pNode.getProperties().getX() + CanvasDragHandler.getTranslateX(),
                pNode.getProperties().getY() + CanvasDragHandler.getTranslateY(),
                pNode.getProperties().getWidth(),
                pNode.getProperties().getHeight());
        NodeRenderer.render(pGraphicsContext, pPane, pNode, CanvasDragHandler.getTranslateX(), CanvasDragHandler.getTranslateY());
        setPrefWidth(newWidth);
        input.getProperties().setWidth(newWidth);
    }

    private void saveValue() {
        input.getProperties().setDefaultValue(this.getText());
        if (this.getParent() != null) {
            this.getParent().requestFocus();
        }
    }

    protected abstract boolean validate(String pText);

    @Override
    public void replaceText(int pStart, int pEnd, String pText) {
        if (validate(pText)) {
            super.replaceText(pStart, pEnd, pText);
        }
    }

    @Override
    public void replaceSelection(String pText) {
        if (validate(pText)) {
            super.replaceSelection(pText);
        }
    }
}
