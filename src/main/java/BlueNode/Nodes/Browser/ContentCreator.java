package BlueNode.Nodes.Browser;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.Set;

public class ContentCreator {

    private final ButtonHandler buttonHandler;

    public ContentCreator(Stage pParentStage) {
        this.buttonHandler = new ButtonHandler(pParentStage);
    }

    public VBox createContent(MouseEvent pEvent, Popup pPopup) {
        VBox popupContent = new VBox();
        popupContent.setStyle("-fx-background-color: #222222; -fx-padding: 10; -fx-border-color: #333333; -fx-border-width: 1; -fx-border-radius: 5;");
        popupContent.setSpacing(10);

        Reflections reflections = new Reflections("BlueNode.Nodes.Library");
        Set<Class<? extends PopupButtonProvider>> nodeClasses = reflections.getSubTypesOf(PopupButtonProvider.class);

        for (Class<? extends PopupButtonProvider> nodeClass : nodeClasses) {
            addButtonToPopup(popupContent, nodeClass, pEvent, pPopup);
        }

        return popupContent;
    }

    private void addButtonToPopup(VBox pPopupContent, Class<? extends PopupButtonProvider> pNodeClass, MouseEvent pEvent, Popup pPopup) {
        try {
            Constructor<? extends PopupButtonProvider> constructor = pNodeClass.getConstructor();
            PopupButtonProvider instance = constructor.newInstance();

            Label buttonLabel = new Label(instance.getButtonText());
            buttonLabel.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-padding: 5 10; -fx-border-radius: 3;");
            buttonLabel.setOnMouseClicked(event -> buttonHandler.handleButtonClick(pNodeClass, pEvent, pPopup, instance));

            pPopupContent.getChildren().add(buttonLabel);
        } catch (Exception pException) {
            BaseLogger.log(ELogLevel.ERROR, "Error creating instance of class: " + pNodeClass.getName(), pException);
        }
    }
}
