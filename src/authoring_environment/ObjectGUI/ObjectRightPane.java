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
			o = object;
			list = object.getEvents();
	}

	public Group init() {
		Group root = new Group();
		ListView<String> listview = new ListView<String>();
		root.getChildren().addAll(list);
		listview.setItems(list);
		return root;
	}
}
