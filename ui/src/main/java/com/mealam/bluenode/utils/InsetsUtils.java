package com.mealam.bluenode.utils;

import javafx.geometry.Insets;

public class InsetsUtils extends Insets {

    public InsetsUtils(double pTop, double pBottom, double pRight, double pLeft) {
        super(pTop, pRight, pBottom, pLeft);
    }

    public InsetsUtils(double pValue) {
        super(pValue);
    }

    public InsetsUtils(double pVertical, double pHorizontal) {
        super(pVertical, pHorizontal, pVertical, pHorizontal);
    }
}