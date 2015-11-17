package authoring_environment.EventGUI.PopUps;

import authoring_environment.EventPopup;
import structures.data.events.KeyReleasedEvent;

public class KeyReleasePopUp extends KeyPopUp{

	public void eventPopup(){
		EventPopup p = new EventPopup();
		p.popup(new KeyReleasedEvent(key));
		myStage.close();

	}
}
