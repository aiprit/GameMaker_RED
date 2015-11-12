package authoring_environment;

import javafx.geometry.Point2D;

public interface IObject {
	void addEvent(IEvent e);
	
	void deleteEvent(IEvent e);
	
	void addSprite(Sprite s);
	
	void setName(String name);
	
	String getName();
	
	void setSize(double width, double height);
	
	double[] getSize();
	
	void setPosition(Point2D pos);
	
	Point2D getPosition();
	
	void setVisibility(boolean visible);
	
	boolean isVisible();
	
	void setRotation(double angle);
	
	double getRotation();
}
