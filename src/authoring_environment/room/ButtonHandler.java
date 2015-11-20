package authoring_environment.room;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoring_environment.room.preview.RoomPreview;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import structures.data.DataRoom;

public class ButtonHandler {
	private static final String BUTTON_TOOLBAR_HEIGHT = "ButtonToolbarHeight";
	private List<RoomEditorButton> myButtons;
	private ResourceBundle myResources;

	public ButtonHandler(ResourceBundle resources, RoomPreview roomPreview, DataRoom room) {
		myResources = resources;
		myButtons = new ArrayList<RoomEditorButton>();
		addButtons(roomPreview, room);
	}
	
	/**
	 * refactor later to use reflection
	 */
	//TODO
	private void addButtons(RoomPreview preview, DataRoom room) {
		//myButtons.add(new SetSizeButton(myResources, preview.getRoomBackground()));
		myButtons.add(new SetBackgroundButton(myResources, preview.getCanvas(), room));
		//myButtons.add(new ShowViewButton(myResources));
	}
	
	public HBox getButtons() {
		HBox pane = new HBox();
		pane.getChildren().addAll(myButtons);
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setSpacing(20);
		return pane;
	}
}

