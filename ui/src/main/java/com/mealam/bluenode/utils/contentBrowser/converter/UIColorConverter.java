package com.mealam.bluenode.utils.contentBrowser.converter;

import javafx.scene.paint.Color;

public class UIColorConverter {

    /**
     * Converts a Java AWT Color to a JavaFX Color.
     *
     * @param awtColor the AWT Color to convert
     * @return the corresponding JavaFX Color
     */
    public static Color toJavaFXColor(java.awt.Color awtColor) {
        if (awtColor == null) {
            return null;
        }
        return Color.rgb(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue(), awtColor.getAlpha() / 255.0);
    }

    /**
     * Converts a JavaFX Color to a Java AWT Color.
     *
     * @param javaFXColor the JavaFX Color to convert
     * @return the corresponding AWT Color
     */
    public static java.awt.Color toAwtColor(Color javaFXColor) {
        if (javaFXColor == null) {
            return null;
        }
        return new java.awt.Color((int) (javaFXColor.getRed() * 255),
                (int) (javaFXColor.getGreen() * 255),
                (int) (javaFXColor.getBlue() * 255),
                (int) (javaFXColor.getOpacity() * 255));
    }
}
