package authoring_environment.room.configure_popup;

import java.util.List;
import java.util.ResourceBundle;

import authoring_environment.room.PopupTemplate;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ConfigureView extends PopupTemplate {

	private static final String VELOCITY_FIELD_X = "VelocityFieldX";
	private static final String VELOCITY_FIELD_Y = "VelocityFieldY";
	private static final String ANGULAR_VELOCITY = "AngularVelocity";
	private static final String SCALE_X = "ScaleX";
	private static final String SCALE_Y = "ScaleY";
	private static final String ANGLE = "Angle";
	private static final String TRANSPARENCY = "Transparency";
	private static final String VISIBILITY = "Visibility";
	private static final String NAME = "ConfigureParameters";
	private static final int NUM_ROWS = 7;

	private RadioButton visibilityButton;
	private List<TextField> fieldList;
	private GridPane myGridPane;
	
	public ConfigureView(ResourceBundle resources) {
		super(resources, NAME);
		myContentsBox.setPrefWidth(500);
	}
	
	@Override
	public void setContents() {
		HBoxHandler handler = new HBoxHandler();
		myGridPane = new GridPane();
		String[] labelStrings = {myResources.getString(VELOCITY_FIELD_X), myResources.getString(VELOCITY_FIELD_Y), myResources.getString(ANGULAR_VELOCITY), myResources.getString(SCALE_X), myResources.getString(SCALE_Y), myResources.getString(ANGLE), myResources.getString(TRANSPARENCY)};
		fieldList = handler.setUpGridPane(NUM_ROWS, labelStrings, myGridPane);
		myGridPane.add(new Label(myResources.getString(VISIBILITY)), 0, NUM_ROWS);
		visibilityButton = new RadioButton();
		myGridPane.add(visibilityButton, 1, NUM_ROWS);
		myGridPane.setAlignment(Pos.CENTER);
		myContentsBox.getChildren().add(myGridPane);
		myGridPane.setPrefWidth(500);
		
	}
	
	public List<TextField> getFieldList() {
		return fieldList;
	}
	
	public RadioButton getVisiblity() {
		return visibilityButton;
	}
}
