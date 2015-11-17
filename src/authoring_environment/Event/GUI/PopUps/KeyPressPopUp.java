package authoring_environment.Event.GUI.PopUps;

import authoring_environment.EventPopup;
import javafx.scene.input.KeyCode;
import structures.data.events.KeyPressedEvent;
import structures.data.events.KeyReleasedEvent;

public class KeyPressPopUp extends KeyPopUp {
	protected void eventPopup(KeyCode code){
		EventPopup p = new EventPopup();
		p.popup(new KeyPressedEvent(code));
		myStage.close();

	}
}
