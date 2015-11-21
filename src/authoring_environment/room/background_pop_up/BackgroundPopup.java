package authoring_environment.room.background_pop_up;

import java.util.ResourceBundle;

import authoring_environment.room.preview.RoomCanvas;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BackgroundPopup extends Stage {
	private static final String RESET_BUTTON_TITLE = "ResetButtonTitle";
	private static final int GRID_SPACING = 15;
	private static final String COLOR_DROPDOWN_PROMPT = "ColorDropdownPrompt";
	private static final int CONTENTS_SPACING = 30;
	private static final String UPLOAD_BACKGROUND_IMAGE = "UploadBackgroundImage";
	private static final String SET_BACKGROUND = "Background";
	private static final String BACKGROUND_POPUP_WIDTH = "BackgroundPopupWidth";
	private static final String BACKGROUND_POPUP_HEIGHT = "BackgroundPopupHeight";
	private static final String OR = "Or";
	
	private ResourceBundle myResources;
	private Group myRoot;
	private Button myUploadButton;
	private ColorDropdown myColorDropdown;
	private Button myResetButton;
	private String myImageFileName;
	private RoomCanvas myRoomBackground;
	
	public BackgroundPopup(ResourceBundle resources, RoomCanvas background) {
		super();
		myResources = resources;
		myRoomBackground = background;
		this.setTitle(myResources.getString(SET_BACKGROUND));
		this.setWidth(Double.parseDouble(myResources.getString(BACKGROUND_POPUP_WIDTH)));
		this.setHeight(Double.parseDouble(myResources.getString(BACKGROUND_POPUP_HEIGHT)));
		initializeScene();
	}
	
	public RoomCanvas getCanvas() {
		return myRoomBackground;
	}
	
	public ColorDropdown getColorDropdown() {
		return myColorDropdown;
	}
	
	public String getImageFileName() {
		return myImageFileName;
	}
	
	public Button getResetButton() {
		return myResetButton;
	}
	
	public Button getUploadButton() {
		return myUploadButton;
	}
	
	public void setImageFileName(String filename) {
		myImageFileName = filename;
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
		pane.getChildren().add(createContents());
		pane.setMinSize(Double.parseDouble(myResources.getString(BACKGROUND_POPUP_WIDTH)), 
				Double.parseDouble(myResources.getString(BACKGROUND_POPUP_HEIGHT)));
		pane.setAlignment(Pos.CENTER);
		return pane;
	}
	
	private VBox createContents() {
		VBox contents = new VBox();
		GridPane colorDropdownField = createColorDropdownField();
		Text or = createOrText();
		Button selectAFile = createFileSelector();
		HBox resetButton = createResetButton();
		contents.getChildren().addAll(colorDropdownField, or, selectAFile, resetButton);
		contents.setAlignment(Pos.CENTER);
		contents.setSpacing(CONTENTS_SPACING);
		return contents;
	}
	
	private GridPane createColorDropdownField() {
		GridPane dropdownField = new GridPane();
		Text prompt = new Text(myResources.getString(COLOR_DROPDOWN_PROMPT));
		createColorDropdown();
		dropdownField.add(prompt, 0, 0);
		dropdownField.add(myColorDropdown, 1, 0);
		dropdownField.setHgap(GRID_SPACING);
		dropdownField.setAlignment(Pos.CENTER);
		return dropdownField;
	}
	
	private void createColorDropdown() {
		myColorDropdown = new ColorDropdown();
	}
	
	private Button createFileSelector() {
		myUploadButton = new Button();
		return myUploadButton;
	}

	public void setDropdownAndUploadText(String backgroundColor) {
		if (backgroundColor == null) {
			myColorDropdown.setValue(myRoomBackground.DEFAULT_COLOR);
			myUploadButton.setText(myResources.getString(UPLOAD_BACKGROUND_IMAGE));
		} else {
			try {
				myColorDropdown.setValue(Color.valueOf(backgroundColor));
				myUploadButton.setText(myResources.getString(UPLOAD_BACKGROUND_IMAGE));
			} catch (IllegalArgumentException e) {
				myColorDropdown.setValue(myRoomBackground.DEFAULT_COLOR);
				myUploadButton.setText(backgroundColor);
			}
		}
	}
	
	private Text createOrText() {
		Text or = new Text(myResources.getString(OR));
		or.setScaleX(2);
		or.setScaleY(2);
		return or;
	}
	
	private HBox createResetButton() {
		HBox resetContainer = new HBox();
		myResetButton = new Button(myResources.getString(RESET_BUTTON_TITLE));
		resetContainer.getChildren().add(myResetButton);
		resetContainer.setAlignment(Pos.CENTER_RIGHT);
		return resetContainer;
	}
	
	public void resetView() {
		myColorDropdown.setValue(myRoomBackground.DEFAULT_COLOR);
		myImageFileName = null;
		myUploadButton.setText(myResources.getString(UPLOAD_BACKGROUND_IMAGE));
	}
	
}
