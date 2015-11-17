package authoring_environment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

import authoring_environment.ObjectGUI.ObjectController;
import authoring_environment.ObjectGUI.ObjectGUI;
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
import structures.IObject;
import structures.data.DataObject;

public class ObjectListWindow {
	private static String NEW_ITEM = "MakeNewItem";
	private static String EDIT_ITEM = "EditItem";
	private static String OBJECT_TITLE = "ObjectListTitle";
	public void init(BorderPane bp, Stage s, ResourceBundle resources){
		EventHandler<ActionEvent> sButtonClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){			 	
					IObject object = new DataObject("dog");
					ObjectController c = new ObjectController(object, null);

					ObjectGUI og = new ObjectGUI(c);
					og.init();


			}
		};
		
		ListView<HBox> listView = makeHBox(sButtonClick, 1, resources.getString(NEW_ITEM), resources.getString(OBJECT_TITLE));

		bp.setLeft(listView);
	}
	private ListView<HBox> makeHBox(EventHandler<ActionEvent> e, int n, String name, String title) {
		ArrayList<HBox> list = new ArrayList<HBox>();
		HBox header = new HBox();
		Label headerLabel = new Label(title);
		header.getChildren().addAll(headerLabel);
		list.add(header);
		for (int i = 0; i < n; i++) {
           // list.add(new HBoxCell("Item " + i, "Button " + i));
			Button plus = new Button(" + ");
			plus.setOnAction(e);
			Label label = new Label(name);
			HBox hbox = new HBox();
		
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);
            
            hbox.getChildren().addAll(label, plus);
            list.add(hbox);
		}

		ListView<HBox> listView = new ListView<HBox>();
		ObservableList<HBox> myObservableList = FXCollections.observableList(list);
		listView.setItems(myObservableList);
		return listView;
	}
	public void update(ObservableList<IObject> newList){
		
	}
}