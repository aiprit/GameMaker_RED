package authoring_environment.ObjectPopUps;

import authoring_environment.Event.EventController;
import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;
import structures.data.events.ObjectDestroyEvent;


public class ObjectDestroyPopUp extends BasicPopUp {



	public ObjectDestroyPopUp(DataObject obj,IObjectInterface game) {
		super(obj,game);
		init();

		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventPopup() {
		EventController p = new EventController(new ObjectDestroyEvent(),myObject,myGame);

		p.showAndWait();
	}

}
