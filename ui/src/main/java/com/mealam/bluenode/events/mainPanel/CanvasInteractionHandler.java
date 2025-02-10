package com.mealam.bluenode.events.mainPanel;

import com.mealam.bluenode.UIConstants;
import com.mealam.bluenode.nodes.NodeManager;
import com.mealam.bluenode.nodes.library.NodeLibrary;
import com.mealam.bluenode.utils.nodes.NodeLoaderUtils;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CanvasInteractionHandler {

    public CanvasInteractionHandler(Stage primaryStage) {
        UIConstants.MAIN_CANVAS.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                if (!NodeManager.isNodeAtLocation(event.getX(), event.getY(), 150, 100)) {
                    NodeLibrary.createPopup(primaryStage, NodeLoaderUtils.getAllNodes(), event);
                }
            }
        });
    }
}
