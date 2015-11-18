package authoring_environment;

import java.util.List;

import authoring_environment.EventGUI.EventController;
import authoring_environment.EventGUI.EventGUI;
import structures.data.DataObject;
import structures.data.events.IDataEvent;

public class EventPopup {

	public void popup(IDataEvent event,	DataObject obj){

		 EventController control = new EventController(event,obj);
         EventGUI gui = new EventGUI(control);
	}

}
