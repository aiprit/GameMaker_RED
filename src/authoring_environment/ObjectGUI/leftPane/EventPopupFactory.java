package authoring_environment.ObjectGUI.leftPane;


import authoring_environment.EventGUI.PopUps.GameEndPopUp;
import authoring_environment.EventGUI.PopUps.GameStartPopUp;
import authoring_environment.EventGUI.PopUps.KeyPressPopUp;
import authoring_environment.EventGUI.PopUps.KeyReleasePopUp;
import authoring_environment.EventGUI.PopUps.ObjectCreatePopUp;
import authoring_environment.EventGUI.PopUps.ObjectDestroyPopUp;
import authoring_environment.EventGUI.PopUps.PopUp;



public class EventPopupFactory {
	PopUp kp;
	public void create(String event) {
		//		if (event.equalsIgnoreCase("Collision Event")) {
		//
		//		}
		if (event.equalsIgnoreCase("Game End Event")) {
			kp = new GameEndPopUp();
		}
		if (event.equalsIgnoreCase("On Game Start Event")) {
			kp = new GameStartPopUp();
		}
		if (event.equalsIgnoreCase("On Key Press Event")) {
			kp = new KeyPressPopUp();
		}
		if (event.equalsIgnoreCase("On Key Release Event")) {
			kp = new KeyReleasePopUp();
		}
		if (event.equalsIgnoreCase("On Object Create Event")) {
			kp = new ObjectCreatePopUp();
		}
		if (event.equalsIgnoreCase("On Object Destroy Event")) {
			kp = new ObjectDestroyPopUp();
		}
		//	}

		kp.init();

	}

}