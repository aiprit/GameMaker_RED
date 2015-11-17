package authoring_environment.EventGUI;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
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
	private EventController myController;
	private DataObject o;
	private ObservableList<String> list= FXCollections.observableList(new ArrayList<String>());
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/EventGUI/EventGUIResources");
	private ResourceBundle l = ResourceBundle.getBundle("authoring_environment/EventGUI/ActionListResources");
	public EventLeftPane(EventController control) {
		myController = control;
	}

	public Group init() {
		Group root = new Group();
		Text title = new Text(r.getString("LeftPane"));
		title.setTranslateX(Integer.parseInt(r.getString("textTranslateX")));
		ListView<String> listview = new ListView<String>();
		Enumeration <String> keys = l.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = l.getString(key);
			list.add(value);

		}
		listview.setItems(list);

		Button b = new Button("Add");
		b.setTranslateY(Integer.parseInt(r.getString("buttonTranslateY")));
		b.setTranslateX(Integer.parseInt(r.getString("buttonTranslateX")));
		listview.setTranslateY(Integer.parseInt(r.getString("listTranslateY")));
		root.getChildren().addAll(title,listview,b);
		b.setAlignment(Pos.BOTTOM_RIGHT);
		b.setOnAction(e ->
		addAction(listview.getSelectionModel().getSelectedItem()));
		return root;
	}
	private void addAction(String str){

	}

}
