package com.mealam.bluenode.components;

import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.InputStream;

public class ImageLoader extends HBox {

    public ImageLoader(String pImagePath, int pWidth, int pHeight) {
        InputStream imageStream = getClass().getResourceAsStream(pImagePath);
        if (imageStream != null) {
            try {
                Image image = new Image(imageStream, pWidth, pHeight, true, true);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(pWidth);
                imageView.setFitHeight(pHeight);
                getChildren().add(imageView);
                setSpacing(5);
                setAlignment(Pos.CENTER);

                BaseLogger.log(BaseLogLevel.INFO, "Successfully loaded image: " + pImagePath);
            } catch (Exception pException) {
                BaseLogger.log(BaseLogLevel.ERROR, "Error occurred while loading image: " + pImagePath, pException);
            }
        } else {
            BaseLogger.log(BaseLogLevel.ERROR, "Failed to locate image at path: " + pImagePath, new Throwable("Resource not found"));
        }
    }

    public static Image getImage(String pImagePath) {
        InputStream imageStream = ImageLoader.class.getResourceAsStream(pImagePath);
        if (imageStream != null) {
            try {
                BaseLogger.log(BaseLogLevel.INFO, "Successfully located image at path: " + pImagePath);
                return new Image(imageStream);
            } catch (Exception pException) {
                BaseLogger.log(BaseLogLevel.ERROR, "Error occurred while creating image from stream: " + pImagePath, pException);
                return null;
            }
        } else {
            BaseLogger.log(BaseLogLevel.ERROR, "Image resource not found at path: " + pImagePath, new Throwable("Resource not found"));
            return null;
        }
    }
}
