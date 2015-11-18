package authoring_environment.ObjectGUI.bottomPane;

import java.util.ResourceBundle;

import authoring_environment.ObjectGUI.ObjectController;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ObjectBottomPane {
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/bottomPane/BottomPaneResources");
	private String name;
	private ObjectController myController;
	public ObjectBottomPane(ObjectController controller) {
		myController = controller;
		try {
			name = myController.getName();

	
		}
		catch (NullPointerException e) {
			name = r.getString("tempName");
		}
	}

	public Group init() {
		Group root = new Group();
		Label nameLabel = new Label(r.getString("nameTitle"));
		TextField nameBox = createTextBox(name,Integer.parseInt(r.getString("prefWidth")),Integer.parseInt(r.getString("prefHeight")));
		HBox name = createHBox(nameLabel, nameBox, Integer.parseInt(r.getString("nameTranslate")));

		Button save = new Button(r.getString("saveButtonTitle"));
		save.setTranslateX(Integer.parseInt(r.getString("saveButtonTranslateX")));
		save.setOnAction(e -> {
			update(nameBox.getText());
			myController.close(e);
		});
		Button cancel = new Button(r.getString("cancelButtonTitle"));
		cancel.setTranslateX(Integer.parseInt(r.getString("cancelButtonTranslateX")));
		cancel.setOnAction(e ->
		myController.close(e));
		root.getChildren().addAll(name, save, cancel);

		return root;
	}

	private void update(String name) {
		myController.setName(name);
	}

	private TextField createTextBox(String s, int width, int height) {
		TextField text = new TextField(s);
		text.setPrefWidth(width);
		text.setPrefHeight(height);
		return text;
	}

	private HBox createHBox(Label label, TextField text, int translate) {
		HBox hb = new HBox();
		hb.getChildren().addAll(label, text);
		hb.setSpacing(Integer.parseInt(r.getString("boxSpacing")));
		hb.setTranslateX(translate);
		return hb;
	}
}