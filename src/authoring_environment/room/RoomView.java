package authoring_environment.room;

import structures.data.DataView;
import java.util.ResourceBundle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class RoomView {
	private static final String VIEW_WIDTH = "ViewWidth";
	private static final String VIEW_HEIGHT = "ViewHeight";
	
	private ResourceBundle myResources;
	private DataView myDataView;
	private DoubleProperty myWidth;
	private DoubleProperty myHeight;
	private DoubleProperty myX;
	private DoubleProperty myY;
	
	public RoomView(ResourceBundle resources, DoubleProperty x, DoubleProperty y) {
		myResources = resources;
		initializeDoublePropertyValues(x, y);
		myDataView = new DataView("View", myWidth.getValue(), myHeight.getValue(), 
				myX.getValue(), myY.getValue());
	}
	
	public double getWidth() {
		return myWidth.get();
	}
	
	public double getHeight() {
		return myHeight.get();
	}
	
	public double getX() {
		return myX.get();
	}
	
	public double getY() {
		return myY.get();
	}
	
	private void initializeDoublePropertyValues(DoubleProperty x, DoubleProperty y) {
		myWidth = new SimpleDoubleProperty();
		myWidth.set(Double.parseDouble(myResources.getString(VIEW_WIDTH)));
		myHeight = new SimpleDoubleProperty();
		myHeight.set(Double.parseDouble(myResources.getString(VIEW_HEIGHT)));
		myX = x;
		myY = y;
		myWidth.addListener(dataViewListener());
		myHeight.addListener(dataViewListener());
		myX.addListener(dataViewListener());
		myY.addListener(dataViewListener());
	}
	
	private ChangeListener<? super Number> dataViewListener() {
		ChangeListener<? super Number> listener = new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				updateDataViewObject();
			}
		};
		return listener;
	}
	
	private void updateDataViewObject() {
		myDataView.setView(new utils.Rectangle(myX.get(), myY.get(), myWidth.get(), myHeight.get()));
	}
}
