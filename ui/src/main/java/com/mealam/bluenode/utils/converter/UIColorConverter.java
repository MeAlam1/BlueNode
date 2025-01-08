package com.mealam.bluenode.utils.converter;

import javafx.scene.paint.Color;

@SuppressWarnings("unused")
public class UIColorConverter {

    /**
     * Converts a Java AWT Color to a JavaFX Color.
     *
     * @param pAwtColor the AWT Color to convert
     * @return the corresponding JavaFX Color
     */
    public static Color toJavaFXColor(java.awt.Color pAwtColor) {
        if (pAwtColor == null) {
            return null;
        }
        return Color.rgb(pAwtColor.getRed(), pAwtColor.getGreen(), pAwtColor.getBlue(), pAwtColor.getAlpha() / 255.0);
    }

    /**
     * Converts a JavaFX Color to a Java AWT Color.
     *
     * @param pJavaFXColor - the JavaFX Color to convert
     * @return the corresponding AWT Color
     */
    public static java.awt.Color toAwtColor(Color pJavaFXColor) {
        if (pJavaFXColor == null) {
            return null;
        }
        return new java.awt.Color((int) (pJavaFXColor.getRed() * 255),
                (int) (pJavaFXColor.getGreen() * 255),
                (int) (pJavaFXColor.getBlue() * 255),
                (int) (pJavaFXColor.getOpacity() * 255));
    }
}
