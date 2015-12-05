package authoring_environment.object_editor;

import java.util.ResourceBundle;

import authoring_environment.room.button_toolbar.HelpIcon;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ObjectEditorViewBottomPane {

	private ResourceBundle bottomResources = ResourceBundle.getBundle("authoring_environment/object_editor/BottomPaneResources");
	private Button close, cancel;
	private TextField nameBox;
	private String objectName;
	private Group root;
	private HBox box;
	private CheckBox checkBox;
	private HelpIcon myTooltip;

	public ObjectEditorViewBottomPane() {
		init();
	}
	public String getNameBoxText() {
		return nameBox.getText();
	}

	public Button getCloseButton() {
		return close;
	}

//	public Button getCancelButton() {
//		return cancel;
//	}

	public Group init() {
		root = new Group();
		box = new HBox();
		Label nameLabel = new Label(bottomResources.getString("nameTitle"));
		nameBox = createTextBox(objectName,Integer.parseInt(bottomResources.getString("prefWidth")),Integer.parseInt(bottomResources.getString("prefHeight")));
		HBox nameHBox = createHBox(nameLabel, nameBox, Integer.parseInt(bottomResources.getString("nameTranslate")));

		close= new Button(bottomResources.getString("saveButtonTitle"));
		close.setTranslateX(Integer.parseInt(bottomResources.getString("cancelButtonTranslateX")));
		
		checkBox = new CheckBox("Solid?");
		checkBox.setTranslateX(Integer.parseInt(bottomResources.getString("checkBoxTranslateX")));
//
//		cancel = new Button(bottomResources.getString("cancelButtonTitle"));
//		cancel.setTranslateX(Integer.parseInt(bottomResources.getString("cancelButtonTranslateX")));
//
		box.getChildren().addAll(nameHBox, checkBox, close);
		initToolTip();
		box.setTranslateX(Integer.parseInt(bottomResources.getString("tooltipTranslateX")));
		root.getChildren().addAll(box);
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
	
	private void initToolTip() {
		Image helpIcon = new Image(getClass().getClassLoader().getResourceAsStream(bottomResources.getString("HelpTooltipIcon")));
		myTooltip = new HelpIcon(bottomResources, helpIcon);
		myTooltip.setTranslateX(Integer.parseInt(bottomResources.getString("tooltipTranslateX")));
		box.getChildren().add(myTooltip);		
	}

	public Group getGroup() {
		return root;
	}

	public TextField getNameBox() {
		return nameBox;
	}
	
	public CheckBox getCheckBox() {
		return checkBox;
	}

}
