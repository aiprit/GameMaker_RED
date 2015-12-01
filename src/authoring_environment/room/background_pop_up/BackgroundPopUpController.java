package authoring_environment.room.background_pop_up;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import authoring_environment.room.preview.RoomCanvas;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import structures.data.IDataRoom;

public class BackgroundPopUpController {
	private static final String DEFAULT_ROOM_BACKGROUND_COLOR = "DefaultRoomBackgroundColor";
	private static final String PNG = "png";
	private static final String IMAGES_FILEPATH_PREFIX = "ImagesFilePath";
	private static final String FILE_CHOOSER_TAG = "FileChooserTag";
	private static final String BACKGROUND_IMAGE_FILE_CHOOSER = "BackgroundImageFileChooser";
	
	private ResourceBundle myResources;
	private BackgroundPopup view;
	private IDataRoom model;
	
	public BackgroundPopUpController(ResourceBundle resources, RoomCanvas background, IDataRoom room) {
		myResources = resources;
		model = room;
		view = new BackgroundPopup(resources, background);
		if (!room.getBackgroundColor().substring(0, 2).equals("0x")) {
			view.setImageFileName(room.getBackgroundColor());
		}
		view.getSaveButton().setOnAction(e -> changeBackground());
		view.getResetButton().setOnAction(e -> reset());
		view.getUploadButton().setOnAction(e -> launchFileChooser());
		view.setDropdownAndUploadText(model.getBackgroundColor());
	}
	
	private void changeBackground() {
		String backgroundColor = view.getColorDropdown().getValue().toString();
		if (view.getImageFileName() != null && !view.getImageFileName().equals("") 
				&& !view.getImageFileName().equals(myResources.getString(DEFAULT_ROOM_BACKGROUND_COLOR))) {
			backgroundColor = view.getImageFileName();
		} else {
			backgroundColor = view.getColorDropdown().getValue().toString();
		}
		view.getCanvas().setBackgroundColor(backgroundColor);
		model.setBackgroundColor(backgroundColor);
		view.getCanvas().redrawCanvas();
		view.close();
	}
	
	private void reset() {
		view.resetView();
		model.setBackgroundColor(myResources.getString(DEFAULT_ROOM_BACKGROUND_COLOR));
		changeBackground();
	}
	
	private void launchFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(myResources.getString(BACKGROUND_IMAGE_FILE_CHOOSER));
		fileChooser.getExtensionFilters().add(new ExtensionFilter(myResources.getString(FILE_CHOOSER_TAG), "*.png"));
		File file = fileChooser.showOpenDialog(null);
		view.setImageFileName(file.getName());
		model.setBackgroundColor(view.getImageFileName());
		view.getUploadButton().setText(view.getImageFileName());
		try {
			//TODO doesn't quite work yet
			BufferedImage image = ImageIO.read(file);
			File output = new File(myResources.getString(IMAGES_FILEPATH_PREFIX)+file.getName());
			ImageIO.write(image, PNG, output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
