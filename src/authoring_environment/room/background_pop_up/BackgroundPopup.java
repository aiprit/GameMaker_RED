package authoring_environment.room.background_pop_up;

import java.util.ResourceBundle;

import authoring_environment.room.PopupTemplate;
import authoring_environment.room.preview.RoomCanvas;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class BackgroundPopup extends PopupTemplate {
	private static final int TEXT_SCALE = 2;
	private static final String RESET_BUTTON_TITLE = "ResetButtonTitle";
	private static final int GRID_SPACING = 15;
	private static final String COLOR_DROPDOWN_PROMPT = "ColorDropdownPrompt";
	private static final int CONTENTS_SPACING = 30;
	private static final String UPLOAD_BACKGROUND_IMAGE = "UploadBackgroundImage";
	private static final String SET_BACKGROUND = "Background";
	private static final String OR = "Or";
	
	private Button myUploadButton;
	private ColorDropdown myColorDropdown;
	private Button myResetButton;
	private String myImageFileName;
	private RoomCanvas myRoomBackground;
	
	public BackgroundPopup(ResourceBundle resources, RoomCanvas background) {
		super(resources, SET_BACKGROUND);
		myRoomBackground = background;
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
	
	@Override
	protected void setContents() {
		GridPane colorDropdownField = createColorDropdownField();
		Text or = createOrText();
		Button selectAFile = createFileSelector();
		createResetButton();
		myContentsBox.getChildren().addAll(colorDropdownField, or, selectAFile);
		myContentsBox.setAlignment(Pos.CENTER);
		myContentsBox.setSpacing(CONTENTS_SPACING);
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
		or.setScaleX(TEXT_SCALE);
		or.setScaleY(TEXT_SCALE);
		return or;
	}
	
	private void createResetButton() {
		myResetButton = new Button(myResources.getString(RESET_BUTTON_TITLE));
		addButton(myResetButton);
	}
	
	public void resetView() {
		myColorDropdown.setValue(myRoomBackground.DEFAULT_COLOR);
		myImageFileName = null;
		myUploadButton.setText(myResources.getString(UPLOAD_BACKGROUND_IMAGE));
	}
}
