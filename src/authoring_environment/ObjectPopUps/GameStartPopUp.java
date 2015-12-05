package authoring_environment.ObjectPopUps;


import authoring_environment.Event.EventController;

import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;
import structures.data.events.GameStartEvent;


public class GameStartPopUp  extends BasicPopUp{


	public GameStartPopUp(DataObject obj,IObjectInterface game) {
		super(obj,game);
		init();

	}

	@Override
	public void eventPopup() {
		EventController p = new EventController(new GameStartEvent(),myObject,myGame);
		p.showAndWait();


	}

}
