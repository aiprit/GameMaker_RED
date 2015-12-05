package authoring_environment.ParamPopups.ParamBoxFactory;

import java.util.List;

import javafx.scene.control.ComboBox;
import structures.data.actions.params.IParameter;
import structures.data.actions.params.ISelectable;
import structures.data.actions.params.RoomParam;

public class GeneralMenu  {
	ISelectable mySelect;
	IParameter myParam;
	ComboBox event;

	public GeneralMenu(Object iParameter) {
		myParam = (IParameter) iParameter;
		mySelect = (ISelectable) iParameter;
	}
	public ComboBox<String> makeMenu() {
		ComboBox<String> event = new ComboBox<String>();
		List<String> list =  mySelect.getOptions();
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


