package authoring_environment.ObjectPopUps;


import authoring_environment.Event.EventController;

import structures.data.DataObject;
import structures.data.events.GlobalMousePressedEvent;


public class GlobalMousePressedPopUp extends MousePopUp{

	public GlobalMousePressedPopUp(DataObject obj) {
		super(obj);
		init();

	}


	@Override
	public void eventPopup() {
			EventController p = new EventController(new GlobalMousePressedEvent(key),myObject);
	}
}
