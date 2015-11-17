package authoring_environment;


import authoring_environment.ObjectGUI.ObjectController;
import authoring_environment.ObjectGUI.ObjectGUI;
import javafx.stage.Modality;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataObject;

public class ObjectPopup {

	public void popup(DataObject data, DataGame game) {
		Stage dialog = new Stage();
		ObjectController controller = new ObjectController(data, game.getSprites(), game);
        ObjectGUI gui = new ObjectGUI(controller);
	}
}
