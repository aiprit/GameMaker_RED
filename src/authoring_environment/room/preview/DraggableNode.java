package authoring_environment.room.preview;

import javafx.beans.property.DoubleProperty;


public abstract class DraggableNode {
	private boolean draggable;
	private DoubleProperty xCor;
	private DoubleProperty yCor;
	private double xOffset;
	private double yOffset;
	private double myAngle;
	private boolean myVisibility;
	private double myScaleX;
	private double myScaleY;
	
	public DraggableNode(DoubleProperty x, DoubleProperty y, double angle, boolean visibility, double scaleX, double scaleY) {
		draggable = false;
		xCor = x;
		yCor = y;
		myAngle = angle;
		myVisibility = visibility;
		myScaleX = scaleX;
		myScaleY = scaleY;
	}
	
	public abstract Object getImage();
	
	public abstract double getWidth();
	
	public abstract double getHeight();
	
	public double getAngle() {
		return myAngle;
	}
	
	public void setAngle(double angle) {
		myAngle = angle;
	}
	
	public boolean getVisibility() {
		return myVisibility;
	}
	
	public void setVisibility(boolean bool) {
		myVisibility = bool;
	}
	
	public void setScale(double scaleX, double scaleY) {
		myScaleX = scaleX;
		myScaleY = scaleY;
	}
	
	public double getScaleX() {
		return myScaleX;
	}
	
	public double getScaleY() {
		return myScaleY;
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
