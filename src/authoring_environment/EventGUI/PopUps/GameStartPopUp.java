package authoring_environment.EventGUI.PopUps;

import authoring_environment.EventPopup;
import structures.data.events.GameStartEvent;

public class GameStartPopUp implements PopUp {

	@Override
	public void init() {
		eventPopup();

	}

	@Override
	public void eventPopup() {
		EventPopup p = new EventPopup();
		p.popup(new GameStartEvent());

	}

}
