package authoring_environment.ObjectPopUps;


import authoring_environment.Event.EventController;

import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;
import structures.data.events.GlobalMousePressedEvent;
import structures.data.events.ObjectMousePressedEvent;


public class ObjectMousePressedPopUp extends MousePopUp{

	public ObjectMousePressedPopUp(DataObject obj,IObjectInterface game) {
		super(obj,game);
		init();

		// TODO Auto-generated constructor stub
	}


	@Override
	public void eventPopup() {
			EventController p = new EventController(new ObjectMousePressedEvent(key),myObject,myGame);
			p.showAndWait();
	}
}
