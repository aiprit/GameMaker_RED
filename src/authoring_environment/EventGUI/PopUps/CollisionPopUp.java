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
import structures.data.DataObject;
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
	}

	public void init() {
		myRoot = new Group();
		myStage = new Stage();
		myStage.setTitle(r.getString("title"));
		Text title = new Text(r.getString("objects"));
		Button b = new Button(r.getString("ok"));

		ListView<DataObject> listview = new ListView<DataObject>();
		ObservableList<DataObject>list = FXCollections.observableList(new ArrayList<DataObject>());
		list.addAll(myController.getObjects());
		listview.setItems(list);
		listview.setTranslateY(8);

		b.setOnAction(e -> eventPopup(listview));
		myRoot.getChildren().addAll(title, listview, b);
		myScene = new Scene(myRoot);
		myStage.setScene(myScene);
		myStage.show();

	}

	public void eventPopup(ListView<DataObject> listview) {
		EventPopup p = new EventPopup();
		p.popup(new CollisionEvent(listview.getSelectionModel().getSelectedItem()), myController.getObject());
	}


	@Override
	public void eventPopup() {
	}
}
