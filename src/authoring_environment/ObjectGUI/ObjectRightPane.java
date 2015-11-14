package authoring_environment.ObjectGUI;

import authoring_environment.ObjectGUI.ListViewHandler;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import structures.data.DataObject;
import structures.data.events.IDataEvent;

public class ObjectRightPane {
	private DataObject o;
	private ObservableList<IDataEvent> list;
	public ObjectRightPane(DataObject object) {
		try{
			o = object;
			list = object.getEvents();
		}
		catch(NullPointerException e){

		}
	}

	public Group init() {
		Group root = new Group();
		ListView<IDataEvent> listview = new ListView<IDataEvent>();
		listview.setItems(list);
		root.getChildren().addAll(listview);

		return root;
	}
}
