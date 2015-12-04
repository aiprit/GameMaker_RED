package authoring_environment.room.configure_popup;

import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import authoring_environment.room.object_instance.DraggableImage;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import structures.data.DataInstance;

public class ConfigureController {
	private ConfigureView configure;
	private ConfigureModel model;
	private DataInstance myDataInstance;
	private DraggableImage myDragImage;
	private Consumer<Void> myDrawFunction;
	
	//TODO think about using interface to isolate set and get methods for DataInstance
	public ConfigureController(ResourceBundle resources, DataInstance dataInstance, DraggableImage dragImage, Consumer<Void> redrawFunc) {
		configure = new ConfigureView(resources);
		model = new ConfigureModel(dataInstance);
		myDataInstance = dataInstance;
		myDragImage = dragImage;
		myDrawFunction = redrawFunc;
	}
	
	public void initialize() {
		populatePopUp();
		configure.getSaveButton().setOnAction(e -> onSave());
	}
	
	private void populatePopUp() {
		List<TextField> fieldList = configure.getFieldList();
		setInput(0, fieldList, myDataInstance.getVelocity().x);
		setInput(1, fieldList, myDataInstance.getVelocity().y);
		setInput(2, fieldList, myDataInstance.getAngularVelocity());
		setInput(3, fieldList, myDataInstance.getScaleX());
		setInput(4, fieldList, myDataInstance.getScaleY());
		setInput(5, fieldList, myDataInstance.getAngle());
		setInput(6, fieldList, myDataInstance.getAlpha());
		RadioButton visiblityButton = configure.getVisiblity();
		visiblityButton.setSelected(myDataInstance.isVisible());
		myDragImage.setAngle(myDataInstance.getAngle());
		myDragImage.setVisibility(myDataInstance.isVisible());
		myDragImage.setScale(myDataInstance.getScaleX(), myDataInstance.getScaleY());
		myDragImage.setAlpha(myDataInstance.getAlpha());
	}
	
	private void onSave() {
		List<TextField> fieldList = configure.getFieldList();
		double velocityFieldX = getInput(0, fieldList);
		double velocityFieldY = getInput(1, fieldList);
		model.setVelocity(velocityFieldX, velocityFieldY);
		double angularVelocity = getInput(2, fieldList);
		model.setAngularVelocity(angularVelocity);
		double scaleX = getInput(3, fieldList);
		double scaleY = getInput(4, fieldList);
		model.setScale(scaleX, scaleY);
		myDragImage.setScale(scaleX, scaleY);
		double angle = getInput(5, fieldList);
		model.setAngle(angle);
		myDragImage.setAngle(angle);
		double transparency = getInput(6, fieldList);
		model.setAlpha(transparency);
		myDragImage.setAlpha(transparency);
		RadioButton visibilityButton = configure.getVisiblity();
		model.setVisibility(visibilityButton.isSelected());
		myDragImage.setVisibility(visibilityButton.isSelected());
		configure.close();
		myDrawFunction.accept(null);
	}

	
	//TODO fix this call, used to redraw canvas and pass to room controller upon deleting instance
	public ConfigureView getConfigureView() {
		return configure;
	}
	
	private double getInput(int n, List<TextField> fieldList) {
		return Double.parseDouble(fieldList.get(n).getText());
	}
	
	private void setInput(int n, List<TextField> fieldList, double doubleToSet) {
		fieldList.get(n).setText(Double.toString(doubleToSet));
	}
}
