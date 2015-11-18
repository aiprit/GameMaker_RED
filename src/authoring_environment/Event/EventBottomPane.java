package authoring_environment.Event;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.control.Button;

public class EventBottomPane {
	Button save,cancel;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Event/EventGUIResources");
	public EventBottomPane(){
		init();
	}
	public Group init(){
		Group root = new Group();
		save = new Button(r.getString("saveButtonTitle"));
		save.setTranslateX(Integer.parseInt(r.getString("saveButtonTranslateX")));
		cancel = new Button(r.getString("cancelButtonTitle"));
		cancel.setTranslateX(Integer.parseInt(r.getString("cancelButtonTranslateX")));
//		cancel.setOnAction(e ->
//		myController.close(e));
		root.getChildren().addAll(save, cancel);

		return root;
	}
	public Button getSaveButton(){
		return save;
	}
	public Button getCancelButton(){
		return cancel;
	}
}
