package com.mealam.bluenode.styles;

import com.mealam.bluenode.UIConstants;
import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.zip.ZipFile;

public class StyleLoader {

    public static void loadAllStyles(URL pStylesFolderUrl) {
        try {
            if (pStylesFolderUrl == null) {
                BaseLogger.log(BaseLogLevel.ERROR, "Styles folder not found in resources.");
                return;
            }

            if ("jar".equals(pStylesFolderUrl.getProtocol())) {
                processJarDirectory(pStylesFolderUrl);
            } else {
                File stylesFolder = new File(pStylesFolderUrl.toURI());
                if (stylesFolder.exists() && stylesFolder.isDirectory()) {
                    processDirectory(stylesFolder);
                } else {
                    BaseLogger.log(BaseLogLevel.WARNING, "Styles folder does not exist or is not a directory.");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load styles.", e);
        }
    }

    private static void processDirectory(File pDirectory) {
        File[] files = pDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    processDirectory(file);
                } else if (file.isFile() && file.getName().endsWith(".css")) {
                    try {
                        String stylesheet = file.toURI().toURL().toExternalForm();
                        UIConstants.MAIN_SCENE.getStylesheets().add(stylesheet);
                        BaseLogger.log(BaseLogLevel.SUCCESS, "[File] Successfully loaded Style: " + stylesheet);
                    } catch (Exception e) {
                        BaseLogger.log(BaseLogLevel.ERROR, "[File] Failed to load stylesheet: " + file.getAbsolutePath(), e);
                    }
                }
            }
        }
    }

    private static void processJarDirectory(URL pJarUrl) throws IOException {
        String jarFilePath = pJarUrl.getPath().substring(5, pJarUrl.getPath().indexOf("!"));
        try (ZipFile zipFile = new ZipFile(new File(jarFilePath))) {
            zipFile.stream()
                    .filter(entry -> entry.getName().startsWith("styles") && entry.getName().endsWith(".css"))
                    .forEach(entry -> {
                        try {
                            String stylesheet = "jar:file:" + jarFilePath + "!/" + entry.getName();
                            UIConstants.MAIN_SCENE.getStylesheets().add(stylesheet);
                            BaseLogger.log(BaseLogLevel.SUCCESS, "[Jar] Successfully loaded Style: " + stylesheet);
                        } catch (Exception e) {
                            BaseLogger.log(BaseLogLevel.ERROR, "[Jar] Failed to load stylesheet: " + entry.getName(), e);
                        }
                    });
        }
    }
}
