package authoring_environment.room.object_instance;

import authoring_environment.room.preview.DraggableNode;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;

public class DraggableImage extends DraggableNode {
	private Image myImage;
	private double myAngle;
	public DraggableImage(Image image, DoubleProperty x, DoubleProperty y, double angle) {
		super(x, y, angle);
		myImage = image;
		myAngle = angle;
	}
	
	@Override
	public Image getImage() {
		return myImage;
	}

	@Override
	public double getWidth() {
		return myImage.getWidth();
	}

	@Override
	public double getHeight() {
		return myImage.getHeight();
	}
	
}
