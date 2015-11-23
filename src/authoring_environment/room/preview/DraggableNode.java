package authoring_environment.room.preview;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;


public abstract class DraggableNode {
	private boolean draggable;
	private DoubleProperty xCor;
	private DoubleProperty yCor;
	private double xOffset;
	private double yOffset;
	private double myAngle;
	
	public DraggableNode(DoubleProperty x, DoubleProperty y, double angle) {
		draggable = false;
		xCor = x;
		yCor = y;
		myAngle = angle;
	}
	
	public abstract Object getImage();
	
	public abstract double getWidth();
	
	public abstract double getHeight();
	
	public double getAngle() {
		System.out.println("Agnle from image itself is " + myAngle);
		return myAngle;
	}
	
	public void setAngle(double angle) {
		myAngle = angle;
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
		return xCor.get();
	}
	
	public boolean getDraggable() {
		return draggable;
	}
	
	public double getY() {
		return yCor.get();
	}
	
	public void setDraggable(boolean bool) {
		draggable = bool;
	}
	
	public void setX(double x) {
		xCor.set(x);
	}
	
	public void setY(double y) {
		yCor.set(y);
	}

}
