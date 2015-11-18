package authoring_environment.object_editor.view;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ObjectEditorViewBottomPane {
	
	private ResourceBundle bottomResources = ResourceBundle.getBundle("authoring_environment/ObjectGUI/bottomPane/BottomPaneResources");
	private Button save, cancel;
	private TextField nameBox;
	private String objectName;
	private Group root;
	
	public ObjectEditorViewBottomPane(String name) {
		objectName = name;
	}
	public String getNameBoxText() {
		return nameBox.getText();
	}
	
	public Button getSaveButton() {
		return save;
	}
	
	public Button getCancelButton() {
		return cancel;
	}

	private Group init() {
		root = new Group();
		Label nameLabel = new Label(bottomResources.getString("nameTitle"));
		nameBox = createTextBox(objectName,Integer.parseInt(bottomResources.getString("prefWidth")),Integer.parseInt(bottomResources.getString("prefHeight")));
		HBox nameHBox = createHBox(nameLabel, nameBox, Integer.parseInt(bottomResources.getString("nameTranslate")));
		
		save = new Button(bottomResources.getString("saveButtonTitle"));
		save.setTranslateX(Integer.parseInt(bottomResources.getString("saveButtonTranslateX")));
		
		cancel = new Button(bottomResources.getString("cancelButtonTitle"));
		cancel.setTranslateX(Integer.parseInt(bottomResources.getString("cancelButtonTranslateX")));
		
		root.getChildren().addAll(nameHBox,save,cancel);
		return root;
		
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
		hb.setSpacing(Integer.parseInt(bottomResources.getString("boxSpacing")));
		hb.setTranslateX(translate);
		return hb;
	}	
	
	public Group getGroup() {
		return root;
	}
}
