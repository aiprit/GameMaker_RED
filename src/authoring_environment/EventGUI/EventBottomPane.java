package authoring_environment.EventGUI;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.control.Button;
import structures.data.DataObject;

public class EventBottomPane {
	EventController myController;
	DataObject myObject;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/EventGUI/EventGUIResources");
	public EventBottomPane(EventController control){
		myController =control;

	}


	public Group init(){
		Group root = new Group();
		Button save = new Button(r.getString("saveButtonTitle"));
		save.setTranslateX(Integer.parseInt(r.getString("saveButtonTranslateX")));
		save.setOnAction(e ->{
		myController.save();
		myController.close(e);});
		Button cancel = new Button(r.getString("cancelButtonTitle"));
		cancel.setTranslateX(Integer.parseInt(r.getString("cancelButtonTranslateX")));
		cancel.setOnAction(e ->
		myController.close(e));
		root.getChildren().addAll(save, cancel);

		return root;
	}
}
