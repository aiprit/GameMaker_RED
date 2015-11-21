package authoring_environment.ParamPopups;

import java.util.List;

import exceptions.ParameterParseException;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import structures.data.actions.IAction;
import structures.data.actions.params.IParameter;
import structures.data.events.IDataEvent;

public class ParamController {

	private ParamPopupView view;
	private ParamModel model;

	public ParamController(IAction e,List<IAction> action ){
		view = new ParamPopupView(e.getParameters());
		model = new ParamModel(e,action);
		init();
	}
	private void init() {
		view.getSaveButton().setOnAction(e -> onSave());
	}
	private void onSave() {
		List<HBox> fieldList = view.getFieldList();
		try {
			for(int i = 0; i<model.getListsize();i++){
				String input = getInput(fieldList.get(i+1));
				if(input!=null){

					model.getList().get(i).parse(input);

				}
			}
			model.addAction();
			view.close();
		}
		catch (ParameterParseException e) {
				alertPopUp();

	}
		System.out.println(model.getActionList());
	//configure.close();
}
private String getInput(HBox field) {
	Node node= field.getChildren().get(1);
	String str = null;
	try{
		str =((TextInputControl) node).getText();
	}
	catch(ClassCastException e){
		try{
			Boolean bol = ((CheckBox)node).isSelected();
			str = bol.toString();
		}
		catch(ClassCastException e1){

		}
	}

	return str;
}
private void alertPopUp() {
	Alert alert = new Alert(AlertType.ERROR);
	alert.setTitle("Error");
	alert.setHeaderText("Invalid Parameter");
	alert.setContentText("Please Reenter");
	alert.showAndWait();
}
}
