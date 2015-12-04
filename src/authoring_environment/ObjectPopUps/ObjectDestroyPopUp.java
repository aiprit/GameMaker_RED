package authoring_environment.ObjectPopUps;

import java.util.List;


import authoring_environment.Event.EventController;
import javafx.collections.ObservableMap;
import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;
import structures.data.events.GameStartEvent;
import structures.data.events.ObjectDestroyEvent;
import structures.data.interfaces.IAction;
import structures.data.interfaces.IDataEvent;

public class ObjectDestroyPopUp extends BasicPopUp {



	public ObjectDestroyPopUp(DataObject obj,IObjectInterface game) {
		super(obj,game);
		init();

		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventPopup() {
		EventController p = new EventController(new ObjectDestroyEvent(),myObject,myGame);


	}

}
