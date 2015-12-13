// This entire file is part of my masterpiece.
// Elizabeth Dowd
package authoring_environment.room.object_instance;

import authoring_environment.room.preview.DraggableNode;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import utils.Point;

public class DraggableImage extends DraggableNode {
	private Image myImage;
	
	public DraggableImage(Image image, DoubleProperty x, DoubleProperty y, double angle, boolean visibility, double scaleX, double scaleY, double alpha) {
		super(x, y, angle, visibility, scaleX, scaleY, alpha);
		myImage = image;
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
	
	public boolean widthOutOfBounds(double canvasWidth) {
		return (this.getX() + this.getWidth()*this.getScaleX()) > canvasWidth;
	}
	
	public boolean heightOutOfBounds(double canvasHeight) {
		return (this.getY() + this.getHeight()*this.getScaleY()) > canvasHeight;
	}
	
	public double getScaleXAdjustment(double canvasWidth) {
		return (canvasWidth - this.getX()) / (this.getWidth() * this.getScaleX());
	}
	
	public double getScaleYAdjustment(double canvasHeight) {
		return (canvasHeight - this.getY()) / (this.getHeight() * this.getScaleY());
	}

	@Override
	public boolean contains(double x, double y) {
		return this.getBounds().contains(new Point(x, y));
	}

	@Override
	public boolean inRoomWidthBounds(double x, double roomWidth) {
		return x >= 0 && x <= roomWidth;
	}

	@Override
	public boolean inRoomHeightBounds(double y, double roomHeight) {
		return y >= 0 && y <= roomHeight;
	}
	
}
