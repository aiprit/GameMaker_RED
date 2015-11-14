package structures;

import javafx.geometry.Point2D;
import structures.data.DataSprite;
import structures.data.events.IDataEvent;

public interface IObject {
	void addEvent(IDataEvent e);
	
	void deleteEvent(IDataEvent e);
	
	void addSprite(DataSprite s);
	
	String getName();
	
	int[] getSize();
	
	void setPosition(Point2D pos);
	
	Point2D getPosition();
	
	void setVisibility(boolean visible);
	
	boolean isVisible();
	
	void setRotation(double angle);
	
	double getRotation();
}
