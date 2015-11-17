package authoring_environment.room;

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


public class ConfigurePopUp extends Stage {
	private Scene myScene;
	private final String VELOCITY_FIELD = "VelocityField";
	private final String SCALE_X = "ScaleX";
	private final String SCALE_Y = "ScaleY";
	private final String ROTATION = "Rotation";
	private final String VISIBILITY = "Visibility";
	private ResourceBundle myResources;
	public ConfigurePopUp(ResourceBundle resources) {
		super();
		myResources = resources;
	}
	
	public void initializePopUp() {
		System.out.println("Here");
		VBox popUp = new VBox();
		HBoxHandler handler = new HBoxHandler();
		String[] labelStrings = {myResources.getString(VELOCITY_FIELD), myResources.getString(SCALE_X), myResources.getString(SCALE_Y), myResources.getString(ROTATION)};
		List<HBox> fieldList = handler.createHBoxes(4, labelStrings);
		for (HBox box : fieldList) {
			popUp.getChildren().add(box);
		}
		HBox visibility = new HBox();
		visibility.getChildren().add(new Label(myResources.getString(VISIBILITY)));
		visibility.getChildren().add(new RadioButton());
		popUp.getChildren().add(visibility);
		popUp.getChildren().add(new Button("Save Changes"));
		popUp.setAlignment(Pos.CENTER);
		myScene = new Scene(popUp);
		this.setScene(myScene);
		this.show();
	}
}
