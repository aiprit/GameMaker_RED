package authoring_environment.room.object_instance;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import structures.data.DataInstance;
import structures.data.DataObject;

public class ObjectInstanceController {
	private DraggableImage view;
	private DataInstance model;
	private DoubleProperty myX;
	private DoubleProperty myY;

	public ObjectInstanceController(DataObject object, DoubleProperty x, DoubleProperty y) {
		view = new DraggableImage(object.getSprite().getImage(), x, y);
		model = new DataInstance(object, x.get(), y.get());
		myX = x;
		myY = y;
		addListeners();
		
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
		model.setPosition(myX.get(), myY.get());
	}
	
	public DraggableImage getDraggableImage() {
		return view;
	}
	
	public DataInstance getDataInstance() {
		return model;
	}
}
