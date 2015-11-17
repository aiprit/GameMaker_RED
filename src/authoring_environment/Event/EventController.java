package authoring_environment.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import authoring_environment.Event.GUI.EventGUI;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class EventController {
	DataObject myObject;
	IDataEvent myEvent;
	public EventController(DataObject o,IDataEvent e,Stage stage){
		myObject = o;
		myEvent = e;
	}


	public List<IAction> getActions() {
		Map<IDataEvent, List<IAction>> map = myObject.getEvents();
		if(map.containsKey(myEvent))
			return map.get(myEvent);
		return new ArrayList<IAction>();
	}

}
