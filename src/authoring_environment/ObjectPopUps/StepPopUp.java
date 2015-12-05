package authoring_environment.ObjectPopUps;


import authoring_environment.Event.EventController;

import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;
import structures.data.events.StepEvent;

public class StepPopUp  extends BasicPopUp{


	public StepPopUp(DataObject obj,IObjectInterface game) {
		super(obj,game);
		init();

	}

	@Override
	public void eventPopup() {
		EventController p = new EventController(new StepEvent(),myObject,myGame);
		p.showAndWait();


	}

}
