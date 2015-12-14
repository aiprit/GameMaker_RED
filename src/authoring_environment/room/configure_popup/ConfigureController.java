// This entire file is part of my masterpiece.
// Ankit Kayastha

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
	private IConfigure configure;
	private ConfigureModel model;
	private DataInstance myDataInstance;
	private DraggableImage myDragImage;
	private Consumer<Void> myDrawFunction;
	private final String SCALE_ERROR = "ScaleError";
	private final String TRANSPARENCY_ERROR = "TransparencyError";
	private final ResourceBundle myResources;
	private final int SCALE_MIN = 0;
	private final int ALPHA_MIN = 0;
	private final int ALPHA_MAX = 1;
	private final int NUM_INPUTS = 10;
	
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
		Double[] doublesToSet = {myDataInstance.getVelocity().x, myDataInstance.getVelocity().y, myDataInstance.getAngularVelocity(), myDataInstance.getScaleX(), myDataInstance.getScaleY(), myDataInstance.getAngle(), myDataInstance.getAlpha(), myDataInstance.getFriction(), myDataInstance.getGravity().x, myDataInstance.getGravity().y};
		setInput(NUM_INPUTS, fieldList, doublesToSet);
		RadioButton visiblityButton = configure.getVisibility();
		visiblityButton.setSelected(myDataInstance.isVisible());
		UpdateDragImage updater = new UpdateDragImage(myDragImage, myDataInstance);
		updater.updateFromInstance();
	}
	
	private void onSave() throws InvalidConfigureException {
		List<TextField> fieldList = configure.getFieldList();
		double[] enteredValues = getInput(NUM_INPUTS, fieldList);
		model.setVelocity(enteredValues[0], enteredValues[1]);
		model.setAngularVelocity(enteredValues[2]);
		double scaleX = enteredValues[3];
		double scaleY = enteredValues[4];
		if (scaleX < SCALE_MIN || scaleY < SCALE_MIN)
			throw new InvalidConfigureException(myResources.getString(SCALE_ERROR)); 
		else {
			model.setScale(scaleX, scaleY);
			myDragImage.setScale(scaleX, scaleY);
		}
		double angle = enteredValues[5];
		model.setAngle(angle);
		myDragImage.setAngle(angle);
		double transparency = enteredValues[6];
		if (transparency < ALPHA_MIN || transparency > ALPHA_MAX) {
			throw new InvalidConfigureException(myResources.getString(TRANSPARENCY_ERROR));
		}
		else {
			model.setAlpha(transparency);
			myDragImage.setAlpha(transparency);
		}
		model.setFriction(enteredValues[7]);
		model.setGravity(enteredValues[8], enteredValues[9]);
		RadioButton visibilityButton = configure.getVisibility();
		model.setVisibility(visibilityButton.isSelected());
		myDragImage.setVisibility(visibilityButton.isSelected());
		configure.close();
		myDrawFunction.accept(null);
	}
	
	private double[] getInput(int n, List<TextField> fieldList) {
		double[] arrToReturn = new double[n];
		for (int i = 0; i < n; i++) {
			arrToReturn[i] = Double.parseDouble(fieldList.get(i).getText());
		}
		return arrToReturn;
	}
	
	private void setInput(int n, List<TextField> fieldList, Double[] doublesToSet) {
		for (int i = 0; i< n; i++) {
			fieldList.get(i).setText(Double.toString(doublesToSet[i]));
		}
	}
}