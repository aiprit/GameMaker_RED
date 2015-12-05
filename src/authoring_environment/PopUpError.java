package authoring_environment;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PopUpError {
	public PopUpError(){
		alertPopUp();
	}

	public void alertPopUp() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Invalid");
		alert.setContentText("Please Reenter");
		alert.showAndWait();
	}
}
