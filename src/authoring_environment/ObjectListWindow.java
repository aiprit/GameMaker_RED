package authoring_environment;

import java.util.ArrayList;
import java.util.ResourceBundle;

import authoring_environment.ObjectGUI.ObjectController;
import authoring_environment.ObjectGUI.ObjectView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataSprite;

public class ObjectListWindow {


	private static String NEW_ITEM = "MakeNewItem";
	private static String EDIT_ITEM = "EditItem";
	private static String OBJECT_TITLE = "ObjectListTitle";
	public void init(ObservableList<DataObject> newObjects, ObservableList<DataSprite> newSprites, BorderPane bp, Stage s, ResourceBundle resources){
		update(newObjects, newSprites, bp, s, resources);

	}
	private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");


	public void update(ObservableList<DataObject> newObjects, ObservableList<DataSprite> newSprites, BorderPane bp, Stage s, ResourceBundle resources){
		//ArrayList<EventHandler<ActionEvent>> events = new ArrayList<EventHandler<ActionEvent>>();

		ArrayList<HBox> list = new ArrayList<HBox>();
		HBox header = new HBox();
		Label headerLabel = new Label("Object");
		header.getChildren().addAll(headerLabel);
		list.add(header);

		for(int i = 0; i <newObjects.size(); i++){
			Button edit = new Button("EDIT");
			Label label = new Label(newObjects.get(i).getName());

			HBox hbox = new HBox();
			final int j  = i;
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);
			EventHandler<ActionEvent> objectClick = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event){
						final Stage s = new Stage();
						DataObject object = newObjects.get(j);


				}
			};
			edit.setOnAction(objectClick);
			hbox.getChildren().addAll(label, edit);
            list.add(hbox);

		}
		Button plus = new Button(" + ");
		Label label = new Label("New");
		HBox hbox = new HBox();
		label.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(label, Priority.ALWAYS);
        EventHandler<ActionEvent> objectClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){

					DataObject object = new DataObject("Dog");



			}
		};
		plus.setOnAction(objectClick);
		hbox.getChildren().addAll(label, plus);
        list.add(hbox);
		//ListView<HBox> listView = makeHBox(sButtonClick, newList, resources.getString(NEW_ITEM), resources.getString(OBJECT_TITLE));
		ListView<HBox> listView = new ListView<HBox>();
		ObservableList<HBox> myObservableList = FXCollections.observableList(list);
		listView.setItems(myObservableList);

		bp.setLeft(listView);

		//return listView;
	}
	public void update(ObservableList<DataObject> newList){

	}
}