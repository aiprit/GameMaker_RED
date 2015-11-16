package authoring_environment.Event.GUI;

import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.events.IDataEvent;

public class EventLeftPane {
	private DataObject o;
	private ObservableList<IDataEvent> list;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Event/GUI/EventGUIResources");
	public EventLeftPane() {
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
		Text title = new Text(r.getString("LeftPane"));
		title.setTranslateX(Integer.parseInt(r.getString("textTranslateX")));
		ListView<IDataEvent> listview = new ListView<IDataEvent>();
		listview.setItems(list);
		Button b = new Button("Delete");
		b.setTranslateY(Integer.parseInt(r.getString("buttonTranslateY")));
		b.setTranslateX(Integer.parseInt(r.getString("buttonTranslateX")));
		listview.setTranslateY(Integer.parseInt(r.getString("listTranslateY")));
		root.getChildren().addAll(title,listview,b);
		b.setAlignment(Pos.BOTTOM_RIGHT);
		b.setOnAction(e ->
		delete(listview.getSelectionModel().getSelectedItem()));
		return root;
	}
	private void delete(IDataEvent e){
		list.remove(e);
	}

}
