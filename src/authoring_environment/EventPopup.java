package authoring_environment;

import authoring_environment.Event.GUI.EventController;
import authoring_environment.Event.GUI.EventGUI;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import structures.data.events.IDataEvent;

public class EventPopup {


	public void popup(IDataEvent event) {

		 EventController control = new EventController(event);
         EventGUI gui = new EventGUI(control);
	}

}
