package com.mealam.bluenode;

import com.mealam.bluenode.contentBrowser.ContentBrowser;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;

public class UIConstants {
    public static final BorderPane MAIN_ROOT = new BorderPane();
    public static final Canvas MAIN_CANVAS = new Canvas();
    public static final Scene MAIN_SCENE = new Scene(MAIN_ROOT);
    public static final ContentBrowser CONTENT_BROWSER = new ContentBrowser();
}
