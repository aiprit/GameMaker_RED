package authoring_environment.ObjectPopUps;

import java.util.List;


import authoring_environment.Event.EventController;

import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;
import structures.data.events.GameEndEvent;
import structures.data.interfaces.IAction;
import structures.data.interfaces.IDataEvent;

public class GameEndPopUp extends BasicPopUp{


	public GameEndPopUp(DataObject obj,IObjectInterface game) {
		super(obj,game);
		init();

		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventPopup() {
		EventController p = new EventController(new GameEndEvent(),myObject,myGame);

	}

}
