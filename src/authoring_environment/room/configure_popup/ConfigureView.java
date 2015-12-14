// This entire file is part of my masterpiece.
// Ankit Kayastha

package authoring_environment.room.configure_popup;

import java.util.List;
import java.util.ResourceBundle;

import authoring_environment.room.PopupTemplate;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ConfigureView extends PopupTemplate implements IConfigure {

	private final String VELOCITY_FIELD_X = "VelocityFieldX";
	private final String VELOCITY_FIELD_Y = "VelocityFieldY";
	private final String ANGULAR_VELOCITY = "AngularVelocity";
	private final String SCALE_X = "ScaleX";
	private final String SCALE_Y = "ScaleY";
	private final String ANGLE = "Angle";
	private final String TRANSPARENCY = "Transparency";
	private final String FRICTION = "Friction";
	private final String GRAVITY_X = "GravityX";
	private final String GRAVITY_Y = "GravityY";
	private final String VISIBILITY = "Visibility";
	private final static String NAME = "ConfigureParameters";
	private final int NUM_ROWS = 10;
	private final String CONFIGURE_WIDTH = "ConfigureWidth";
	private RadioButton visibilityButton;
	private List<TextField> fieldList;
	private GridPane myGridPane;
	private final int LABEL_COL_NUM = 0;
	private final int VISIBILITY_BUTTON_COL = 1;
	
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
		myGridPane.add(new Label(myResources.getString(VISIBILITY)), LABEL_COL_NUM, NUM_ROWS);
		visibilityButton = new RadioButton();
		myGridPane.add(visibilityButton, VISIBILITY_BUTTON_COL, NUM_ROWS);
		myGridPane.setAlignment(Pos.CENTER);
		myContentsBox.getChildren().add(myGridPane);
	}
	@Override
	public List<TextField> getFieldList() {
		return fieldList;
	}
	
	@Override
	public RadioButton getVisibility() {
		return visibilityButton;
	}
}