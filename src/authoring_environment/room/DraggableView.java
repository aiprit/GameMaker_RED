package authoring_environment.room;


import javafx.beans.property.DoubleProperty;
import structures.data.DataView;

public class DraggableView extends DraggableNode {
	private RoomView myView;
	
	public DraggableView(DataView view, DoubleProperty x, DoubleProperty y) {
		super(x, y);
		myView = new RoomView(view, x, y);
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
	
	public DataView getDataView() {
		return myView.getDataView();
	}

}
