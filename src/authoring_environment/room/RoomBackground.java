package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class RoomBackground extends Rectangle {
	public static final Color DEFAULT_COLOR = Color.WHITE;
	
	private Color myColor;
	private Image myImage;
	private String myImageFileName;

	public RoomBackground(ResourceBundle resources) {
		super(Double.parseDouble(resources.getString("PreviewWidth")), 
				Double.parseDouble(resources.getString("PreviewHeight"))-1);
		myColor = DEFAULT_COLOR;
		this.setFill(DEFAULT_COLOR);
	}

	public Color getColor() {
		return myColor;
	}

	public Image getImage() {
		return myImage;
	}

	public String getImageFileName() {
		return myImageFileName;
	}
	
	public void setImage(Image image) {
		myImage = image;
	}

	public void setImageFileName(String name) {
		myImageFileName = name;
	}
	
	//TODO write this to IRoom as well
	public void setBackground(Color color, Image image, String fileName) {
		if (image != null) {
			this.setFill(new ImagePattern(image));
			myImage = image;
			myImageFileName = fileName;
			myColor = null;
		} else if (color != null){
			this.setFill(color);
			myColor = color;
			myImage = null;
			myImageFileName = null;
		} else {
			this.setFill(DEFAULT_COLOR);
			myColor = DEFAULT_COLOR;
			myImage = image;
			myImageFileName = fileName;
		}
	}
	
}
