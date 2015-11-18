package authoring_environment;


import authoring_environment.Event.EventRightPane;
import authoring_environment.OtherGUI.ActionGUI;
import javafx.stage.Stage;

public class ActionPopup {
	public void popup(EventRightPane right) {
		 Stage dialog = new Stage();

        ActionGUI gui = new ActionGUI(dialog,right);
	}
}
