package authoring_environment.EventGUI.PopUps;

import authoring_environment.main.EventPopup;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import structures.data.DataObject;
import structures.data.events.KeyReleasedEvent;

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
			EventPopup p = new EventPopup();
			p.popup(new KeyReleasedEvent(key),myObject);


		}

	}
}
