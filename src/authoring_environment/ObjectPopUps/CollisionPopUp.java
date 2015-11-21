package authoring_environment.ObjectPopUps;

import java.util.ArrayList;
import java.util.ResourceBundle;


import authoring_environment.Event.EventController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.events.CollisionEvent;
import structures.data.events.IDataEvent;
import structures.data.events.KeyPressedEvent;

public class CollisionPopUp extends BasicPopUp{
	private Group myRoot;
	private Stage myStage;
	private Scene myScene;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectPopUps/CollisionResources");
	private ObservableList<DataObject> myList;
	ListView<DataObject> listview;
	private DataObject selectedObject;
	public CollisionPopUp(DataObject data, ObservableList<DataObject> list) {
		super(data);
		myList = list;
init();

	}

	public void init() {
		myRoot = new Group();
		myStage = new Stage();
		myStage.setTitle(r.getString("title"));
		Text title = new Text(r.getString("objects"));
		Button b = new Button(r.getString("ok"));
		b.setTranslateX(Integer.parseInt(r.getString("buttonTranslateX")));
		b.setTranslateY(Integer.parseInt(r.getString("buttonTranslateY")));
		listview = new ListView<DataObject>();
		ObservableList<DataObject>list = FXCollections.observableList(new ArrayList<DataObject>());
		list.addAll(myList);
		listview.setItems(list);
		listview.setTranslateY(8);
		b.setOnAction(e -> {
			select(e);
			eventPopup();
			close(e);
			});
		myRoot.getChildren().addAll(title, listview, b);
		myScene = new Scene(myRoot);
		myStage.setScene(myScene);
		myStage.show();

	}


	private void select(ActionEvent e) {
		selectedObject = listview.getSelectionModel().getSelectedItem();
	}

	@Override
	public void eventPopup() {
		EventController p = new EventController(new CollisionEvent(selectedObject),myObject);
	}

}
