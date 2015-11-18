package authoring_environment.ObjectPopUps;

<<<<<<< HEAD
import authoring_environment.main.EventPopup;
=======
import authoring_environment.Event.EventController;
>>>>>>> a729fee0b69507a58aff9d3d702b022182226556
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import structures.data.DataObject;
import structures.data.events.KeyReleasedEvent;
import structures.data.events.ObjectDestroyEvent;

public class KeyReleasePopUp extends KeyPopUp{

	public KeyReleasePopUp(DataObject obj) {
		super(obj);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void eventPopup() {
		if(key ==null){

			nullAlert();
		}
		else{
			EventController p = new EventController(new KeyReleasedEvent(key),myObject);
;


		}

	}
}
