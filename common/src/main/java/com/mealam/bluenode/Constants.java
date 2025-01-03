package com.mealam.bluenode;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class Constants {

    public static final String APP_NAME = "BlueNode";
    public static final String APP_ID = "bluenode";
    public static final int DEFAULT_WIDTH = 1200;
    public static final int DEFAULT_HEIGHT = 800;
    public static final String LOGO_PATH = "/assets/images/logo.png";

    /**
     * Private constructor to prevent instantiation.
     * <p>
     * This constructor is intentionally empty to prevent creating instances of this class.
     * </p>
     *
     * @author MeAlam
     * @since 1.0.0
     */
    private Constants() {}

    /**
     * A {@code public static} {@link ScheduledExecutorService} used to schedule tasks, such as printing messages after a delay.
     * <p>
     * This executor runs tasks on a single thread to ensure delayed tasks run in a separate thread from the main thread.
     * </p>
     *
     * @since 1.0.0
     */
    public static ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);

    /**
     * A {@link Logger} instance for logging messages.
     *
     * @since 1.0.0
     */
    public static final Logger LOGGER = Logger.getLogger(Constants.APP_NAME);

    /**
     * A {@link Boolean} to enable or disable logging.
     *
     * @since 1.0.0
     */
    public static boolean isLoggingEnabled = true;
}
