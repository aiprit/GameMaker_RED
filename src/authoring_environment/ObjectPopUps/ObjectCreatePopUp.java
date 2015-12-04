package authoring_environment.ObjectPopUps;

import java.util.List;


import authoring_environment.Event.EventController;

import javafx.collections.ObservableMap;
import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;
import structures.data.events.GameStartEvent;
import structures.data.events.ObjectCreateEvent;
import structures.data.interfaces.IAction;
import structures.data.interfaces.IDataEvent;

public class ObjectCreatePopUp extends BasicPopUp{
	public ObjectCreatePopUp(DataObject obj,IObjectInterface game) {
		super(obj,game);

		init();

	}
	@Override
	public void init() {
		eventPopup();

	}

	@Override
	public void eventPopup() {
		EventController p = new EventController(new ObjectCreateEvent(),myObject,myGame);


	}
}
