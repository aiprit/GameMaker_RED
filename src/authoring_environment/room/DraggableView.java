package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.beans.property.DoubleProperty;

public class DraggableView extends DraggableNode {
	RoomView myView;

	public DraggableView(ResourceBundle resources, DoubleProperty x, DoubleProperty y) {
		super(x, y);
		myView = new RoomView(resources, x, y);
	}

	@Override
	public Object getImage() {
		return myView;
	}
	
	public double getWidth() {
		return myView.getWidth();
	}
	
	public double getHeight() {
		return myView.getHeight();
	}

}
