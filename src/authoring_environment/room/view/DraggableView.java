package authoring_environment.room.view;


import authoring_environment.room.preview.DraggableNode;
import javafx.beans.property.DoubleProperty;
import structures.data.DataView;

public class DraggableView extends DraggableNode {
	private DoubleProperty myWidth;
	private DoubleProperty myHeight;
	private DoubleProperty myX;
	private DoubleProperty myY;
	
	public DraggableView(DoubleProperty x, DoubleProperty y) {
		super(x, y);
	}

	@Override
	public Object getImage() {
		return this;
	}
	
	public DoubleProperty getWidthProperty() {
		return myWidth;
	}
	
	public DoubleProperty getHeightProperty() {
		return myHeight;
	}
	
	public DoubleProperty getXProperty() {
		return myX;
	}
	
	public DoubleProperty getYProperty() {
		return myY;
	}

	@Override
	public double getWidth() {
		return myWidth.get();
	}

	@Override
	public double getHeight() {
		return myHeight.get();
	}

	public void setWidthProperty(DoubleProperty myWidth) {
		this.myWidth = myWidth;
	}

	public void setHeightProperty(DoubleProperty myHeight) {
		this.myHeight = myHeight;
	}

	public void setXProperty(DoubleProperty myX) {
		this.myX = myX;
	}

	public void setYProperty(DoubleProperty myY) {
		this.myY = myY;
	}

}
