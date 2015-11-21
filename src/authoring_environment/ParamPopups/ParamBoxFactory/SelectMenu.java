package authoring_environment.ParamPopups.ParamBoxFactory;

import java.util.List;

import authoring_environment.Event.EventRightPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import structures.data.actions.params.IParameter;
import structures.data.actions.params.RoomParam;
import structures.data.actions.params.SelectParam;

public class SelectMenu {

	SelectParam myParam;
	ComboBox event;
	public SelectMenu(IParameter iParameter) {
		myParam = (SelectParam) iParameter;
	}
	public ComboBox<String> makeMenu() {
		ComboBox<String> event = new ComboBox<String>();
		List<String> list =  myParam.getOptions();
		for(String str: list){
		event.getItems().add(str);
		}
		return event;
	}
	public ComboBox<String> getComboBoxItem(){
		return event;
	}


}
