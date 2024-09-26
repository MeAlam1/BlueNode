package BlueNode.UI.Components;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.InputStream;

public class ImageLoader extends HBox {

    public ImageLoader(String pImagePath, int pWidth, int pHeight) {
        InputStream imageStream = getClass().getResourceAsStream(pImagePath);
        if (imageStream != null) {
            Image image = new Image(imageStream, pWidth, pHeight, true, true);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(pWidth);
            imageView.setFitHeight(pHeight);
            getChildren().add(imageView);
            setSpacing(5);
            setAlignment(Pos.CENTER);

            BaseLogger.log("Image found: " + pImagePath, ELogLevel.DEBUG);
        } else {
            BaseLogger.log("Image not found: " + pImagePath, ELogLevel.ERROR);
        }
    }

    public static Image getImage(String pImagePath) {
        InputStream imageStream = ImageLoader.class.getResourceAsStream(pImagePath);
        if (imageStream != null) {
            BaseLogger.log("Image found: " + pImagePath, ELogLevel.DEBUG);
            return new Image(imageStream);
        } else {
            BaseLogger.log("Image not found: " + pImagePath, ELogLevel.ERROR);
            return null;
        }
    }
}
