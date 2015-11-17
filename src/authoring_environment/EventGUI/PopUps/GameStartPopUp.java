package authoring_environment.EventGUI.PopUps;

import java.util.List;

import authoring_environment.EventPopup;
import javafx.collections.ObservableMap;
import structures.data.actions.IAction;
import structures.data.events.GameStartEvent;
import structures.data.events.IDataEvent;

public class GameStartPopUp implements PopUp {

	private ObservableMap<IDataEvent,List<IAction>> myMap;

	public GameStartPopUp(ObservableMap<IDataEvent,List<IAction>> m){
		myMap = m;
	}
	@Override
	public void init() {
		eventPopup();

	}

	@Override
	public void eventPopup() {
		EventPopup p = new EventPopup();
		p.popup(new GameStartEvent(),myMap);

	}

}
