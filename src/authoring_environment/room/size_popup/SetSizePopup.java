package authoring_environment.room.size_popup;

import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SetSizePopup extends Stage {
	private static final String SET_ROOM_SIZE = "SetRoomSize";
	private static final String BACKGROUND_POPUP_WIDTH = "BackgroundPopupWidth";
	private static final String BACKGROUND_POPUP_HEIGHT = "BackgroundPopupHeight";
	private static final String ROOM_WIDTH_LABEL = "RoomWidthLabel";
	private static final String ROOM_HEIGHT_LABEL = "RoomHeightLabel";
	private static final String SAVE = "SaveTitle";
	
	private ResourceBundle myResources;
	private Group myRoot;
	private Button mySaveButton;
	private TextField myWidth;
	private TextField myHeight;
	
	public SetSizePopup(ResourceBundle resources) {
		super();
		myResources = resources;
		this.setTitle(myResources.getString(SET_ROOM_SIZE));
		this.setWidth(Double.parseDouble(myResources.getString(BACKGROUND_POPUP_WIDTH)));
		this.setHeight(Double.parseDouble(myResources.getString(BACKGROUND_POPUP_HEIGHT)));
		initializeScene();
	}
	
	public Button getSaveButton() {
		return mySaveButton;
	}
	
	private void initializeScene() {
		myRoot = new Group();
		StackPane contents = setContents();
		myRoot.getChildren().addAll(contents);
		Scene s = new Scene(myRoot);
		this.setScene(s);
		this.show();
	}

	private StackPane setContents() {
		StackPane pane = new StackPane();
		pane.getChildren().addAll(createContents(), createSaveButton());
		pane.setMinSize(Double.parseDouble(myResources.getString(BACKGROUND_POPUP_WIDTH)), 
				Double.parseDouble(myResources.getString(BACKGROUND_POPUP_HEIGHT)));
		pane.setAlignment(Pos.CENTER);
		return pane;
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
		return pane;
	}
	
	private Button createSaveButton() {
		mySaveButton = new Button(myResources.getString(SAVE));
		return mySaveButton;
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
