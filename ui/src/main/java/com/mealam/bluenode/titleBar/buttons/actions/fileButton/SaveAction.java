package com.mealam.bluenode.titleBar.buttons.actions.fileButton;

import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;

public class SaveAction {

    public static void handleSaveAction() {
        System.out.println("Save action");

        BaseLogger.log(BaseLogLevel.SUCCESS, "Save action called!");
    }
}
