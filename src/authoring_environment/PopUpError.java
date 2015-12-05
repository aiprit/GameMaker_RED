package authoring_environment;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PopUpError {
	public PopUpError(String str){
		alertPopUp(str);
	}

	public void alertPopUp(String str) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(str);
		alert.setContentText("Please Reenter");
		alert.showAndWait();
	}
}
