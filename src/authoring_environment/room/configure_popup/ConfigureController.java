package authoring_environment.room.configure_popup;

import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import structures.data.DataInstance;

public class ConfigureController {
	private ConfigureView configure;
	private ConfigureModel model;
	
	public ConfigureController(ResourceBundle resources, DataInstance dataInstance) {
		configure = new ConfigureView(resources);
		model = new ConfigureModel(dataInstance);
	}
	
	public ConfigureView getConfigureView() {
		return configure;
	}
	
	public void initialize() {
		configure.initializePopUp();
		configure.getSaveButton().setOnAction(e -> onSave());
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
		RadioButton visibilityButton = configure.getVisiblity();
		model.setVisibility(visibilityButton.isSelected());
		configure.close();
	}
	
	private void onDelete() {
		
	}
	
	private double getInput(int n, List<HBox> fieldList) {
		TextField field =  ((TextField) fieldList.get(n).getChildren().get(1));
		return Double.parseDouble(field.getText());
	}
}
