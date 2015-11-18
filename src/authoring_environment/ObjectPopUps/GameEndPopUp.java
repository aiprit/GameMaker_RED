package authoring_environment.ObjectPopUps;

import java.util.List;

import authoring_environment.main.EventPopup;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.GameEndEvent;
import structures.data.events.IDataEvent;

public class GameEndPopUp extends BasicPopUp{


	public GameEndPopUp(DataObject obj) {
		super(obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventPopup() {
		EventPopup p = new EventPopup();
		p.popup(new GameEndEvent(),myObject);

	}

}
