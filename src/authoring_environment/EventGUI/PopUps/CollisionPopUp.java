package authoring_environment.EventGUI.PopUps;

import java.util.ArrayList;
import java.util.ResourceBundle;

import authoring_environment.EventPopup;
import authoring_environment.ObjectGUI.ObjectController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import structures.data.events.CollisionEvent;
import structures.data.events.IDataEvent;
import structures.data.events.KeyPressedEvent;

public class CollisionPopUp implements PopUp{
	private ObjectController myController;
	private Group myRoot;
	private Stage myStage;
	private Scene myScene;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/EventGUI/PopUps/CollisionResources");

	public CollisionPopUp(ObjectController controller) {
		myController = controller;
		init();
	}

	public void init() {
		myRoot = new Group();
		myStage = new Stage();
		myStage.setTitle(r.getString("title"));
		Text title = new Text(r.getString("objects"));
		ListView<IDataEvent> listview = new ListView<IDataEvent>();
		ObservableList<IDataEvent>list = FXCollections.observableList(new ArrayList<IDataEvent>());
		list.addAll(myController.getEvents().keySet());
		listview.setItems(list);
		listview.setTranslateY(8);
		myRoot.getChildren().add(listview);
		myScene = new Scene(myRoot);
		myStage.setScene(myScene);
		myStage.show();
		
	}

	@Override
	public void eventPopup() {
		if (myController.getName().equals(listview.getSelectionModel().getSelectedItem().getName())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Object can't collide with itself!");
			alert.setContentText("Please choose a different object");
			alert.showAndWait();
		}
		else {
			EventPopup p = new EventPopup();
			p.popup(new CollisionEvent(myController.getObjects().get(listview.getSelectionModel().getSelectedItem())), myController.getEvents());
			myStage.close();
		}
		
	}

}
