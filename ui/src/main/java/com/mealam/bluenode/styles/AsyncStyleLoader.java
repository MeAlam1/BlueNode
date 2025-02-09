package com.mealam.bluenode.styles;

import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;
import javafx.application.Platform;

import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class AsyncStyleLoader {

    public static void loadAllStyles(URL stylesFolderUrl) {
        CompletableFuture.runAsync(() -> {
            StyleLoader.loadAllStyles(stylesFolderUrl);
        }).whenComplete((result, exception) -> {
            if (exception != null) {
                BaseLogger.log(BaseLogLevel.ERROR, "Failed to load styles asynchronously.", exception);
            } else {
                Platform.runLater(() -> {
                    BaseLogger.log(BaseLogLevel.SUCCESS, "All styles loaded successfully.");
                });
            }
        });
    }
}
