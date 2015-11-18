package authoring_environment.ObjectPopUps;

import java.util.List;

import authoring_environment.Event.EventController;
import javafx.collections.ObservableMap;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.GameStartEvent;
import structures.data.events.IDataEvent;
import structures.data.events.ObjectDestroyEvent;

public class ObjectDestroyPopUp extends BasicPopUp {



	public ObjectDestroyPopUp(DataObject obj) {
		super(obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventPopup() {
		EventController p = new EventController(new ObjectDestroyEvent(),myObject);


	}

}
