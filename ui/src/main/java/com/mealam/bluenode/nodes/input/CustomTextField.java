package com.mealam.bluenode.nodes.input;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/* OPTIMIZE: CustomTextField is not done, this is a test class and it works.
  * This class needs a lot more buttons like copy, paste, cut, etc.
  * This class needs another Color and some other details
 */
public class CustomTextField extends TextField {

    public CustomTextField() {
        this.setContextMenu(new ContextMenu());
        this.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Right-click
                showCustomMenu(event);
            }
        });
    }

    private void showCustomMenu(MouseEvent event) {
        Popup popup = new Popup();

        VBox menuBox = new VBox();
        menuBox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10;");
        menuBox.setSpacing(5);

        Text undoOption = new Text("Undo");
        undoOption.setOnMouseClicked(e -> {
            System.out.println("Undo clicked");
            popup.hide();
        });

        Text customOption = new Text("Custom Action");
        customOption.setOnMouseClicked(e -> {
            System.out.println("Custom action clicked");
            popup.hide();
        });

        menuBox.getChildren().addAll(undoOption, customOption);

        popup.getContent().add(menuBox);
        popup.setAutoHide(true);
        popup.show(this, event.getScreenX(), event.getScreenY());
    }
}
