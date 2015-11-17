package authoring_environment.EventGUI.PopUps;

import java.util.List;

import authoring_environment.EventPopup;
import javafx.collections.ObservableMap;
import structures.data.actions.IAction;
import structures.data.events.GameEndEvent;
import structures.data.events.IDataEvent;

public class GameEndPopUp implements PopUp{
	private ObservableMap<IDataEvent,List<IAction>> myMap;

	public GameEndPopUp(ObservableMap<IDataEvent,List<IAction>> m){
		myMap = m;
	}

	@Override
	public void init() {
		eventPopup();

	}

	@Override
	public void eventPopup() {
		EventPopup p = new EventPopup();
		p.popup(new GameEndEvent(),myMap);

	}

}
