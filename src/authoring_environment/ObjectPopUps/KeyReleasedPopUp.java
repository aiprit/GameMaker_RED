package authoring_environment.ObjectPopUps;


import authoring_environment.PopUpError;
import authoring_environment.Event.EventController;

import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;
import structures.data.events.KeyReleasedEvent;


public class KeyReleasedPopUp extends KeyPopUp{

	public KeyReleasedPopUp(DataObject obj,IObjectInterface game) {
		super(obj,game);
		init();

		// TODO Auto-generated constructor stub
	}


	@Override
	public void eventPopup() {
		if(key ==null){
			new PopUpError(r.getString("Error"));
		}
		else{
			EventController p = new EventController(new KeyReleasedEvent(key),myObject,myGame);
;
p.showAndWait();

		}

	}
}
