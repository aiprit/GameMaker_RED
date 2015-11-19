package authoring_environment.ObjectPopUps;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import structures.data.DataObject;


public abstract class BasicPopUp implements PopUp {

	protected DataObject myObject;

	public BasicPopUp(DataObject obj){
		myObject = obj;
	}

	public void init() {
		eventPopup();

	}
	protected void close(ActionEvent e) {
		 Node  source = (Node)  e.getSource();
		 Stage stage  = (Stage) source.getScene().getWindow();
		 stage.close();
	}

}
