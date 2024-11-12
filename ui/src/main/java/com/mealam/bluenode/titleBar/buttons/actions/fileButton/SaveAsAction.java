package com.mealam.bluenode.titleBar.buttons.actions.fileButton;

import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;

public class SaveAsAction {

    public static void handleSaveAsAction() {
        System.out.println("Save as action called!");

        BaseLogger.log(BaseLogLevel.SUCCESS, "Save as action called!");
    }
}
