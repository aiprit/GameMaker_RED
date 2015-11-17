package authoring_environment.EventGUI.PopUps;

import authoring_environment.EventPopup;
import javafx.collections.ObservableMap;
import structures.data.events.KeyPressedEvent;


public class KeyPressPopUp extends KeyPopUp {
	public KeyPressPopUp(ObservableMap m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	public void eventPopup(){
		EventPopup p = new EventPopup();
		p.popup(new KeyPressedEvent(key),myMap);
		myStage.close();

	}
}
