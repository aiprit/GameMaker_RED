package authoring_environment.ObjectPopUps;

import java.util.ArrayList;
import java.util.ResourceBundle;

import authoring_environment.PopUpError;
import authoring_environment.Event.EventController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;
import structures.data.events.CollisionEvent;
import structures.data.events.KeyPressedEvent;
import structures.data.interfaces.IDataEvent;

public class CollisionPopUp extends BasicPopUp{
	private Group myRoot;
	private Stage myStage;
	private Scene myScene;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectPopUps/CollisionResources");
	private ObservableList<DataObject> myList;
	ListView<DataObject> listview;
	private DataObject selectedObject;
	public CollisionPopUp(DataObject data, IObjectInterface game) {
		super(data,game);
		myList = game.getObjects();
		init();

	}

	public void init() {
		myRoot = new Group();
		myStage = new Stage();
		myStage.setTitle(r.getString("title"));
		VBox box = new VBox();
		Text title = new Text(r.getString("objects"));
		box.getChildren().add(title);
		Button b = new Button(r.getString("ok"));
		b.setTranslateX(Integer.parseInt(r.getString("buttonTranslateX")));
		b.setTranslateY(Integer.parseInt(r.getString("buttonTranslateY")));
		listview = new ListView<DataObject>();
		ObservableList<DataObject>list = FXCollections.observableList(new ArrayList<DataObject>());
		list.addAll(myList);
		listview.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) {
					//Use ListView's getSelected Item
					//String selected = view.getLeftPane().getListView().getSelectionModel().getSelectedItem();
					actions(click);
				}
			}
		});
		listview.setItems(list);
		listview.setTranslateY(8);
		b.setOnAction(e -> {
			actions(e);
		});
		box.getChildren().add(listview);
		myRoot.getChildren().addAll(box, b);
		myScene = new Scene(myRoot);
		myStage.setScene(myScene);
		myStage.show();

	}
	@Override
	public void eventPopup() {
		EventController p = new EventController(new CollisionEvent(selectedObject),myObject,myGame);
		p.showAndWait();
	}
	private void actions(Event e){
		selectedObject = listview.getSelectionModel().getSelectedItem();
		if(selectedObject ==null){
			PopUpError er = new PopUpError(r.getString("Error"));
		}else{
		eventPopup();
		}
		close(e);
	}
}
