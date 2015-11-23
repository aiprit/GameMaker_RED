package authoring_environment.room.configure_popup;

import java.util.List;
import java.util.ResourceBundle;

import authoring_environment.room.object_instance.DraggableImage;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import structures.data.DataInstance;

public class ConfigureController {
	private ConfigureView configure;
	private ConfigureModel model;
	private DataInstance myDataInstance;
	private DraggableImage myDragImage;
	//TODO think about using interface to isolate set and get methods for DataInstance
	public ConfigureController(ResourceBundle resources, DataInstance dataInstance, DraggableImage dragImage) {
		configure = new ConfigureView(resources);
		model = new ConfigureModel(dataInstance);
		myDataInstance = dataInstance;
		myDragImage = dragImage;
	}
	
	public void initialize() {
		populatePopUp();
		configure.getSaveButton().setOnAction(e -> onSave());
	}
	
	private void populatePopUp() {
		List<HBox> fieldList = configure.getFieldList();
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
	}
	
	private void onSave() {
		List<HBox> fieldList = configure.getFieldList();
		double velocityFieldX = getInput(0, fieldList);
		double velocityFieldY = getInput(1, fieldList);
		model.setVelocity(velocityFieldX, velocityFieldY);
		double angularVelocity = getInput(2, fieldList);
		model.setAngularVelocity(angularVelocity);
		double scaleX = getInput(3, fieldList);
		double scaleY = getInput(4, fieldList);
		model.setScale(scaleX, scaleY);
		double angle = getInput(5, fieldList);
		model.setAngle(angle);
		myDragImage.setAngle(angle);
		System.out.println("Drag Image angle is " + myDragImage.getAngle());
		double transparency = getInput(6, fieldList);
		model.setAlpha(transparency);
		RadioButton visibilityButton = configure.getVisiblity();
		model.setVisibility(visibilityButton.isSelected());
		configure.close();
	}

	
	//TODO fix this call, used to redraw canvas and pass to room controller upon deleting instance
	public ConfigureView getConfigureView() {
		return configure;
	}
	
	private double getInput(int n, List<HBox> fieldList) {
		TextField field =  ((TextField) fieldList.get(n).getChildren().get(1));
		return Double.parseDouble(field.getText());
	}
	
	private void setInput(int n, List<HBox> fieldList, double doubleToSet) {
		TextField field = ((TextField) fieldList.get(n).getChildren().get(1));
		field.setText(Double.toString(doubleToSet));
	}
}
