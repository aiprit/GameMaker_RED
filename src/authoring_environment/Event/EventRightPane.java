// This entire file is part of my masterpiece.
// Parit Burintrathikul
package authoring_environment.Event;

import java.util.ResourceBundle;


import javafx.collections.ObservableList;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import structures.data.interfaces.IAction;

public class EventRightPane {
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Event/EventGUIResources");
	private ListView<IAction> listView;
	Button delete;
	public Group init() {
		Group root = new Group();
		Text title = new Text(r.getString("RightPane"));
		title.setTranslateX(Integer.parseInt(r.getString("textTranslateX")));
		listView = new ListView<IAction>();
		delete = new Button(r.getString("Delete"));
		delete.setTranslateY(Integer.parseInt(r.getString("buttonTranslateY")));
		delete.setTranslateX(Integer.parseInt(r.getString("deleteButtonTranslateX")));
		root.getChildren().addAll(title,listView,delete);
		listView.setTranslateY(Integer.parseInt(r.getString("listTranslateY")));
		listView.setMinWidth(Integer.parseInt(r.getString("listviewWidth")));
		delete.setAlignment(Pos.BOTTOM_RIGHT);
		return root;
	}

	public ListView<IAction> getListView(){
		return listView;
	}
	public Button getDelete(){
		return delete;
	}
}
