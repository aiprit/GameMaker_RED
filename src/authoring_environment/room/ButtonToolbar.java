package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonToolbar {
	
	private ResourceBundle myResources;
	private static final String SIZE_TITLE = "SizeTitle";
	private static final String BACKGROUND = "Background";
	private static final String VIEW_TITLE = "ViewTitle";
	private static final String SAVE_TITLE = "SaveTitle";

	public ButtonToolbar(ResourceBundle resources) {
		myResources = resources;
	}
	
	public HBox createButtons() {
		HBox hbox = new HBox();
		ButtonHandler handler = new ButtonHandler();
		String[] buttonLabels = {SIZE_TITLE, BACKGROUND, VIEW_TITLE, SAVE_TITLE};
		Button[] arr = handler.create(4, buttonLabels);
		hbox.getChildren().addAll(arr);
		hbox.setSpacing(50);
		return hbox;
	}
	
}
