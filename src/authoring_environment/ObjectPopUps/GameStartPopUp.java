package authoring_environment.ObjectPopUps;

import java.util.List;

import authoring_environment.Event.EventController;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;
import structures.data.events.GameStartEvent;
import structures.data.interfaces.IDataEvent;

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
