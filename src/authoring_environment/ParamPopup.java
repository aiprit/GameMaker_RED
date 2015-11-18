package authoring_environment;

import java.util.List;

//import authoring_environment.EventGUI.EventController;
//import authoring_environment.EventGUI.EventGUI;
import authoring_environment.OtherGUI.ParamGUI;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.actions.params.IParameter;
import structures.data.events.IDataEvent;

public class ParamPopup {
	public void popup(List<IParameter> action){

        ParamGUI gui = new ParamGUI(action);
	}
}
