package authoring_environment.room;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import structures.data.DataInstance;
import structures.data.DataObject;


public class ObjectInstance {
	private ImageView myImage;
	private DataInstance myInstance;
	private DataObject myObject;
	private DoubleProperty myX;
	private DoubleProperty myY;
	
	public ObjectInstance(DataObject object, DoubleProperty x, DoubleProperty y) {
		myObject = object;
		myImage = new ImageView(myObject.getSprite().getImage());
		myX = x;
		myY = y;
		addListeners();
		myInstance = new DataInstance(object, x.get(), y.get());
	}
	
	public DoubleProperty getXProperty() {
		return myX;
	}
	
	public DoubleProperty getYProperty() {
		return myY;
	}
	
	public DataInstance getDataInstance() {
		return myInstance;
	}
	
	public void setDataInstance(DataInstance instance) {
		myInstance = instance;
	}
	
	public DataObject getObject() {
		return myObject;
	}
	
	public ImageView getImageView() {
		return myImage;
	}
	
	private void addListeners() {
		myX.addListener(dataInstanceListener());
		myY.addListener(dataInstanceListener());
	}
	
	private ChangeListener<? super Number> dataInstanceListener() {
		ChangeListener<? super Number> listener = new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				updateDataInstancePosition();
			}
		};
		return listener;
	}
	
	private void updateDataInstancePosition() {
		myInstance.setPosition(myX.get(), myY.get());
	}

}
