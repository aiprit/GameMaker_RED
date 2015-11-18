package authoring_environment.EventGUI.PopUps;

import java.util.List;

import structures.data.DataObject;


public abstract class BasicPopUp implements PopUp {

	protected DataObject myObject;

	public BasicPopUp(DataObject obj){
		myObject = obj;
	}

	public void init() {
		eventPopup();

	}

}
