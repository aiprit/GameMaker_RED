package authoring_environment.ObjectGUI;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
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
