package authoring_environment.room.configure_popup;

import java.util.List;
import java.util.ResourceBundle;

import authoring_environment.room.PopupTemplate;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConfigureView extends PopupTemplate {
	private Scene myScene;
	private final String VELOCITY_FIELD_X = "VelocityFieldX";
	private final String VELOCITY_FIELD_Y = "VelocityFieldY";
	private final String ANGULAR_VELOCITY = "AngularVelocity";
	private final String SCALE_X = "ScaleX";
	private final String SCALE_Y = "ScaleY";
	private final String ANGLE = "Angle";
	private final String TRANSPARENCY = "Transparency";
	private final String VISIBILITY = "Visibility";
	private final String DELETE_INSTANCE = "DeleteInstance";
	private Button deleteButton;
	private RadioButton visibilityButton;
	private List<HBox> fieldList;
	private static final String NAME = "ConfigureParameters";
	
	public ConfigureView(ResourceBundle resources) {
		super(resources, NAME);
	}
	
	@Override
	public void setContents() {
		HBoxHandler handler = new HBoxHandler();
		String[] labelStrings = {myResources.getString(VELOCITY_FIELD_X), myResources.getString(VELOCITY_FIELD_Y), myResources.getString(ANGULAR_VELOCITY), myResources.getString(SCALE_X), myResources.getString(SCALE_Y), myResources.getString(ANGLE), myResources.getString(TRANSPARENCY)};
		fieldList = handler.createHBoxes(7, labelStrings);
		System.out.println(myContentsBox == null);
		for (HBox box : fieldList) {
			myContentsBox.getChildren().add(box);
		}
		HBox visibility = new HBox();
		visibility.getChildren().add(new Label(myResources.getString(VISIBILITY)));
		visibilityButton = new RadioButton();
		visibility.getChildren().add(visibilityButton);
		myContentsBox.getChildren().add(visibility);
		deleteButton = new Button(myResources.getString(DELETE_INSTANCE));
		addButton(deleteButton);
		myContentsBox.setAlignment(Pos.CENTER);
		
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
