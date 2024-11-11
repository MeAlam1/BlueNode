package com.mealam.bluenode.utils.conversion;

import java.awt.Color;

public class ColorUtils {

    // Convert java.awt.Color to RGB array
    public static int[] toRGB(Color pColor) {
        return new int[]{pColor.getRed(), pColor.getGreen(), pColor.getBlue()};
    }

    // Convert java.awt.Color to HEX string
    public static String toHex(Color pColor) {
        return String.format("#%02X%02X%02X", pColor.getRed(), pColor.getGreen(), pColor.getBlue());
    }

    // Convert RGB values to java.awt.Color
    public static Color fromRGB(int pRed, int pGreen, int pBlue) {
        return new Color(pRed, pGreen, pBlue);
    }

    // Convert HEX string to java.awt.Color
    public static Color fromHex(String pHex) {
        return Color.decode(pHex);
    }

    // Convert RGB values to HEX string
    public static String rgbToHex(int pRed, int pGreen, int pBlue) {
        return String.format("#%02X%02X%02X", pRed, pGreen, pBlue);
    }

    // Convert HEX string to RGB array
    public static int[] hexToRGB(String pHex) {
        Color color = Color.decode(pHex);
        return new int[] { color.getRed(), color.getGreen(), color.getBlue() };
    }

    public static String rgbToString(int[] pColor) {
        return String.format("rgb(%d, %d, %d)", pColor[0], pColor[1], pColor[2]);
    }

    public static String colorToString(Color pColor) {
        int[] rgb = toRGB(pColor);
        return String.format("rgb(%d, %d, %d)", rgb[0], rgb[1], rgb[2]);
    }
}
