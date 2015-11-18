package authoring_environment.room;

import javafx.scene.image.Image;

public class DraggableImage extends DraggableNode {
	private ObjectInstance myObjectInstance;
	private Image myImage;

	public DraggableImage(ObjectInstance object) {
		super(object.getXProperty(), object.getYProperty());
		myObjectInstance = object;
		myImage = object.getImageView().getImage();
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
