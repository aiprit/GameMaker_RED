package authoring_environment.room.configure_popup;

import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConfigureView extends Stage {
	private Scene myScene;
	private final String VELOCITY_FIELD_X = "VelocityFieldX";
	private final String VELOCITY_FIELD_Y = "VelocityFieldY";
	private final String ANGULAR_VELOCITY = "AngularVelocity";
	private final String SCALE_X = "ScaleX";
	private final String SCALE_Y = "ScaleY";
	private final String ANGLE = "Angle";
	private final String TRANSPARENCY = "Transparency";
	private final String VISIBILITY = "Visibility";
	private final String SAVE_CHANGES = "SaveChanges";
	private final String DELETE_INSTANCE = "DeleteInstance";
	private Button saveButton;
	private Button deleteButton;
	private VBox popUp;
	private ResourceBundle myResources;
	private RadioButton visibilityButton;
	private List<HBox> fieldList;
	
	public ConfigureView(ResourceBundle resources) {
		myResources = resources;
		popUp = new VBox();
	}
	
	public void initializePopUp() {
		HBoxHandler handler = new HBoxHandler();
		String[] labelStrings = {myResources.getString(VELOCITY_FIELD_X), myResources.getString(VELOCITY_FIELD_Y), myResources.getString(ANGULAR_VELOCITY), myResources.getString(SCALE_X), myResources.getString(SCALE_Y), myResources.getString(ANGLE), myResources.getString(TRANSPARENCY)};
		fieldList = handler.createHBoxes(7, labelStrings);
		for (HBox box : fieldList) {
			popUp.getChildren().add(box);
		}
		HBox visibility = new HBox();
		visibility.getChildren().add(new Label(myResources.getString(VISIBILITY)));
		visibilityButton = new RadioButton();
		visibility.getChildren().add(visibilityButton);
		popUp.getChildren().add(visibility);
		saveButton = new Button(myResources.getString(SAVE_CHANGES));
		deleteButton = new Button(myResources.getString(DELETE_INSTANCE));
		HBox buttonBox = new HBox();
		buttonBox.getChildren().addAll(saveButton, deleteButton);
		popUp.getChildren().add(buttonBox);
		popUp.setAlignment(Pos.CENTER);
		myScene = new Scene(popUp);
		this.setScene(myScene);
		this.show();
		
	}
	
	public Button getSaveButton() {
		return saveButton;
	}
	
	public Button getDeleteButton() {
		return deleteButton;
	}
	
	public List<HBox> getFieldList() {
		return fieldList;
	}
	
	public RadioButton getVisiblity() {
		return visibilityButton;
	}
}
