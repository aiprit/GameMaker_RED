package authoring_environment.EventGUI.PopUps;

import authoring_environment.EventPopup;
import structures.data.events.KeyPressedEvent;


public class KeyPressPopUp extends KeyPopUp {
	public void eventPopup(){
		EventPopup p = new EventPopup();
		p.popup(new KeyPressedEvent(key));
		myStage.close();

	}
}
