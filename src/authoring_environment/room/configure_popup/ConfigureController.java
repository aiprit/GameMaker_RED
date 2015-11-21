package authoring_environment.room.configure_popup;

import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import structures.data.DataInstance;

public class ConfigureController {
	private ConfigureView configure;
	private ConfigureModel model;
	private Consumer<DataInstance> myConsumer;
	private DataInstance myDataInstance;
	//TODO think about using interface to isolate set and get methods for DataInstance
	public ConfigureController(ResourceBundle resources, DataInstance dataInstance, Consumer<DataInstance> consumer) {
		configure = new ConfigureView(resources);
		model = new ConfigureModel(dataInstance);
		myConsumer = consumer;
		myDataInstance = dataInstance;
	}
	
	public ConfigureView getConfigureView() {
		return configure;
	}
	
	public void initialize() {
		configure.initializePopUp();
		populatePopUp();
		System.out.println("Angular Velocity" + myDataInstance.getAlpha());
		configure.getSaveButton().setOnAction(e -> onSave());
		configure.getDeleteButton().setOnAction(e -> onDelete());
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
		double transparency = getInput(6, fieldList);
		model.setAlpha(transparency);
		RadioButton visibilityButton = configure.getVisiblity();
		model.setVisibility(visibilityButton.isSelected());
		configure.close();
	}
	
	private void onDelete() {
		myConsumer.accept(myDataInstance);
		configure.close();
 		//System.out.println("Deleted" + myDataInstance.getAngle());
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
