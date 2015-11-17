package authoring_environment.room;

import javafx.scene.image.Image;

public class DraggableNode {
	private boolean draggable;
	private Image image;
	private double xCor;
	private double yCor;
	private double xOffset;
	private double yOffset;
	
	public DraggableNode(Image imageObj, double x, double y) {
		image = imageObj;
		draggable = false;
		xCor = x;
		yCor = y;
	}
	
	public double getXOffset() {
		return xOffset;
	}
	public double getYOffset() {
		return yOffset;
	}
	
	public void setXOffset(double xOff) {
		xOffset = xOff;
	}
	
	public void setYOffset(double yOff) {
		yOffset = yOff;
	}
	
	public double getX() {
		return xCor;
	}
	public boolean getDraggable() {
		return draggable;
	}
	public double getY() {
		return yCor;
	}
	public void setDraggable(boolean bool) {
		draggable = bool;
	}
	public Image getImage() {
		return image;
	}
	public void setX(double x) {
		xCor = x;
	}
	public void setY(double y) {
		yCor = y;
	}


}
