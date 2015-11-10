package authoring_environment;

import javafx.geometry.Point2D;

public interface IObject {
	public void addEvent(IEvent e);
	
	public void deleteEvent(IEvent e);
	
	public void addSprite(Sprite s);
	
	public void setName(String name);
	
	public String getName();
	
	public void setSize(double width, double height);
	
	public double[] getSize();
	
	public void setPosition(Point2D pos);
	
	public Point2D getPosition();
	
	public void setVisibility(boolean visible);
	
	public boolean isVisible();
	
	public void setRotation(double angle);
	
	public double getRotation();
}
