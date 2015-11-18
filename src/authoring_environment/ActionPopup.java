package authoring_environment;

import authoring_environment.Action.GUI.ActionGUI;
import authoring_environment.EventGUI.EventGUI;
import javafx.stage.Stage;

public class ActionPopup {
	public void popup() {
		 Stage dialog = new Stage();

        ActionGUI gui = new ActionGUI(dialog);
	}
}
