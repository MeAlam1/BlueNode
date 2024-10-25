package BlueNode.UI.TitleBar.Buttons.Actions.FileButton;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;

public class SaveAction {

    public static void handleSaveAction() {
        System.out.println("Save action");

        BaseLogger.log(ELogLevel.SUCCESS, "Save action called!");
    }
}
