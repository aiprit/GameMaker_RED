package authoring_environment.EventGUI.PopUps;

import java.util.List;

import authoring_environment.EventPopup;
import javafx.collections.ObservableMap;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;
import structures.data.events.ObjectCreateEvent;

public class ObjectCreatePopUp extends BasicPopUp{
	public ObjectCreatePopUp(DataObject obj) {
		super(obj);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init() {
		eventPopup();

	}

	@Override
	public void eventPopup() {
		EventPopup p = new EventPopup();
		p.popup(new ObjectCreateEvent(),myObject);

	}
}
