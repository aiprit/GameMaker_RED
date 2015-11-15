package authoring_environment;

import authoring_environment.Event.EventController;
import authoring_environment.Event.GUI.EventGUI;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EventPopup {



	public void popup() {
		 Stage dialog = new Stage();
         
         EventGUI gui = new EventGUI(null,dialog);
	}

}
