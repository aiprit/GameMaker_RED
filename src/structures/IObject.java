package structures;

import authoring_environment.Sprite;
import javafx.geometry.Point2D;
import structures.data.events.IDataEvent;

public interface IObject {
	void addEvent(IDataEvent e);
	
	void deleteEvent(IDataEvent e);
	
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