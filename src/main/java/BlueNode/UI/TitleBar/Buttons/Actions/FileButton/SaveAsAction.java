package BlueNode.UI.TitleBar.Buttons.Actions.FileButton;

import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;

public class SaveAsAction {

    public static void handleSaveAsAction() {
        System.out.println("Save as action called!");

        BaseLogger.log("Save as action called!", ELogLevel.INFO);
    }
}
