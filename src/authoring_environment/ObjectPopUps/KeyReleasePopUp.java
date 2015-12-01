package authoring_environment.ObjectPopUps;


import authoring_environment.Event.EventController;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import structures.data.DataObject;
import structures.data.events.KeyReleasedEvent;
import structures.data.events.ObjectDestroyEvent;

public class KeyReleasePopUp extends KeyPopUp{

	public KeyReleasePopUp(DataObject obj) {
		super(obj);
		init();

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
