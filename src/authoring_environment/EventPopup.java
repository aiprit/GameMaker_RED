package authoring_environment;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class EventPopup {

	public void popup() {
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		VBox dialogVbox = new VBox(20);
		dialogVbox.getChildren().add(new Text("This is a Dialog"));
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		dialog.setScene(dialogScene);
		dialog.show();
	}

}
