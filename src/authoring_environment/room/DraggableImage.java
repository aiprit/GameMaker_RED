package authoring_environment.room;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;

public class DraggableImage extends DraggableNode {
	private Image image;

	public DraggableImage(Image im, DoubleProperty x, DoubleProperty y) {
		super(x, y);
		image = im;
	}
	
	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public double getWidth() {
		return image.getWidth();
	}

	@Override
	public double getHeight() {
		return image.getHeight();
	}
	
}
