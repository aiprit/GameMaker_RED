package authoring_environment.ObjectPopUps;

import java.util.List;


import authoring_environment.Event.EventController;

import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.GameEndEvent;
import structures.data.events.IDataEvent;

public class GameEndPopUp extends BasicPopUp{


	public GameEndPopUp(DataObject obj) {
		super(obj);
		init();

		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventPopup() {
		EventController p = new EventController(new GameEndEvent(),myObject);

	}

}
