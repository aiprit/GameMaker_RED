package authoring_environment.ObjectPopUps;


import authoring_environment.Event.EventController;

import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;

import structures.data.events.ObjectCreateEvent;

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

		p.showAndWait();
	}
}
