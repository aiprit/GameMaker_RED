package authoring_environment.room.size_popup;

import java.util.ResourceBundle;

import authoring_environment.room.PopupTemplate;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SetSizePopup extends PopupTemplate {
	private static final String SET_ROOM_SIZE = "SetRoomSize";
	private static final String ROOM_WIDTH_LABEL = "RoomWidthLabel";
	private static final String ROOM_HEIGHT_LABEL = "RoomHeightLabel";

	private TextField myWidth;
	private TextField myHeight;
	
	public SetSizePopup(ResourceBundle resources) {
		super(resources, SET_ROOM_SIZE);
	}
	
	@Override
	protected void setContents() {
		myContentsBox.getChildren().add(createContents());
	}
	
	private GridPane createContents() {
		GridPane pane = new GridPane();
		Text width = new Text(myResources.getString(ROOM_WIDTH_LABEL));
		Text height = new Text(myResources.getString(ROOM_HEIGHT_LABEL));
		myWidth = new TextField();
		myHeight = new TextField();
		pane.add(width, 0, 0);
		pane.add(myWidth, 1, 0);
		pane.add(height, 0, 1);
		pane.add(myHeight, 1, 1);
		pane.setAlignment(Pos.CENTER);
		return pane;
	}
	
	public TextField getWidthField() {
		return myWidth;
	}
	
	public TextField getHeightField() {
		return myHeight;
	}
	
	public String getRoomWidth() {
		return myWidth.getText();
	}
	
	public String getRoomHeight() {
		return myHeight.getText();
	}

}
