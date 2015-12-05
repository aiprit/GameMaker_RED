package authoring_environment.ParamPopups.ParamBoxFactory;

import java.util.List;

import authoring_environment.Event.EventRightPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import structures.data.actions.params.IParameter;
import structures.data.actions.params.ObjectParam;
import structures.data.actions.params.RoomParam;

public class RoomMenu {
	RoomParam myParam;
	ComboBox event;

	public RoomMenu(IParameter iParameter) {
		myParam = (RoomParam) iParameter;
	}
	public ComboBox<String> makeMenu() {
		ComboBox<String> event = new ComboBox<String>();
		List<String> list =  myParam.getOptions();
		for(String str: list){
			event.getItems().add(str);
			if(myParam.getValue()!=null){
				if(myParam.getOriginal().equals(str)){
					event.setValue(str);
				}
			}
		}
		return event;
	}
	public ComboBox<String> getComboBoxItem(){
		return event;
	}


}
