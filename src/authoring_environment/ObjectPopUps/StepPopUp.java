package authoring_environment.ObjectPopUps;

import java.util.List;

import authoring_environment.Event.EventController;

import structures.data.DataObject;

import structures.data.events.StepEvent;

public class StepPopUp  extends BasicPopUp{


	public StepPopUp(DataObject obj) {
		super(obj);
		init();

	}

	@Override
	public void eventPopup() {
		EventController p = new EventController(new StepEvent(),myObject);



	}

}
