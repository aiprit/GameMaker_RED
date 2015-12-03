package authoring_environment.room.view;

import structures.data.IDataView;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ViewController {
	private IDataView model;
	private DraggableView view;
	private final boolean VIEW_BOOL = true;
	private final double ANGLE = 0;
	private final double SCALE_X = 1;
	private final double SCALE_Y = 1;
	private final double ALPHA = 1;
	public ViewController(IDataView dataView) {
		model = dataView;
		DoubleProperty x = new SimpleDoubleProperty();
		DoubleProperty y = new SimpleDoubleProperty();
		x.set(dataView.getView().x());
		y.set(dataView.getView().y());
		view = new DraggableView(x, y, ANGLE, VIEW_BOOL , SCALE_X, SCALE_Y, ALPHA);
		addListeners(x, y);
	}
	
	public DraggableView getDraggableView() {
		return view;
	}

	
	private void addListeners(DoubleProperty x, DoubleProperty y) {
		DoubleProperty width = new SimpleDoubleProperty();
		DoubleProperty height = new SimpleDoubleProperty();
		width.set(model.getView().width());
		height.set(model.getView().height());
		view.setWidthProperty(width);
		view.setHeightProperty(height);
		view.setXProperty(x);
		view.setYProperty(y);
		width.addListener(dataViewListener());
		height.addListener(dataViewListener());
		x.addListener(dataViewListener());
		y.addListener(dataViewListener());
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
		model.setView(new utils.rectangle.Rectangle(view.getXProperty().get(), view.getYProperty().get(), 
				view.getWidthProperty().get(), view.getHeightProperty().get()));
	}
}
