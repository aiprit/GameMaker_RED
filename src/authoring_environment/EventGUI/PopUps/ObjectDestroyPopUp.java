package authoring_environment.EventGUI.PopUps;

import java.util.List;

import authoring_environment.EventPopup;
import javafx.collections.ObservableMap;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;
import structures.data.events.ObjectDestroyEvent;

public class ObjectDestroyPopUp implements PopUp {
	private ObservableMap<IDataEvent,List<IAction>> myMap;

	public ObjectDestroyPopUp(ObservableMap<IDataEvent,List<IAction>> m){
		myMap = m;
	}

	@Override
	public void init() {
		eventPopup();

	}

	@Override
	public void eventPopup() {
		EventPopup p = new EventPopup();
		p.popup(new ObjectDestroyEvent(),myMap);

	}

}
