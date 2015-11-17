package authoring_environment;

import authoring_environment.EventGUI.EventController;
import authoring_environment.EventGUI.EventGUI;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import structures.data.events.IDataEvent;

public class EventPopup {



	public void popup(IDataEvent event) {
		Stage dialog = new Stage();
		 EventController control = new EventController(event,dialog);
         EventGUI gui = new EventGUI(control);
	}

}
