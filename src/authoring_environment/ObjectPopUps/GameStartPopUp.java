package authoring_environment.ObjectPopUps;

import java.util.List;

<<<<<<< HEAD
import authoring_environment.main.EventPopup;
=======
import authoring_environment.Event.EventController;
>>>>>>> a729fee0b69507a58aff9d3d702b022182226556
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import structures.data.DataObject;
import structures.data.events.GameStartEvent;
import structures.data.events.IDataEvent;

public class GameStartPopUp  extends BasicPopUp{


	public GameStartPopUp(DataObject obj) {
		super(obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventPopup() {
		EventController p = new EventController(new GameStartEvent(),myObject);



	}

}
