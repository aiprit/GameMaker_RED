package authoring_environment.room.configure_pop_up;

import java.util.List;
import java.util.ResourceBundle;

import authoring_environment.room.HBoxHandler;
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
	private final String VELOCITY_FIELD = "VelocityField";
	private final String SCALE_X = "ScaleX";
	private final String SCALE_Y = "ScaleY";
	private final String ROTATION = "Rotation";
	private final String VISIBILITY = "Visibility";
	private Button saveButton;
	private VBox popUp;
	private ResourceBundle myResources;
	private RadioButton visibilityButton;
	public void initializePopUp() {
		System.out.println("Here");
		HBoxHandler handler = new HBoxHandler();
		String[] labelStrings = {myResources.getString(VELOCITY_FIELD), myResources.getString(SCALE_X), myResources.getString(SCALE_Y), myResources.getString(ROTATION)};
		List<HBox> fieldList = handler.createHBoxes(4, labelStrings);
		for (HBox box : fieldList) {
			popUp.getChildren().add(box);
		}
		HBox visibility = new HBox();
		visibility.getChildren().add(new Label(myResources.getString(VISIBILITY)));
		visibilityButton = new RadioButton();
		visibility.getChildren().add(visibilityButton);
		popUp.getChildren().add(visibility);
		saveButton = new Button("Save Changes");
		popUp.getChildren().add(saveButton);
		popUp.setAlignment(Pos.CENTER);
		myScene = new Scene(popUp);
		this.setScene(myScene);
		this.show();
		
		//TODO set action on save button clicked
	}
	public Button getSaveButton() {
		return saveButton;
	}
	
	public double getInput(int n) {
		HBox box = ((HBox) popUp.getChildren().get(n));
		String text = box.getChildren().get(1).toString();
		return Double.parseDouble(text);
	}
	
	public RadioButton getVisiblity() {
		return visibilityButton;
	}
}
