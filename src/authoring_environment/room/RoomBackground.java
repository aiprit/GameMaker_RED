package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class RoomBackground extends Canvas {
	public static final Color DEFAULT_COLOR = Color.WHITE;
	
	private Color myColor;
	private Image myImage;
	private String myImageFileName;

	public RoomBackground(ResourceBundle resources) {
		super(Double.parseDouble(resources.getString("PreviewWidth")), 
				Double.parseDouble(resources.getString("PreviewHeight"))-1);
		myColor = DEFAULT_COLOR;
		setColorFill(DEFAULT_COLOR);
	}

	private void setColorFill(Color fill) {
		this.getGraphicsContext2D().setFill(fill);
		this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	private void setImageFill(Image image) {
		this.getGraphicsContext2D().setFill(new ImagePattern(image));
		this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());
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
			setImageFill(image);
			myImage = image;
			myImageFileName = fileName;
			myColor = null;
		} else if (color != null){
			setColorFill(color);
			myColor = color;
			myImage = null;
			myImageFileName = null;
		} else {
			setColorFill(DEFAULT_COLOR);
			myColor = DEFAULT_COLOR;
			myImage = image;
			myImageFileName = fileName;
		}
	}
	
}
