package authoring_environment.room.configure_popup;

import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import authoring_environment.room.InvalidConfigureException;
import authoring_environment.room.object_instance.DraggableImage;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import structures.data.DataInstance;

public class ConfigureController {
	private ConfigureView configure;
	private ConfigureModel model;
	private DataInstance myDataInstance;
	private DraggableImage myDragImage;
	private Consumer<Void> myDrawFunction;
	private final String SCALE_ERROR = "ScaleError";
	private final String TRANSPARENCY_ERROR = "TransparencyError";
	private final ResourceBundle myResources;
	//TODO think about using interface to isolate set and get methods for DataInstance
	public ConfigureController(ResourceBundle resources, DataInstance dataInstance, DraggableImage dragImage, Consumer<Void> redrawFunc) {
		configure = new ConfigureView(resources);
		model = new ConfigureModel(dataInstance);
		myDataInstance = dataInstance;
		myDragImage = dragImage;
		myDrawFunction = redrawFunc;
		myResources = resources;
	}
	
	public void initialize() {
		populatePopUp();
		configure.getSaveButton().setOnAction(e -> {
		try {
			onSave(); 
		}
		catch (InvalidConfigureException exception) {
			Alert myAlert = new Alert(AlertType.ERROR);
			myAlert.setContentText(exception.getBadInput());
			myAlert.showAndWait();
		}
		});
	}
	
	private void populatePopUp() {
		List<TextField> fieldList = configure.getFieldList();
		Double[] doublesToSet = {myDataInstance.getVelocity().x, myDataInstance.getVelocity().y, myDataInstance.getAngularVelocity(), myDataInstance.getScaleX(), myDataInstance.getScaleY(), myDataInstance.getAngle(), myDataInstance.getAlpha()};
		setInput(7, fieldList, doublesToSet);
		RadioButton visiblityButton = configure.getVisiblity();
		visiblityButton.setSelected(myDataInstance.isVisible());
		myDragImage.setAngle(myDataInstance.getAngle());
		myDragImage.setVisibility(myDataInstance.isVisible());
		myDragImage.setScale(myDataInstance.getScaleX(), myDataInstance.getScaleY());
		myDragImage.setAlpha(myDataInstance.getAlpha());
	}
	
	private void onSave() throws InvalidConfigureException {
		List<TextField> fieldList = configure.getFieldList();
		double velocityFieldX = getInput(0, fieldList);
		double velocityFieldY = getInput(1, fieldList);
		model.setVelocity(velocityFieldX, velocityFieldY);
		double angularVelocity = getInput(2, fieldList);
		model.setAngularVelocity(angularVelocity);
		double scaleX = getInput(3, fieldList);
		double scaleY = getInput(4, fieldList);
		if (scaleX < 0 || scaleY < 0)
			throw new InvalidConfigureException(myResources.getString(SCALE_ERROR)); 
		else {
			model.setScale(scaleX, scaleY);
			myDragImage.setScale(scaleX, scaleY);
		}
		double angle = getInput(5, fieldList);
		model.setAngle(angle);
		myDragImage.setAngle(angle);
		double transparency = getInput(6, fieldList);
		if (transparency < 0 || transparency > 1) {
			throw new InvalidConfigureException(myResources.getString(TRANSPARENCY_ERROR));
		}
		else {
			model.setAlpha(transparency);
			myDragImage.setAlpha(transparency);
		}
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
	
	private void setInput(int n, List<TextField> fieldList, Double[] doublesToSet) {
		for (int i = 0; i< n; i++) {
			fieldList.get(i).setText(Double.toString(doublesToSet[i]));
		}
	}
}
