package authoring_environment;


import authoring_environment.ObjectGUI.ObjectGUI;
import javafx.stage.Modality;
import javafx.stage.Stage;
import structures.data.DataObject;

public class ObjectPopup {

	public void popup(DataObject data) {
		Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        ObjectGUI gui = new ObjectGUI(null,dialog);
	}
}
