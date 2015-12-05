package authoring_environment.ObjectPopUps;


import authoring_environment.PopUpError;
import authoring_environment.Event.EventController;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;
import structures.data.events.KeyReleasedEvent;
import structures.data.events.ObjectDestroyEvent;

public class KeyReleasedPopUp extends KeyPopUp{

	public KeyReleasedPopUp(DataObject obj,IObjectInterface game) {
		super(obj,game);
		init();

		// TODO Auto-generated constructor stub
	}


	@Override
	public void eventPopup() {
		if(key ==null){
			PopUpError er = new PopUpError(r.getString("Error"));
		}
		else{
			EventController p = new EventController(new KeyReleasedEvent(key),myObject,myGame);
;
p.showAndWait();

		}

	}
}
