package authoring_environment.Event.GUI;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.events.IDataEvent;

public class EventLeftPane {
	private DataObject o;
	private ObservableList<IDataEvent> list;
	public EventLeftPane(DataGame object) {
		try{
//			o = object;
//			list = object.getEvents();
			// get list of events
		}
		catch(NullPointerException e){
		}
	}

	public Group init() {
		Group root = new Group();
		ListView<IDataEvent> listview = new ListView<IDataEvent>();
		listview.setItems(list);
		Button b = new Button("Delete");
		b.setTranslateY(350);
		b.setTranslateX(200);
		root.getChildren().addAll(listview,b);
		b.setAlignment(Pos.BOTTOM_RIGHT);
		b.setOnAction(e ->
		delete(listview.getSelectionModel().getSelectedItem()));
		return root;
	}
	private void delete(IDataEvent e){
		list.remove(e);
	}

}
