package authoring_environment.room.background_pop_up;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ResourceBundle;

import authoring_environment.room.preview.RoomCanvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import structures.data.DataRoom;

public class BackgroundPopUpController {
	private static final String FILE_CHOOSER_TAG = "FileChooserTag";
	private static final String BACKGROUND_IMAGE_FILE_CHOOSER = "BackgroundImageFileChooser";
	
	private ResourceBundle myResources;
	
	private BackgroundPopup view;
	private DataRoom model;
	
	public BackgroundPopUpController(ResourceBundle resources, RoomCanvas background, DataRoom room) {
		myResources = resources;
		model = room;
		view = new BackgroundPopup(resources, background);
		view.setOnCloseRequest(e -> changeBackground());
		view.getResetButton().setOnAction(e -> reset());
		view.getUploadButton().setOnAction(e -> launchFileChooser());
		view.setDropdownAndUploadText(model.getBackgroundColor());
	}
	
	private void changeBackground() {
		String backgroundColor = view.getColorDropdown().getValue().toString();
		if (view.getImageFileName() != null) {
			backgroundColor = view.getImageFileName();
		} else {
			backgroundColor = view.getColorDropdown().getValue().toString();
		}
		view.getCanvas().setBackgroundColor(backgroundColor);
		model.setBackgroundColor(backgroundColor);
		view.getCanvas().redrawCanvas();
	}
	
	private void reset() {
		view.resetView();
		model.setBackgroundColor(view.getCanvas().DEFAULT_COLOR.toString());
		changeBackground();
	}
	
	private void launchFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(myResources.getString(BACKGROUND_IMAGE_FILE_CHOOSER));
		fileChooser.getExtensionFilters().add(new ExtensionFilter(myResources.getString(FILE_CHOOSER_TAG), "*.png"));
		File file = fileChooser.showOpenDialog(null);
		String filePath = file.toString();
		view.setImageFileName(file.toString().substring(filePath.lastIndexOf('/') + 1, filePath.length()));
		model.setBackgroundColor(view.getImageFileName());
		view.getUploadButton().setText(view.getImageFileName());
	}

}
