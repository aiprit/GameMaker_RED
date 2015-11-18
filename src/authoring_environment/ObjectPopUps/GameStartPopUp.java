package authoring_environment.ObjectPopUps;

import java.util.List;

import authoring_environment.EventPopup;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.GameStartEvent;
import structures.data.events.IDataEvent;

public class GameStartPopUp  extends BasicPopUp{


	public GameStartPopUp(DataObject obj) {
		super(obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventPopup() {
		EventPopup p = new EventPopup();
		p.popup(new GameStartEvent(),myObject);

	}

}
