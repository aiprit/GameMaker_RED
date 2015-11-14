package authoring_environment.Event.GUI;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.control.Button;

public class EventBottomPane {
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Event/GUI/EventGUIResources");



	public Group init(){
		Group root = new Group();
		Button save = new Button(r.getString("saveButtonTitle"));
		save.setTranslateX(Integer.parseInt(r.getString("saveButtonTranslateX")));
		save.setOnAction(e ->
		System.exit(0));
		Button cancel = new Button(r.getString("cancelButtonTitle"));
		cancel.setTranslateX(Integer.parseInt(r.getString("cancelButtonTranslateX")));
		save.setOnAction(e ->
		System.exit(0));
		root.getChildren().addAll(save, cancel);

		return root;
	}
}
