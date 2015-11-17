package authoring_environment.ObjectGUI.leftPane;

import java.util.ArrayList;

import authoring_environment.ObjectGUI.ObjectController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import structures.data.events.IDataEvent;

public class CollisionPopUp {
	private ObjectController myController;
	private Stage myStage;
	
	public CollisionPopUp(ObjectController controller) {
		myController = controller;
	}
	
	private Group init() {
	Group root = new Group();
	Text title = new Text("Objects");
	title.setTranslateX(8);
	ListView<IDataEvent> listview = new ListView<IDataEvent>();
	ObservableList<IDataEvent>list = FXCollections.observableList(new ArrayList<IDataEvent>());
	list.addAll(myController.getEvents().keySet());
	listview.setItems(list);
	listview.setTranslateY(8);
	return root;
	}
	
	public void popup() {
		myStage.setScene(new Scene(init()));
		myStage.show();
	}
}
