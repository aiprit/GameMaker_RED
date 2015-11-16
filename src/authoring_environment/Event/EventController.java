package authoring_environment.Event;

import authoring_environment.Event.GUI.EventGUI;
import javafx.stage.Stage;
import structures.data.events.IDataEvent;

public class EventController {

	public EventController(IDataEvent e,Stage stage){
		init();
		EventGUI gui = new EventGUI(e,stage);
	}

	private void init() {
		// TODO Auto-generated method stub

	}

}
