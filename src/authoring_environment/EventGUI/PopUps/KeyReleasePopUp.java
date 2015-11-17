package authoring_environment.EventGUI.PopUps;

import authoring_environment.EventPopup;
import javafx.collections.ObservableMap;
import structures.data.events.KeyReleasedEvent;

public class KeyReleasePopUp extends KeyPopUp{

	public KeyReleasePopUp(ObservableMap m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	public void eventPopup(){
		EventPopup p = new EventPopup();
		p.popup(new KeyReleasedEvent(key),myMap);
		myStage.close();

	}
}
