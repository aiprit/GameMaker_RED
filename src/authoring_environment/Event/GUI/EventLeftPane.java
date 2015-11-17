package authoring_environment.Event.GUI;

import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
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
	private List<String> list;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Event/GUI/EventGUIResources");
	private ResourceBundle l = ResourceBundle.getBundle("authoring_environment/Event/GUI/EventGUIResources");
	public EventLeftPane() {
		try{

		}
		catch(NullPointerException e){
		}
	}

	public Group init() {
		Group root = new Group();
		Text title = new Text(r.getString("LeftPane"));
		title.setTranslateX(Integer.parseInt(r.getString("textTranslateX")));
		ListView<String> listview = new ListView<String>();
		Enumeration <String> keys = l.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			list.add(l.getString(key));
		}
		listview.setItems((ObservableList<String>) list);
		Button b = new Button("Add");
		b.setTranslateY(Integer.parseInt(r.getString("buttonTranslateY")));
		b.setTranslateX(Integer.parseInt(r.getString("buttonTranslateX")));
		listview.setTranslateY(Integer.parseInt(r.getString("listTranslateY")));
		root.getChildren().addAll(title,listview,b);
		b.setAlignment(Pos.BOTTOM_RIGHT);
		b.setOnAction(e ->
		add(listview.getSelectionModel().getSelectedItem()));
		return root;
	}
	private void add(String str){
//		list.remove(e);
	}

}
