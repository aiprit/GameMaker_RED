// This entire file is part of my masterpiece.
// Elizabeth Dowd
package authoring_environment.room.preview;

import javafx.beans.property.DoubleProperty;
import utils.rectangle.IRectangle;
import utils.rectangle.Rectangle;


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
	private double myAlpha;
	
	public DraggableNode(DoubleProperty x, DoubleProperty y, double angle, boolean visibility, double scaleX, double scaleY, double alpha) {
		draggable = false;
		xCor = x;
		yCor = y;
		myAngle = angle;
		myVisibility = visibility;
		myScaleX = scaleX;
		myScaleY = scaleY;
		myAlpha = alpha;
	}
	
	public abstract Object getImage();
	
	public abstract double getWidth();
	
	public abstract double getHeight();
	
	public abstract boolean contains(double x, double y);
	
	public abstract boolean inRoomWidthBounds(double x, double roomWidth);
	
	public abstract boolean inRoomHeightBounds(double y, double roomHeight);
	
	public boolean inRoomBounds(double x, double y, double roomWidth, double roomHeight) {
		return inRoomWidthBounds(x, roomWidth) && inRoomHeightBounds(y, roomHeight);
	}
	
	public double getAngle() {
		return myAngle;
	}
	
	public void setAngle(double angle) {
		myAngle = angle;
	}
	
	public IRectangle getBounds() {
		Rectangle rect = new Rectangle(getX(), getY(), getWidth() * getScaleX(), getHeight() * getScaleY());
		rect.center();
		rect.angle(getAngle());
		
		return rect.getImmutable();
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
	
	public double getAlpha() {
		return myAlpha;
	}
	public void setAlpha(double transparency) {
		myAlpha = transparency;
	}
	
}
