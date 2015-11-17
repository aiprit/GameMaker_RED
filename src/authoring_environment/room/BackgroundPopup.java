package authoring_environment.room;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class BackgroundPopup extends Stage {
	private static final String RESET_BUTTON_TITLE = "ResetButtonTitle";
	private static final int GRID_SPACING = 15;
	private static final String FILE_CHOOSER_TAG = "FileChooserTag";
	private static final String BACKGROUND_IMAGE_FILE_CHOOSER = "BackgroundImageFileChooser";
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
	private Color myColor;
	private String myImageFileName;
	private Image myImageUpload;
	private RoomBackground myRoomBackground;
	
	public BackgroundPopup(ResourceBundle resources, RoomBackground background) {
		super();
		myResources = resources;
		myRoomBackground = background;
		myImageUpload = myRoomBackground.getImage();
		myImageFileName = myRoomBackground.getImageFileName();
		myColor = myRoomBackground.getColor();
		this.setTitle(myResources.getString(SET_BACKGROUND));
		this.setWidth(Double.parseDouble(myResources.getString(BACKGROUND_POPUP_WIDTH)));
		this.setHeight(Double.parseDouble(myResources.getString(BACKGROUND_POPUP_HEIGHT)));
		initializeScene();
		this.setOnCloseRequest(e -> changeBackground());
	}

	private void initializeScene() {
		myRoot = new Group();
		StackPane contents = setContents();
		myRoot.getChildren().addAll(contents);
		Scene s = new Scene(myRoot);
		this.setScene(s);
		s.setFill(Color.ALICEBLUE);
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
		myColorDropdown.setValue(myColor);
	}
	
	private Button createFileSelector() {
		myUploadButton = new Button();
		myUploadButton.setText(myImageFileName == null ? myResources.getString(UPLOAD_BACKGROUND_IMAGE) : myImageFileName);
		myUploadButton.setOnAction(e -> launchFileChooser());
		return myUploadButton;
	}
	
	private void launchFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(myResources.getString(BACKGROUND_IMAGE_FILE_CHOOSER));
		fileChooser.getExtensionFilters().add(new ExtensionFilter(myResources.getString(FILE_CHOOSER_TAG), "*.png"));
		File file = fileChooser.showOpenDialog(null);
		String filePath = file.toString();
		myImageFileName = file.toString().substring(filePath.lastIndexOf('/') + 1, filePath.length());
		myUploadButton.setText(myImageFileName);
		try {
			myImageUpload = new Image(file.toURL().toString());
			//TODO FIX THIS!!
		} catch (MalformedURLException e) {
			System.out.println("bad file");
			e.printStackTrace();
		}
	}
	
	private Text createOrText() {
		Text or = new Text(myResources.getString(OR));
		or.setScaleX(2);
		or.setScaleY(2);
		return or;
	}
	
	private void changeBackground() {
		myColor = myColorDropdown.getValue();
		myRoomBackground.setBackground(myColor, myImageUpload, myImageFileName);
	}
	
	private HBox createResetButton() {
		HBox resetContainer = new HBox();
		Button resetButton = new Button(myResources.getString(RESET_BUTTON_TITLE));
		resetButton.setOnAction(e -> reset());
		resetContainer.getChildren().add(resetButton);
		resetContainer.setAlignment(Pos.CENTER_RIGHT);
		return resetContainer;
	}
	
	private void reset() {
		myColorDropdown.setValue(myRoomBackground.DEFAULT_COLOR);
		myImageUpload = null;
		myImageFileName = null;
		myUploadButton.setText(myResources.getString(UPLOAD_BACKGROUND_IMAGE));
		changeBackground();
	}
	
}
