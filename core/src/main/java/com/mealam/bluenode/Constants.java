package com.mealam.bluenode;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class Constants {

    public static final String APP_NAME = "BlueNode";
    public static final String APP_ID = "bluenode";
    public static final String APP_VERSION = "0.0.1-SNAPSHOT";
    public static final boolean IS_DEVELOPMENT = true;
    public static final int DEFAULT_WIDTH = 1200;
    public static final int DEFAULT_HEIGHT = 800;
    public static final String LOGO_PATH = "/assets/images/logo.png";

    private Constants() {}

    public static ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);

    public static final Logger LOGGER = Logger.getLogger(Constants.APP_NAME);

    public static boolean isLoggingEnabled = true;
}
