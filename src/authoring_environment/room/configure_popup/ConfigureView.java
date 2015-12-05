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
	private static final String FRICTION = "Friction";
	private static final String GRAVITY_X = "GravityX";
	private static final String GRAVITY_Y = "GravityY";
	private static final String VISIBILITY = "Visibility";
	private static final String NAME = "ConfigureParameters";
	private static final int NUM_ROWS = 10;
	private static final String CONFIGURE_WIDTH = "ConfigureWidth";
	private RadioButton visibilityButton;
	private List<TextField> fieldList;
	private GridPane myGridPane;
	
	public ConfigureView(ResourceBundle resources) {
		super(resources, NAME);
		this.setMinWidth(Double.parseDouble(resources.getString(CONFIGURE_WIDTH)));
	}
	
	@Override
	public void setContents() {
		GridPaneHandler handler = new GridPaneHandler();
		myGridPane = new GridPane();
		String[] labelStrings = {myResources.getString(VELOCITY_FIELD_X), myResources.getString(VELOCITY_FIELD_Y), myResources.getString(ANGULAR_VELOCITY), myResources.getString(SCALE_X), myResources.getString(SCALE_Y), myResources.getString(ANGLE), myResources.getString(TRANSPARENCY), myResources.getString(FRICTION), myResources.getString(GRAVITY_X), myResources.getString(GRAVITY_Y)};
		fieldList = handler.setUpGridPane(NUM_ROWS, labelStrings, myGridPane);
		myGridPane.add(new Label(myResources.getString(VISIBILITY)), 0, NUM_ROWS);
		visibilityButton = new RadioButton();
		myGridPane.add(visibilityButton, 1, NUM_ROWS);
		myGridPane.setAlignment(Pos.CENTER);
		myContentsBox.getChildren().add(myGridPane);
	}
	
	public List<TextField> getFieldList() {
		return fieldList;
	}
	
	public RadioButton getVisiblity() {
		return visibilityButton;
	}
}
